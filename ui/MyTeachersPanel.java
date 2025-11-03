package ui;

import dao.*;
import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * MyTeachersPanel shows teachers for the logged-in student's courses
 */
public class MyTeachersPanel extends JPanel {
    
    private User currentUser;
    private JTable teacherTable;
    private DefaultTableModel tableModel;
    private TeacherDAO teacherDAO;
    private StudentDAO studentDAO;
    
    public MyTeachersPanel(User user) {
        this.currentUser = user;
        this.teacherDAO = new TeacherDAO();
        this.studentDAO = new StudentDAO();
        initializeUI();
        loadTeachers();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("My Teachers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        add(titlePanel, BorderLayout.NORTH);
        
        // Table setup
        String[] columns = {"Teacher ID", "Name", "Email", "Phone", "Department", "Specialization"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        teacherTable = new JTable(tableModel);
        teacherTable.setFont(new Font("Arial", Font.PLAIN, 14));
        teacherTable.setRowHeight(30);
        teacherTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        teacherTable.getTableHeader().setBackground(new Color(52, 73, 94));
        teacherTable.getTableHeader().setForeground(Color.WHITE);
        teacherTable.setSelectionBackground(new Color(52, 152, 219));
        teacherTable.setGridColor(new Color(189, 195, 199));
        
        JScrollPane scrollPane = new JScrollPane(teacherTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadTeachers() {
        tableModel.setRowCount(0);
        
        try {
            // Get all teachers (in real app, would filter by student's enrolled courses)
            List<Teacher> teachers = teacherDAO.getAllTeachers();
            for (Teacher teacher : teachers) {
                Object[] row = {
                    teacher.getTeacherId(),
                    teacher.getFirstName() + " " + teacher.getLastName(),
                    teacher.getEmail(),
                    teacher.getPhone(),
                    teacher.getDepartment(),
                    teacher.getSpecialization()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading teachers: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
