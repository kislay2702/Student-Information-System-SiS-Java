package dao;

import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Course;

/**
 * CourseDAO handles all database operations for Course objects
 */
public class CourseDAO {
    
    /**
     * Get all courses from database with teacher names
     * @return List of all courses
     */
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.*, CONCAT(t.first_name, ' ', t.last_name) as teacher_name " +
                      "FROM courses c " +
                      "LEFT JOIN teachers t ON c.teacher_id = t.teacher_id " +
                      "ORDER BY c.course_id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                course.setTeacherName(rs.getString("teacher_name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    /**
     * Get course by ID
     * @param courseId Course ID
     * @return Course object
     */
    public Course getCourseById(int courseId) {
        String query = "SELECT c.*, CONCAT(t.first_name, ' ', t.last_name) as teacher_name " +
                      "FROM courses c " +
                      "LEFT JOIN teachers t ON c.teacher_id = t.teacher_id " +
                      "WHERE c.course_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                course.setTeacherName(rs.getString("teacher_name"));
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Add new course
     * @param course Course object to add
     * @return true if successful, false otherwise
     */
    public boolean addCourse(Course course) {
        String query = "INSERT INTO courses (course_code, course_name, description, credits, teacher_id) " +
                      "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDescription());
            stmt.setInt(4, course.getCredits());
            
            // Handle null teacher_id
            if (course.getTeacherId() > 0) {
                stmt.setInt(5, course.getTeacherId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update course information
     * @param course Course object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateCourse(Course course) {
        String query = "UPDATE courses SET course_code = ?, course_name = ?, description = ?, " +
                      "credits = ?, teacher_id = ? WHERE course_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDescription());
            stmt.setInt(4, course.getCredits());
            
            // Handle null teacher_id
            if (course.getTeacherId() > 0) {
                stmt.setInt(5, course.getTeacherId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            
            stmt.setInt(6, course.getCourseId());
            
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete course
     * @param courseId Course ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteCourse(int courseId) {
        String query = "DELETE FROM courses WHERE course_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, courseId);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Helper method to extract Course object from ResultSet
     */
    private Course extractCourseFromResultSet(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setCourseCode(rs.getString("course_code"));
        course.setCourseName(rs.getString("course_name"));
        course.setDescription(rs.getString("description"));
        course.setCredits(rs.getInt("credits"));
        course.setTeacherId(rs.getInt("teacher_id"));
        return course;
    }
}
