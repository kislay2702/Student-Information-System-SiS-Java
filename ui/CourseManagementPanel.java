package ui;

import dao.CourseDAO;
import dao.TeacherDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Course;
import models.Teacher;

/**
 * CourseManagementPanel provides UI for managing course records
 */
public class CourseManagementPanel extends JPanel {
    
    private final CourseDAO courseDAO;
    private final TeacherDAO teacherDAO;
    private JTable courseTable;
    private DefaultTableModel tableModel;
    
    public CourseManagementPanel() {
        courseDAO = new CourseDAO();
        teacherDAO = new TeacherDAO();
        initializeUI();
        loadCourses();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Course Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton addButton = createButton("Add Course", new Color(46, 204, 113));
        JButton editButton = createButton("Edit Course", new Color(52, 152, 219));
        JButton deleteButton = createButton("Delete Course", new Color(231, 76, 60));
        JButton refreshButton = createButton("Refresh", new Color(149, 165, 166));
        
        addButton.addActionListener(e -> addCourse());
        editButton.addActionListener(e -> editCourse());
        deleteButton.addActionListener(e -> deleteCourse());
        refreshButton.addActionListener(e -> loadCourses());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        titlePanel.add(buttonPanel, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Course Code", "Course Name", "Credits", "Teacher", "Description"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        courseTable = new JTable(tableModel);
        courseTable.setFont(new Font("Arial", Font.PLAIN, 13));
        courseTable.setRowHeight(30);
        courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        courseTable.getTableHeader().setBackground(new Color(52, 73, 94));
        courseTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(130, 35));
        return button;
    }
    
    private void loadCourses() {
        tableModel.setRowCount(0);
        List<Course> courses = courseDAO.getAllCourses();
        
        for (Course course : courses) {
            Object[] row = {
                course.getCourseId(),
                course.getCourseCode(),
                course.getCourseName(),
                course.getCredits(),
                course.getTeacherName() != null ? course.getTeacherName() : "Not Assigned",
                course.getDescription()
            };
            tableModel.addRow(row);
        }
    }
    
    private void addCourse() {
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        CourseDialog dialog = new CourseDialog((Frame) SwingUtilities.getWindowAncestor(this), null, teachers);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Course course = dialog.getCourse();
            if (courseDAO.addCourse(course)) {
                JOptionPane.showMessageDialog(this, "Course added successfully!");
                loadCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add course!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int courseId = (int) tableModel.getValueAt(selectedRow, 0);
        Course course = courseDAO.getCourseById(courseId);
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        
        CourseDialog dialog = new CourseDialog((Frame) SwingUtilities.getWindowAncestor(this), course, teachers);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Course updatedCourse = dialog.getCourse();
            updatedCourse.setCourseId(courseId);
            
            if (courseDAO.updateCourse(updatedCourse)) {
                JOptionPane.showMessageDialog(this, "Course updated successfully!");
                loadCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update course!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this course?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int courseId = (int) tableModel.getValueAt(selectedRow, 0);
            
            if (courseDAO.deleteCourse(courseId)) {
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
                loadCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete course!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

/**
 * Dialog for adding/editing course information
 */
class CourseDialog extends JDialog {
    private JTextField courseCodeField, courseNameField, descriptionField, creditsField;
    private JComboBox<String> teacherCombo;
    private List<Teacher> teachers;
    private boolean confirmed = false;
    private Course course;
    
    public CourseDialog(Frame parent, Course existingCourse, List<Teacher> teachers) {
        super(parent, existingCourse == null ? "Add Course" : "Edit Course", true);
        this.course = existingCourse;
        this.teachers = teachers;
        initializeUI();
    }
    
    private void initializeUI() {
        setSize(450, 450);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Course Code
        formPanel.add(new JLabel("Course Code:"));
        courseCodeField = new JTextField();
        formPanel.add(courseCodeField);
        
        // Course Name
        formPanel.add(new JLabel("Course Name:"));
        courseNameField = new JTextField();
        formPanel.add(courseNameField);
        
        // Credits
        formPanel.add(new JLabel("Credits:"));
        creditsField = new JTextField();
        formPanel.add(creditsField);
        
        // Description
        formPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        formPanel.add(descriptionField);
        
        // Teacher
        formPanel.add(new JLabel("Teacher:"));
        teacherCombo = new JComboBox<>();
        teacherCombo.addItem("Not Assigned");
        for (Teacher teacher : teachers) {
            teacherCombo.addItem(teacher.getFullName());
        }
        formPanel.add(teacherCombo);
        
        add(formPanel, BorderLayout.CENTER);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(46, 204, 113));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> save());
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(149, 165, 166));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Load existing data if editing
        if (course != null) {
            courseCodeField.setText(course.getCourseCode());
            courseNameField.setText(course.getCourseName());
            creditsField.setText(String.valueOf(course.getCredits()));
            descriptionField.setText(course.getDescription());
            
            // Select teacher
            if (course.getTeacherId() > 0) {
                for (int i = 0; i < teachers.size(); i++) {
                    if (teachers.get(i).getTeacherId() == course.getTeacherId()) {
                        teacherCombo.setSelectedIndex(i + 1);
                        break;
                    }
                }
            }
        }
    }
    
    private void save() {
        try {
            Course newCourse = new Course();
            newCourse.setCourseCode(courseCodeField.getText().trim());
            newCourse.setCourseName(courseNameField.getText().trim());
            newCourse.setCredits(Integer.parseInt(creditsField.getText().trim()));
            newCourse.setDescription(descriptionField.getText().trim());
            
            // Get selected teacher
            int teacherIndex = teacherCombo.getSelectedIndex();
            if (teacherIndex > 0) {
                newCourse.setTeacherId(teachers.get(teacherIndex - 1).getTeacherId());
            } else {
                newCourse.setTeacherId(0);
            }
            
            this.course = newCourse;
            this.confirmed = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid data! Please check all fields.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public Course getCourse() {
        return course;
    }
}
