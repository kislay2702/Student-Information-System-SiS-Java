package ui;

import dao.StudentDAO;
import java.awt.*;
import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Student;

/**
 * StudentManagementPanel provides UI for managing student records
 */
public class StudentManagementPanel extends JPanel {
    
    private StudentDAO studentDAO;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    
    public StudentManagementPanel() {
        studentDAO = new StudentDAO();
        initializeUI();
        loadStudents();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Student Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton addButton = createButton("Add Student", new Color(46, 204, 113));
        JButton editButton = createButton("Edit Student", new Color(52, 152, 219));
        JButton deleteButton = createButton("Delete Student", new Color(231, 76, 60));
        JButton refreshButton = createButton("Refresh", new Color(149, 165, 166));
        
        addButton.addActionListener(e -> addStudent());
        editButton.addActionListener(e -> editStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        refreshButton.addActionListener(e -> loadStudents());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        titlePanel.add(buttonPanel, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "First Name", "Last Name", "Email", "Phone", "Date of Birth", "Enrollment Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 13));
        studentTable.setRowHeight(30);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        studentTable.getTableHeader().setBackground(new Color(52, 73, 94));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
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
    
    private void loadStudents() {
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.getAllStudents();
        
        for (Student student : students) {
            Object[] row = {
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getDateOfBirth(),
                student.getEnrollmentDate()
            };
            tableModel.addRow(row);
        }
    }
    
    private void addStudent() {
        StudentDialog dialog = new StudentDialog((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Student student = dialog.getStudent();
            if (studentDAO.addStudent(student)) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add student!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        Student student = studentDAO.getStudentById(studentId);
        
        StudentDialog dialog = new StudentDialog((Frame) SwingUtilities.getWindowAncestor(this), student);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Student updatedStudent = dialog.getStudent();
            updatedStudent.setStudentId(studentId);
            
            if (studentDAO.updateStudent(updatedStudent)) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update student!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this student?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int studentId = (int) tableModel.getValueAt(selectedRow, 0);
            
            if (studentDAO.deleteStudent(studentId)) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete student!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

/**
 * Dialog for adding/editing student information
 */
class StudentDialog extends JDialog {
    private JTextField firstNameField, lastNameField, emailField, phoneField, dobField, enrollmentField;
    private boolean confirmed = false;
    private Student student;
    
    public StudentDialog(Frame parent, Student existingStudent) {
        super(parent, existingStudent == null ? "Add Student" : "Edit Student", true);
        this.student = existingStudent;
        initializeUI();
    }
    
    private void initializeUI() {
        setSize(450, 500);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
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
        
        // Date of Birth
        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        dobField = new JTextField();
        formPanel.add(dobField);
        
        // Enrollment Date
        formPanel.add(new JLabel("Enrollment Date (YYYY-MM-DD):"));
        enrollmentField = new JTextField();
        formPanel.add(enrollmentField);
        
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
        if (student != null) {
            firstNameField.setText(student.getFirstName());
            lastNameField.setText(student.getLastName());
            emailField.setText(student.getEmail());
            phoneField.setText(student.getPhone());
            dobField.setText(student.getDateOfBirth() != null ? student.getDateOfBirth().toString() : "");
            enrollmentField.setText(student.getEnrollmentDate() != null ? student.getEnrollmentDate().toString() : "");
        }
    }
    
    private void save() {
        try {
            Student newStudent = new Student();
            newStudent.setFirstName(firstNameField.getText().trim());
            newStudent.setLastName(lastNameField.getText().trim());
            newStudent.setEmail(emailField.getText().trim());
            newStudent.setPhone(phoneField.getText().trim());
            
            if (!dobField.getText().trim().isEmpty()) {
                newStudent.setDateOfBirth(Date.valueOf(dobField.getText().trim()));
            }
            if (!enrollmentField.getText().trim().isEmpty()) {
                newStudent.setEnrollmentDate(Date.valueOf(enrollmentField.getText().trim()));
            }
            
            this.student = newStudent;
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
    
    public Student getStudent() {
        return student;
    }
}
