# Student Information System

A comprehensive Java-based Student Information System with a beautiful GUI built using Swing/AWT and MySQL database.

## 📋 Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Default Login Credentials](#default-login-credentials)
- [System Features](#system-features)
- [Code Explanation](#code-explanation)
- [Database Schema](#database-schema)

---

## ✨ Features

### Core Features
- **User Authentication System** - Secure login with role-based access (Admin, Teacher, Student)
- **Student Management** - Add, edit, delete, and view student records
- **Teacher Management** - Complete CRUD operations for teacher information
- **Course Management** - Manage courses with teacher assignments
- **Profile Management** - Users can view and edit their own profiles
- **Beautiful UI** - Modern gradient design with intuitive navigation
- **Responsive Layout** - Clean and organized interface

### User Roles
1. **Admin** - Full access to all management features
2. **Teacher** - Can manage courses and view student information
3. **Student** - Can view their profile and course information

---

## 🛠 Technologies Used

- **Programming Language**: Java (JDK 8 or higher)
- **GUI Framework**: Java Swing & AWT
- **Database**: MySQL
- **JDBC**: MySQL Connector/J for database connectivity
- **Design Pattern**: DAO (Data Access Object) pattern
- **Architecture**: MVC-inspired layered architecture

---

## 📁 Project Structure

```
Java lab/
│
├── Main.java                          # Application entry point
├── schema.sql                         # Database schema file
│
├── database/
│   └── DatabaseConnection.java        # Database connection utility
│
├── models/                            # Data models (POJOs)
│   ├── User.java                      # User model
│   ├── Student.java                   # Student model
│   ├── Teacher.java                   # Teacher model
│   └── Course.java                    # Course model
│
├── dao/                               # Data Access Objects
│   ├── UserDAO.java                   # User database operations
│   ├── StudentDAO.java                # Student database operations
│   ├── TeacherDAO.java                # Teacher database operations
│   └── CourseDAO.java                 # Course database operations
│
└── ui/                                # User Interface components
    ├── LoginFrame.java                # Login window
    ├── DashboardFrame.java            # Main dashboard
    ├── StudentManagementPanel.java    # Student management UI
    ├── TeacherManagementPanel.java    # Teacher management UI
    ├── CourseManagementPanel.java     # Course management UI
    └── ProfilePanel.java              # User profile UI
```

---

## 📋 Prerequisites

Before running this application, ensure you have:

1. **Java Development Kit (JDK)** 
   - Version 8 or higher
   - Download from: https://www.oracle.com/java/technologies/downloads/

2. **MySQL Database Server**
   - Version 5.7 or higher
   - Download from: https://dev.mysql.com/downloads/mysql/

3. **MySQL JDBC Driver** (Connector/J)
   - Download from: https://dev.mysql.com/downloads/connector/j/
   - Or add to classpath: `mysql-connector-java-8.0.xx.jar`

4. **IDE (Optional but Recommended)**
   - IntelliJ IDEA, Eclipse, NetBeans, or VS Code with Java extensions

---

## 🚀 Installation & Setup

### Step 1: Database Setup

1. **Start MySQL Server**
   ```bash
   # On Windows (if MySQL is in PATH)
   mysql.exe -u root -p
   
   # On Mac/Linux
   mysql -u root -p
   ```

2. **Create Database and Tables**
   ```sql
   # Run the schema.sql file
   source path/to/schema.sql
   
   # Or copy and paste the contents of schema.sql into MySQL
   ```

3. **Verify Database Creation**
   ```sql
   USE student_info_system;
   SHOW TABLES;
   SELECT * FROM users;
   ```

### Step 2: Configure Database Connection

1. Open `database/DatabaseConnection.java`
2. Update the database credentials:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/student_info_system";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "your_mysql_password"; // Update this!
   ```

### Step 3: Add MySQL JDBC Driver

#### Option A: Using Command Line
Place `mysql-connector-java-x.x.xx.jar` in your project folder and include in classpath.

#### Option B: Using IDE
- **IntelliJ IDEA**: File → Project Structure → Libraries → Add JAR
- **Eclipse**: Right-click project → Build Path → Add External JARs
- **VS Code**: Add to `.classpath` or use Maven/Gradle

---

## ▶️ How to Run

### Method 1: Using Command Line

```bash
# Navigate to project directory
cd "C:\Users\Kislay\OneDrive\Desktop\Java lab"

# Compile all Java files
javac -d bin -cp "mysql-connector-java-8.0.33.jar" Main.java database/*.java models/*.java dao/*.java ui/*.java

# Run the application
java -cp "bin;mysql-connector-java-8.0.33.jar" Main
```

### Method 2: Using IDE

1. Open the project in your IDE
2. Add MySQL JDBC driver to project libraries
3. Run `Main.java`

### Method 3: Using VS Code

1. Open the project folder in VS Code
2. Ensure Java Extension Pack is installed
3. Press `F5` or click "Run" on `Main.java`

---

## 🔐 Default Login Credentials

After setting up the database with `schema.sql`, use these credentials:

| Role    | Username  | Password  |
|---------|-----------|-----------|
| Admin   | admin     | admin123  |
| Student | john.doe  | pass123   |
| Teacher | dr.smith  | teach123  |

---

## 🎯 System Features

### 1. Login System
- **File**: `LoginFrame.java`
- Beautiful gradient background
- Username and password authentication
- Role-based access control
- Error handling for invalid credentials

### 2. Dashboard
- **File**: `DashboardFrame.java`
- Side navigation menu
- Welcome screen with info cards
- User information display
- Logout functionality

### 3. Student Management
- **File**: `StudentManagementPanel.java`
- View all students in a table
- Add new student with dialog form
- Edit existing student information
- Delete student records
- Fields: Name, Email, Phone, Date of Birth, Address, Enrollment Date

### 4. Teacher Management
- **File**: `TeacherManagementPanel.java`
- Complete teacher record management
- Add/Edit/Delete operations
- Fields: Name, Email, Phone, Department, Specialization, Hire Date

### 5. Course Management
- **File**: `CourseManagementPanel.java`
- Manage course information
- Assign teachers to courses
- Fields: Course Code, Name, Description, Credits, Teacher

### 6. Profile Management
- **File**: `ProfilePanel.java`
- View personal information
- Edit profile details
- Update password
- Role-specific information display

---

## 📚 Code Explanation

### Architecture Overview

The application follows a **layered architecture** with clear separation of concerns:

1. **Presentation Layer** (`ui/` package)
   - Handles all user interface components
   - Uses Java Swing for GUI
   - Displays data and captures user input

2. **Business Logic Layer** (`dao/` package)
   - Contains Data Access Objects
   - Implements CRUD operations
   - Manages database transactions

3. **Data Layer** (`models/` package)
   - Plain Old Java Objects (POJOs)
   - Represents database entities
   - Contains getters and setters

4. **Database Layer** (`database/` package)
   - Manages database connections
   - Connection pooling
   - JDBC configuration

### Key Classes Explained

#### 1. Main.java
```java
// Entry point of the application
// Tests database connection
// Sets look and feel
// Launches login window
```

#### 2. DatabaseConnection.java
```java
// Singleton pattern for database connection
// Methods:
// - getConnection(): Returns active connection
// - closeConnection(): Closes the connection
// - testConnection(): Tests if connection works
```

#### 3. User.java (Model)
```java
// Represents a user in the system
// Fields: userId, username, password, role, email
// Used for authentication and authorization
```

#### 4. UserDAO.java
```java
// Database operations for User
// Methods:
// - authenticateUser(): Validates login credentials
// - getUserById(): Retrieves user by ID
// - createUser(): Creates new user account
// - updateUser(): Updates user information
```

#### 5. LoginFrame.java
```java
// Login window with beautiful UI
// Components:
// - Gradient background panel
// - Username and password fields
// - Login button with hover effect
// - Form validation
// - Calls UserDAO.authenticateUser()
// - Opens DashboardFrame on success
```

#### 6. DashboardFrame.java
```java
// Main application window
// Components:
// - Top bar with user info and logout
// - Left navigation panel
// - Content area with CardLayout
// - Switches between different panels
```

#### 7. StudentManagementPanel.java
```java
// Student management interface
// Components:
// - JTable to display students
// - Add/Edit/Delete buttons
// - StudentDialog for form input
// - Calls StudentDAO methods
// - Refreshes table after operations
```

### Design Patterns Used

1. **DAO Pattern**
   - Separates data access logic from business logic
   - Each entity has its own DAO class
   - Provides abstraction over database operations

2. **MVC-inspired Architecture**
   - Models: `models/` package
   - Views: `ui/` package
   - Controllers: Logic in UI event handlers and DAO methods

3. **Singleton Pattern**
   - DatabaseConnection uses singleton to ensure one connection

---

## 🗄 Database Schema

### Tables

#### 1. users
```sql
- user_id (INT, PRIMARY KEY, AUTO_INCREMENT)
- username (VARCHAR, UNIQUE, NOT NULL)
- password (VARCHAR, NOT NULL)
- role (VARCHAR, NOT NULL) -- 'ADMIN', 'TEACHER', 'STUDENT'
- email (VARCHAR)
- created_at (TIMESTAMP)
```

#### 2. students
```sql
- student_id (INT, PRIMARY KEY, AUTO_INCREMENT)
- user_id (INT, FOREIGN KEY → users.user_id)
- first_name (VARCHAR, NOT NULL)
- last_name (VARCHAR, NOT NULL)
- email (VARCHAR)
- phone (VARCHAR)
- date_of_birth (DATE)
- address (TEXT)
- enrollment_date (DATE)
```

#### 3. teachers
```sql
- teacher_id (INT, PRIMARY KEY, AUTO_INCREMENT)
- user_id (INT, FOREIGN KEY → users.user_id)
- first_name (VARCHAR, NOT NULL)
- last_name (VARCHAR, NOT NULL)
- email (VARCHAR)
- phone (VARCHAR)
- department (VARCHAR)
- specialization (VARCHAR)
- hire_date (DATE)
```

#### 4. courses
```sql
- course_id (INT, PRIMARY KEY, AUTO_INCREMENT)
- course_code (VARCHAR, UNIQUE, NOT NULL)
- course_name (VARCHAR, NOT NULL)
- description (TEXT)
- credits (INT)
- teacher_id (INT, FOREIGN KEY → teachers.teacher_id)
```

#### 5. enrollments
```sql
- enrollment_id (INT, PRIMARY KEY, AUTO_INCREMENT)
- student_id (INT, FOREIGN KEY → students.student_id)
- course_id (INT, FOREIGN KEY → courses.course_id)
- enrollment_date (DATE)
- grade (VARCHAR)
```

### Relationships
- One user can be one student or one teacher (1:1)
- One teacher can teach multiple courses (1:N)
- Students can enroll in multiple courses (M:N through enrollments)

---

## 🎨 UI Design Features

### Color Scheme
- **Primary Blue**: #3498db (rgb(52, 152, 219))
- **Success Green**: #2ecc71 (rgb(46, 204, 113))
- **Danger Red**: #e74c3c (rgb(231, 76, 60))
- **Dark Blue**: #34495e (rgb(52, 73, 94))
- **Purple**: #9b59b6 (rgb(155, 89, 182))

### UI Components
- **Gradient Backgrounds**: Login screen has blue-to-purple gradient
- **Hover Effects**: Buttons change color on mouse hover
- **Custom Borders**: Rounded corners and subtle shadows
- **Icons**: Unicode emojis for visual appeal
- **Tables**: Styled headers with alternating row colors
- **Dialogs**: Modal dialogs for form input

---

## 🔧 Troubleshooting

### Common Issues

1. **Database Connection Error**
   ```
   Solution: 
   - Check MySQL service is running
   - Verify credentials in DatabaseConnection.java
   - Ensure database exists: USE student_info_system;
   ```

2. **ClassNotFoundException: com.mysql.cj.jdbc.Driver**
   ```
   Solution:
   - Add MySQL JDBC driver to classpath
   - Download from: https://dev.mysql.com/downloads/connector/j/
   ```

3. **Table doesn't exist**
   ```
   Solution:
   - Run schema.sql file
   - Check database name is correct
   ```

4. **Login fails even with correct credentials**
   ```
   Solution:
   - Check if default data was inserted
   - Query: SELECT * FROM users;
   - Re-run INSERT statements from schema.sql
   ```

---

## 📝 How to Explain This Project

### For Demonstrations/Presentations

1. **Introduction** (1 min)
   - "This is a Student Information System built with Java Swing and MySQL"
   - "It manages students, teachers, and courses with a beautiful user interface"

2. **Show Architecture** (2 min)
   - Explain the folder structure
   - Show the separation of concerns (models, dao, ui)
   - Mention the DAO pattern

3. **Demo Features** (5 min)
   - Login with admin credentials
   - Navigate through the dashboard
   - Show student management (add, edit, delete)
   - Show teacher and course management
   - Demo profile editing

4. **Code Walkthrough** (5 min)
   - Show Main.java - entry point
   - Show DatabaseConnection.java - connection management
   - Show one Model class - data structure
   - Show one DAO class - database operations
   - Show one UI class - user interface

5. **Database Schema** (2 min)
   - Show the tables in MySQL
   - Explain relationships
   - Show sample data

### Key Points to Highlight

✅ **Clean Code**: Well-organized with packages and comments  
✅ **Design Patterns**: Uses DAO pattern for separation of concerns  
✅ **Beautiful UI**: Modern gradient design with hover effects  
✅ **CRUD Operations**: Complete Create, Read, Update, Delete functionality  
✅ **Security**: Login authentication with role-based access  
✅ **Database Integration**: Proper JDBC usage with PreparedStatements  
✅ **Error Handling**: Try-catch blocks and user-friendly error messages  
✅ **Simple & Maintainable**: Easy to understand and extend  

---

## 🚀 Future Enhancements

- Add password encryption (BCrypt)
- Implement search and filter functionality
- Add student enrollment in courses
- Generate reports (PDF export)
- Add grade management system
- Email notification system
- Attendance tracking
- Dashboard analytics with charts

---

## 👨‍💻 Author

Created as a comprehensive Java Swing project demonstrating:
- Object-Oriented Programming
- Database connectivity with JDBC
- GUI development with Swing/AWT
- MVC architecture
- CRUD operations

---

## 📄 License

This project is created for educational purposes and is free to use and modify.

---

## 🙏 Acknowledgments

- Java Swing Documentation
- MySQL Documentation
- Design inspiration from modern web applications

---

**Happy Coding! 🎓**
