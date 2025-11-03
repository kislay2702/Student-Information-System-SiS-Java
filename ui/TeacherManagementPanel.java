package ui;

import dao.TeacherDAO;
import java.awt.*;
import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Teacher;

/**
 * TeacherManagementPanel provides UI for managing teacher records
 */
public class TeacherManagementPanel extends JPanel {
    
    private final TeacherDAO teacherDAO;
    private JTable teacherTable;
    private DefaultTableModel tableModel;
    
    public TeacherManagementPanel() {
        teacherDAO = new TeacherDAO();
        initializeUI();
        loadTeachers();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Teacher Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton addButton = createButton("Add Teacher", new Color(46, 204, 113));
        JButton editButton = createButton("Edit Teacher", new Color(52, 152, 219));
        JButton deleteButton = createButton("Delete Teacher", new Color(231, 76, 60));
        JButton refreshButton = createButton("Refresh", new Color(149, 165, 166));
        
        addButton.addActionListener(e -> addTeacher());
        editButton.addActionListener(e -> editTeacher());
        deleteButton.addActionListener(e -> deleteTeacher());
        refreshButton.addActionListener(e -> loadTeachers());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        titlePanel.add(buttonPanel, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "First Name", "Last Name", "Email", "Phone", "Department", "Specialization"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        teacherTable = new JTable(tableModel);
        teacherTable.setFont(new Font("Arial", Font.PLAIN, 13));
        teacherTable.setRowHeight(30);
        teacherTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teacherTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        teacherTable.getTableHeader().setBackground(new Color(52, 73, 94));
        teacherTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(teacherTable);
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
    
    private void loadTeachers() {
        tableModel.setRowCount(0);
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        
        for (Teacher teacher : teachers) {
            Object[] row = {
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getPhone(),
                teacher.getDepartment(),
                teacher.getSpecialization()
            };
            tableModel.addRow(row);
        }
    }
    
    private void addTeacher() {
        TeacherDialog dialog = new TeacherDialog((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Teacher teacher = dialog.getTeacher();
            if (teacherDAO.addTeacher(teacher)) {
                JOptionPane.showMessageDialog(this, "Teacher added successfully!");
                loadTeachers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add teacher!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editTeacher() {
        int selectedRow = teacherTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a teacher to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int teacherId = (int) tableModel.getValueAt(selectedRow, 0);
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        
        TeacherDialog dialog = new TeacherDialog((Frame) SwingUtilities.getWindowAncestor(this), teacher);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Teacher updatedTeacher = dialog.getTeacher();
            updatedTeacher.setTeacherId(teacherId);
            
            if (teacherDAO.updateTeacher(updatedTeacher)) {
                JOptionPane.showMessageDialog(this, "Teacher updated successfully!");
                loadTeachers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update teacher!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteTeacher() {
        int selectedRow = teacherTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a teacher to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this teacher?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int teacherId = (int) tableModel.getValueAt(selectedRow, 0);
            
            if (teacherDAO.deleteTeacher(teacherId)) {
                JOptionPane.showMessageDialog(this, "Teacher deleted successfully!");
                loadTeachers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete teacher!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

/**
 * Dialog for adding/editing teacher information
 */
class TeacherDialog extends JDialog {
    private JTextField firstNameField, lastNameField, emailField, phoneField;
    private JTextField departmentField, specializationField, hireDateField;
    private boolean confirmed = false;
    private Teacher teacher;
    
    public TeacherDialog(Frame parent, Teacher existingTeacher) {
        super(parent, existingTeacher == null ? "Add Teacher" : "Edit Teacher", true);
        this.teacher = existingTeacher;
        initializeUI();
    }
    
    private void initializeUI() {
        setSize(450, 550);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // First Name
        formPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);
        
        // Last Name
        formPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);
        
        // Email
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);
        
        // Phone
        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);
        
        // Department
        formPanel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        formPanel.add(departmentField);
        
        // Specialization
        formPanel.add(new JLabel("Specialization:"));
        specializationField = new JTextField();
        formPanel.add(specializationField);
        
        // Hire Date
        formPanel.add(new JLabel("Hire Date (YYYY-MM-DD):"));
        hireDateField = new JTextField();
        formPanel.add(hireDateField);
        
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
        if (teacher != null) {
            firstNameField.setText(teacher.getFirstName());
            lastNameField.setText(teacher.getLastName());
            emailField.setText(teacher.getEmail());
            phoneField.setText(teacher.getPhone());
            departmentField.setText(teacher.getDepartment());
            specializationField.setText(teacher.getSpecialization());
            hireDateField.setText(teacher.getHireDate() != null ? teacher.getHireDate().toString() : "");
        }
    }
    
    private void save() {
        try {
            Teacher newTeacher = new Teacher();
            newTeacher.setFirstName(firstNameField.getText().trim());
            newTeacher.setLastName(lastNameField.getText().trim());
            newTeacher.setEmail(emailField.getText().trim());
            newTeacher.setPhone(phoneField.getText().trim());
            newTeacher.setDepartment(departmentField.getText().trim());
            newTeacher.setSpecialization(specializationField.getText().trim());
            
            if (!hireDateField.getText().trim().isEmpty()) {
                newTeacher.setHireDate(Date.valueOf(hireDateField.getText().trim()));
            }
            
            this.teacher = newTeacher;
            this.confirmed = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid data! Please check date format (YYYY-MM-DD)", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
}
