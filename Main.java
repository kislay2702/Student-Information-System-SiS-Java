import database.DatabaseConnection;
import javax.swing.*;
import ui.LoginFrame;

/**
 * Main class - Entry point for the Student Information System
 * This class initializes the application and displays the login window
 */
public class Main {
    
    public static void main(String[] args) {
        // Test database connection
        System.out.println("Testing database connection...");
        DatabaseConnection.testConnection();
        
        // Set the look and feel to the system default for better UI appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set look and feel: " + e.getMessage());
        }
        
        // Create and display the login frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
        
        System.out.println("Student Information System started successfully!");
        System.out.println("Default login credentials:");
        System.out.println("Username: admin");
        System.out.println("Password: admin123");
    }
}
