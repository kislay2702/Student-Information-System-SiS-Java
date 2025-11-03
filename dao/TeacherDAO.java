package dao;

import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Teacher;

/**
 * TeacherDAO handles all database operations for Teacher objects
 */
public class TeacherDAO {
    
    /**
     * Get all teachers from database
     * @return List of all teachers
     */
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers ORDER BY teacher_id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Teacher teacher = extractTeacherFromResultSet(rs);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }
    
    /**
     * Get teacher by ID
     * @param teacherId Teacher ID
     * @return Teacher object
     */
    public Teacher getTeacherById(int teacherId) {
        String query = "SELECT * FROM teachers WHERE teacher_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractTeacherFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get teacher by user ID
     * @param userId User ID
     * @return Teacher object
     */
    public Teacher getTeacherByUserId(int userId) {
        String query = "SELECT * FROM teachers WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractTeacherFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Add new teacher with auto-created user account
     * @param teacher Teacher object to add
     * @return true if successful, false otherwise
     */
    public boolean addTeacher(Teacher teacher) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            // Step 1: Create user account with default password
            String username = teacher.getEmail().split("@")[0]; // Extract username from email
            String insertUserQuery = "INSERT INTO users (username, password, role, email) VALUES (?, ?, ?, ?)";
            int userId = 0;
            
            try (PreparedStatement userStmt = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
                userStmt.setString(1, username);
                userStmt.setString(2, "teacher123"); // Default password
                userStmt.setString(3, "TEACHER");
                userStmt.setString(4, teacher.getEmail());
                userStmt.executeUpdate();
                
                // Get generated user_id
                ResultSet rs = userStmt.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
            }
            
            // Step 2: Insert teacher with the created user_id
            String insertTeacherQuery = "INSERT INTO teachers (user_id, first_name, last_name, email, phone, " +
                          "department, specialization, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement stmt = conn.prepareStatement(insertTeacherQuery)) {
                stmt.setInt(1, userId);
                stmt.setString(2, teacher.getFirstName());
                stmt.setString(3, teacher.getLastName());
                stmt.setString(4, teacher.getEmail());
                stmt.setString(5, teacher.getPhone());
                stmt.setString(6, teacher.getDepartment());
                stmt.setString(7, teacher.getSpecialization());
                stmt.setDate(8, teacher.getHireDate());
                
                stmt.executeUpdate();
            }
            
            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Update teacher information
     * @param teacher Teacher object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateTeacher(Teacher teacher) {
        String query = "UPDATE teachers SET first_name = ?, last_name = ?, email = ?, " +
                      "phone = ?, department = ?, specialization = ?, hire_date = ? " +
                      "WHERE teacher_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getEmail());
            stmt.setString(4, teacher.getPhone());
            stmt.setString(5, teacher.getDepartment());
            stmt.setString(6, teacher.getSpecialization());
            stmt.setDate(7, teacher.getHireDate());
            stmt.setInt(8, teacher.getTeacherId());
            
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete teacher
     * @param teacherId Teacher ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteTeacher(int teacherId) {
        String query = "DELETE FROM teachers WHERE teacher_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, teacherId);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Helper method to extract Teacher object from ResultSet
     */
    private Teacher extractTeacherFromResultSet(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getInt("teacher_id"));
        teacher.setUserId(rs.getInt("user_id"));
        teacher.setFirstName(rs.getString("first_name"));
        teacher.setLastName(rs.getString("last_name"));
        teacher.setEmail(rs.getString("email"));
        teacher.setPhone(rs.getString("phone"));
        teacher.setDepartment(rs.getString("department"));
        teacher.setSpecialization(rs.getString("specialization"));
        teacher.setHireDate(rs.getDate("hire_date"));
        return teacher;
    }
}
