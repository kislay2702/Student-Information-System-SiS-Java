package ui;

import java.awt.*;
import javax.swing.*;
import models.User;

/**
 * DashboardFrame is the main application window after login
 * It contains navigation and displays different panels based on user selection
 */
public class DashboardFrame extends JFrame {
    
    private User currentUser;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    public DashboardFrame(User user) {
        this.currentUser = user;
        initializeUI();
    }
    
    private void initializeUI() {
        // Frame settings
        setTitle("Student Information System - Dashboard");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main container
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        
        // Top bar with user info and logout
        JPanel topBar = createTopBar();
        container.add(topBar, BorderLayout.NORTH);
        
        // Left navigation panel
        JPanel navigationPanel = createNavigationPanel();
        container.add(navigationPanel, BorderLayout.WEST);
        
        // Content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        
        // Add different panels based on user role
        String role = currentUser.getRole();
        
        if (role.equals("ADMIN")) {
            // Admin panels
            contentPanel.add(new StudentManagementPanel(), "Students");
            contentPanel.add(new TeacherManagementPanel(), "Teachers");
            contentPanel.add(new CourseManagementPanel(), "Courses");
        } else if (role.equals("STUDENT")) {
            // Student panels
            contentPanel.add(new MyCoursesPanel(currentUser), "MyCourses");
            contentPanel.add(new MyTeachersPanel(currentUser), "MyTeachers");
        } else if (role.equals("TEACHER")) {
            // Teacher panels
            contentPanel.add(new MyCoursesPanel(currentUser), "MyCourses");
            contentPanel.add(new MyStudentsPanel(currentUser), "MyStudents");
        }
        
        contentPanel.add(new ProfilePanel(currentUser), "Profile");
        contentPanel.add(createWelcomePanel(), "Welcome");
        
        container.add(contentPanel, BorderLayout.CENTER);
        
        // Show welcome panel by default
        cardLayout.show(contentPanel, "Welcome");
    }
    
    private JPanel createTopBar() {
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(52, 73, 94));
        topBar.setPreferredSize(new Dimension(0, 60));
        topBar.setLayout(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Student Information System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        topBar.add(titleLabel, BorderLayout.WEST);
        
        // User info panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        userPanel.setOpaque(false);
        
        JLabel userLabel = new JLabel("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);
        userPanel.add(userLabel);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setBackground(new Color(231, 76, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(e -> logout());
        userPanel.add(logoutButton);
        
        topBar.add(userPanel, BorderLayout.EAST);
        
        return topBar;
    }
    
    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(44, 62, 80));
        navPanel.setPreferredSize(new Dimension(220, 0));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Navigation buttons based on user role
        addNavButton(navPanel, "🏠 Home", "Welcome");
        
        String role = currentUser.getRole();
        
        if (role.equals("ADMIN")) {
            // Admin sees all tabs
            addNavButton(navPanel, "👨‍🎓 Students", "Students");
            addNavButton(navPanel, "👨‍🏫 Teachers", "Teachers");
            addNavButton(navPanel, "📚 Courses", "Courses");
        } else if (role.equals("STUDENT")) {
            // Student sees their courses and teachers
            addNavButton(navPanel, "📚 My Courses", "MyCourses");
            addNavButton(navPanel, "👨‍🏫 My Teachers", "MyTeachers");
        } else if (role.equals("TEACHER")) {
            // Teacher sees their courses and students
            addNavButton(navPanel, "📚 My Courses", "MyCourses");
            addNavButton(navPanel, "👨‍🎓 My Students", "MyStudents");
        }
        
        addNavButton(navPanel, "👤 Profile", "Profile");
        
        return navPanel;
    }
    
    private void addNavButton(JPanel panel, String text, String panelName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(44, 62, 80));
        button.setMaximumSize(new Dimension(220, 50));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 73, 94));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(44, 62, 80));
            }
        });
        
        // Click action
        button.addActionListener(e -> cardLayout.show(contentPanel, panelName));
        
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }
    
    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setBackground(Color.WHITE);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        
        JLabel welcomeLabel = new JLabel("Welcome to Student Information System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(52, 73, 94));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(welcomeLabel);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel infoLabel = new JLabel("Select an option from the left menu to get started");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setForeground(new Color(127, 140, 141));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(infoLabel);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Info cards
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setMaximumSize(new Dimension(900, 150));
        
        cardsPanel.add(createInfoCard("Students", "Manage student records", new Color(52, 152, 219)));
        cardsPanel.add(createInfoCard("Teachers", "Manage teacher information", new Color(46, 204, 113)));
        cardsPanel.add(createInfoCard("Courses", "Manage courses", new Color(155, 89, 182)));
        
        centerPanel.add(cardsPanel);
        
        welcomePanel.add(centerPanel, BorderLayout.CENTER);
        
        return welcomePanel;
    }
    
    private JPanel createInfoCard(String title, String description, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);
        
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setForeground(Color.WHITE);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(descLabel);
        
        return card;
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            });
        }
    }
}
