package ui;

import dao.*;
import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * MyCoursesPanel shows courses for the logged-in student or teacher
 */
public class MyCoursesPanel extends JPanel {
    
    private User currentUser;
    private JTable courseTable;
    private DefaultTableModel tableModel;
    private CourseDAO courseDAO;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    
    public MyCoursesPanel(User user) {
        this.currentUser = user;
        this.courseDAO = new CourseDAO();
        this.studentDAO = new StudentDAO();
        this.teacherDAO = new TeacherDAO();
        initializeUI();
        loadCourses();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("My Courses");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        add(titlePanel, BorderLayout.NORTH);
        
        // Table setup
        String[] columns;
        if (currentUser.getRole().equals("STUDENT")) {
            columns = new String[]{"Course ID", "Course Code", "Course Name", "Credits", "Teacher", "Grade"};
        } else {
            columns = new String[]{"Course ID", "Course Code", "Course Name", "Credits", "Department"};
        }
        
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        courseTable = new JTable(tableModel);
        courseTable.setFont(new Font("Arial", Font.PLAIN, 14));
        courseTable.setRowHeight(30);
        courseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        courseTable.getTableHeader().setBackground(new Color(52, 73, 94));
        courseTable.getTableHeader().setForeground(Color.WHITE);
        courseTable.setSelectionBackground(new Color(52, 152, 219));
        courseTable.setGridColor(new Color(189, 195, 199));
        
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadCourses() {
        tableModel.setRowCount(0);
        
        try {
            if (currentUser.getRole().equals("STUDENT")) {
                // Get student's enrolled courses
                Student student = studentDAO.getStudentByUserId(currentUser.getUserId());
                if (student != null) {
                    // Query to get enrolled courses with grades
                    List<Course> courses = courseDAO.getAllCourses();
                    for (Course course : courses) {
                        // In a real app, you'd query enrollments table
                        Teacher teacher = teacherDAO.getTeacherById(course.getTeacherId());
                        String teacherName = teacher != null ? teacher.getFirstName() + " " + teacher.getLastName() : "N/A";
                        
                        Object[] row = {
                            course.getCourseId(),
                            course.getCourseCode(),
                            course.getCourseName(),
                            course.getCredits(),
                            teacherName,
                            "N/A" // Grade would come from enrollments table
                        };
                        tableModel.addRow(row);
                    }
                }
            } else if (currentUser.getRole().equals("TEACHER")) {
                // Get teacher's assigned courses
                Teacher teacher = teacherDAO.getTeacherByUserId(currentUser.getUserId());
                if (teacher != null) {
                    List<Course> courses = courseDAO.getAllCourses();
                    for (Course course : courses) {
                        if (course.getTeacherId() == teacher.getTeacherId()) {
                            Object[] row = {
                                course.getCourseId(),
                                course.getCourseCode(),
                                course.getCourseName(),
                                course.getCredits(),
                                teacher.getDepartment()
                            };
                            tableModel.addRow(row);
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading courses: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
