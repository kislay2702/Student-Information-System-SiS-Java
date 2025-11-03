package ui;

import dao.StudentDAO;
import dao.TeacherDAO;
import dao.UserDAO;
import java.awt.*;
import javax.swing.*;
import models.Student;
import models.Teacher;
import models.User;

/**
 * ProfilePanel allows users to view and edit their profile information
 */
public class ProfilePanel extends JPanel {
    
    private final User currentUser;
    private final UserDAO userDAO;
    private final StudentDAO studentDAO;
    private final TeacherDAO teacherDAO;
    
    private JTextField usernameField, emailField, passwordField;
    private JTextField firstNameField, lastNameField, phoneField;
    private JTextArea additionalInfoArea;
    
    public ProfilePanel(User user) {
        this.currentUser = user;
        this.userDAO = new UserDAO();
        this.studentDAO = new StudentDAO();
        this.teacherDAO = new TeacherDAO();
        initializeUI();
        loadProfileData();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        add(titlePanel, BorderLayout.NORTH);
        
        // Center panel with profile form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        
        // Profile card
        JPanel profileCard = new JPanel();
        profileCard.setLayout(new BorderLayout(20, 20));
        profileCard.setBackground(Color.WHITE);
        profileCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        // User Icon
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.Y_AXIS));
        iconPanel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel("👤");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconPanel.add(iconLabel);
        
        iconPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel roleLabel = new JLabel(currentUser.getRole());
        roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roleLabel.setForeground(new Color(52, 152, 219));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconPanel.add(roleLabel);
        
        profileCard.add(iconPanel, BorderLayout.WEST);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 15));
        formPanel.setBackground(Color.WHITE);
        
        // Username
        formPanel.add(createLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        
        // Email
        formPanel.add(createLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);
        
        // Password
        formPanel.add(createLabel("Password:"));
        passwordField = new JTextField();
        formPanel.add(passwordField);
        
        // First Name
        formPanel.add(createLabel("First Name:"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);
        
        // Last Name
        formPanel.add(createLabel("Last Name:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);
        
        // Phone
        formPanel.add(createLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);
        
        // Additional Info
        formPanel.add(createLabel("Additional Info:"));
        additionalInfoArea = new JTextArea(3, 20);
        additionalInfoArea.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        additionalInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(additionalInfoArea);
        formPanel.add(scrollPane);
        
        profileCard.add(formPanel, BorderLayout.CENTER);
        
        centerPanel.add(profileCard);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton updateButton = new JButton("Update Profile");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(46, 204, 113));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorderPainted(false);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setPreferredSize(new Dimension(150, 40));
        updateButton.addActionListener(e -> updateProfile());
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBackground(new Color(52, 152, 219));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.setPreferredSize(new Dimension(150, 40));
        refreshButton.addActionListener(e -> loadProfileData());
        
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        changePasswordButton.setBackground(new Color(155, 89, 182));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.setBorderPainted(false);
        changePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changePasswordButton.setPreferredSize(new Dimension(180, 40));
        changePasswordButton.addActionListener(e -> changePassword());
        
        buttonPanel.add(updateButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(changePasswordButton);
        
        centerPanel.add(buttonPanel);
        
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(44, 62, 80));
        return label;
    }
    
    private void loadProfileData() {
        // Load user data
        usernameField.setText(currentUser.getUsername());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(currentUser.getPassword());
        
        // Load role-specific data
        if (currentUser.getRole().equals("STUDENT")) {
            Student student = studentDAO.getStudentByUserId(currentUser.getUserId());
            if (student != null) {
                firstNameField.setText(student.getFirstName());
                lastNameField.setText(student.getLastName());
                phoneField.setText(student.getPhone());
                
                StringBuilder info = new StringBuilder();
                info.append("Date of Birth: ").append(student.getDateOfBirth()).append("\n");
                info.append("Enrollment Date: ").append(student.getEnrollmentDate()).append("\n");
                info.append("Address: ").append(student.getAddress());
                additionalInfoArea.setText(info.toString());
            }
        } else if (currentUser.getRole().equals("TEACHER")) {
            Teacher teacher = teacherDAO.getTeacherByUserId(currentUser.getUserId());
            if (teacher != null) {
                firstNameField.setText(teacher.getFirstName());
                lastNameField.setText(teacher.getLastName());
                phoneField.setText(teacher.getPhone());
                
                StringBuilder info = new StringBuilder();
                info.append("Department: ").append(teacher.getDepartment()).append("\n");
                info.append("Specialization: ").append(teacher.getSpecialization()).append("\n");
                info.append("Hire Date: ").append(teacher.getHireDate());
                additionalInfoArea.setText(info.toString());
            }
        } else {
            // Admin user
            firstNameField.setText("Admin");
            lastNameField.setText("User");
            phoneField.setText("N/A");
            additionalInfoArea.setText("Administrator Account");
        }
    }
    
    private void updateProfile() {
        // Update user credentials
        currentUser.setUsername(usernameField.getText().trim());
        currentUser.setEmail(emailField.getText().trim());
        currentUser.setPassword(passwordField.getText().trim());
        
        boolean success = userDAO.updateUser(currentUser);
        
        // Update role-specific data
        if (currentUser.getRole().equals("STUDENT")) {
            Student student = studentDAO.getStudentByUserId(currentUser.getUserId());
            if (student != null) {
                student.setFirstName(firstNameField.getText().trim());
                student.setLastName(lastNameField.getText().trim());
                student.setPhone(phoneField.getText().trim());
                student.setEmail(emailField.getText().trim());
                success = success && studentDAO.updateStudent(student);
            }
        } else if (currentUser.getRole().equals("TEACHER")) {
            Teacher teacher = teacherDAO.getTeacherByUserId(currentUser.getUserId());
            if (teacher != null) {
                teacher.setFirstName(firstNameField.getText().trim());
                teacher.setLastName(lastNameField.getText().trim());
                teacher.setPhone(phoneField.getText().trim());
                teacher.setEmail(emailField.getText().trim());
                success = success && teacherDAO.updateTeacher(teacher);
            }
        }
        
        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Profile updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Failed to update profile!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void changePassword() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPasswordField oldPasswordField = new JPasswordField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        
        panel.add(new JLabel("Current Password:"));
        panel.add(oldPasswordField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);
        panel.add(new JLabel("Confirm New Password:"));
        panel.add(confirmPasswordField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Change Password", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "New passwords do not match!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean success = userDAO.changePassword(currentUser.getUserId(), oldPassword, newPassword);
            
            if (success) {
                currentUser.setPassword(newPassword);
                passwordField.setText(newPassword);
                JOptionPane.showMessageDialog(this, 
                    "Password changed successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to change password! Please check your current password.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
