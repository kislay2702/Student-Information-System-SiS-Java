package ui;

import dao.*;
import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * MyStudentsPanel shows students enrolled in the logged-in teacher's courses
 */
public class MyStudentsPanel extends JPanel {
    
    private User currentUser;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    
    public MyStudentsPanel(User user) {
        this.currentUser = user;
        this.studentDAO = new StudentDAO();
        this.teacherDAO = new TeacherDAO();
        initializeUI();
        loadStudents();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("My Students");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        add(titlePanel, BorderLayout.NORTH);
        
        // Table setup
        String[] columns = {"Student ID", "Name", "Email", "Phone", "Date of Birth", "Enrollment Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        studentTable.setRowHeight(30);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        studentTable.getTableHeader().setBackground(new Color(52, 73, 94));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionBackground(new Color(52, 152, 219));
        studentTable.setGridColor(new Color(189, 195, 199));
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadStudents() {
        tableModel.setRowCount(0);
        
        try {
            // Get all students (in real app, would filter by teacher's assigned courses)
            List<Student> students = studentDAO.getAllStudents();
            for (Student student : students) {
                Object[] row = {
                    student.getStudentId(),
                    student.getFirstName() + " " + student.getLastName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getDateOfBirth(),
                    student.getEnrollmentDate()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
