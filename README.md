# 🎓 Student Information System (SIS)# 🎓 Student Information System (SIS)



A Java-based Student Information System with role-based login, beautiful Swing GUI, and MySQL database.A Java-based Student Information System with role-based login, beautiful Swing GUI, and MySQL database.



## ✨ Features## ✨ Features



- **Role-Based Login** - Admin, Teacher, and Student access levels- **Role-Based Login** - Admin, Teacher, and Student access levels

- **Auto User Creation** - New students/teachers automatically get login accounts  - **Auto User Creation** - New students/teachers automatically get login accounts

- **Student Management** - Add, edit, delete, and search student records- **Student Management** - Add, edit, delete, and search student records

- **Teacher Management** - Complete teacher information management- **Teacher Management** - Complete teacher information management

- **Course Management** - Manage courses with teacher assignments- **Course Management** - Manage courses with teacher assignments

- **Profile & Password** - Users can edit profile and change passwords- **Profile & Password** - Users can edit profile and change passwords

- **Beautiful UI** - Modern gradient design with color-coded buttons- **Beautiful UI** - Modern gradient design with color-coded buttons



------



## 🛠 Technologies## 🛠 Technologies



- **Java** - JDK 8+ with Swing/AWT- **Java** - JDK 8+ with Swing/AWT

- **MySQL** - Database server- **MySQL** - Database server

- **JDBC** - MySQL Connector/J 9.5.0- **JDBC** - MySQL Connector/J 9.5.0

- **Design Pattern** - DAO (Data Access Object)- **Design Pattern** - DAO (Data Access Object)



------



## 📁 Project Structure## 📁 Project Structure



``````

├── Main.java                          # Application entry pointJava lab/

├── database/│

│   └── DatabaseConnection.java        # Database connection manager├── Main.java                          # Application entry point

├── models/├── schema.sql                         # Database schema file

│   ├── User.java                      # User model│

│   ├── Student.java                   # Student model├── database/

│   ├── Teacher.java                   # Teacher model│   └── DatabaseConnection.java        # Database connection utility

│   └── Course.java                    # Course model│

├── dao/├── models/                            # Data models (POJOs)

│   ├── UserDAO.java                   # User database operations│   ├── User.java                      # User model

│   ├── StudentDAO.java                # Student database operations│   ├── Student.java                   # Student model

│   ├── TeacherDAO.java                # Teacher database operations│   ├── Teacher.java                   # Teacher model

│   └── CourseDAO.java                 # Course database operations│   └── Course.java                    # Course model

└── ui/│

    ├── LoginFrame.java                # Login window├── dao/                               # Data Access Objects

    ├── DashboardFrame.java            # Main dashboard│   ├── UserDAO.java                   # User database operations

    ├── StudentManagementPanel.java    # Student management│   ├── StudentDAO.java                # Student database operations

    ├── TeacherManagementPanel.java    # Teacher management│   ├── TeacherDAO.java                # Teacher database operations

    ├── CourseManagementPanel.java     # Course management│   └── CourseDAO.java                 # Course database operations

    ├── ProfilePanel.java              # User profile│

    ├── MyCoursesPanel.java            # Student/Teacher courses view└── ui/                                # User Interface components

    ├── MyTeachersPanel.java           # Student's teachers view    ├── LoginFrame.java                # Login window

    └── MyStudentsPanel.java           # Teacher's students view    ├── DashboardFrame.java            # Main dashboard

```    ├── StudentManagementPanel.java    # Student management UI

    ├── TeacherManagementPanel.java    # Teacher management UI

---    ├── CourseManagementPanel.java     # Course management UI

    └── ProfilePanel.java              # User profile UI

## 📋 Prerequisites```



- **Java JDK 8+**---

- **MySQL Server**

- **MySQL Connector/J** (included: mysql-connector-j-9.5.0.jar)## 📋 Prerequisites



---- **Java JDK 8+**

- **MySQL Server**

## 🚀 Quick Setup- **MySQL Connector/J** (included: mysql-connector-j-9.5.0.jar)



### 1. Setup Database---

```bash

# Login to MySQL## 🚀 Quick Setup

mysql -u root -p

### 1. Setup Database

# Run the schema```bash

source setup_database.sql# Login to MySQL

```mysql -u root -p



### 2. Update Password# Run the schema

Edit `database/DatabaseConnection.java`:source setup_database.sql

```java```

private static final String PASSWORD = "your_mysql_password";

```### 2. Update Password

Edit `database/DatabaseConnection.java`:

### 3. Compile & Run```java

```bashprivate static final String PASSWORD = "your_mysql_password";

# Compile```

javac -d bin -cp "mysql-connector-j-9.5.0.jar" Main.java database\*.java models\*.java dao\*.java ui\*.java

### 3. Compile & Run

# Run```bash

java -cp "bin;mysql-connector-j-9.5.0.jar" Main# Compile

```javac -d bin -cp "mysql-connector-j-9.5.0.jar" Main.java database\*.java models\*.java dao\*.java ui\*.java



**Or use the batch files:**# Run

```bashjava -cp "bin;mysql-connector-j-9.5.0.jar" Main

compile.bat  # Compile```

run.bat      # Run

```**Or use the batch files:**

```bash

---compile.bat  # Compile

run.bat      # Run

## 🔑 Default Login```



| Role    | Username  | Password    |### Method 3: Using VS Code

|---------|-----------|-------------|

| Admin   | admin     | admin123    |1. Open the project folder in VS Code

| Student | john.doe  | student123  |2. Ensure Java Extension Pack is installed

| Teacher | dr.smith  | teacher123  |3. Press `F5` or click "Run" on `Main.java`



**New students/teachers automatically get:**---

- Username: email prefix (e.g., alice.wonder@school.com → alice.wonder)

- Password: student123 or teacher123## 🔐 Default Login Credentials



---After setting up the database with `schema.sql`, use these credentials:



## 🎯 How It Works| Role    | Username  | Password  |

|---------|-----------|-----------|

### Architecture| Admin   | admin     | admin123  |

- **Presentation Layer** (`ui/`) - Swing GUI components| Student | john.doe  | pass123   |

- **Business Logic** (`dao/`) - Database operations  | Teacher | dr.smith  | teach123  |

- **Data Layer** (`models/`) - Java POJOs

- **Database** (`database/`) - Connection management---



### Key Features by Role## 🎯 System Features



**Admin:**### 1. Login System

- Full access to manage students, teachers, and courses- **File**: `LoginFrame.java`

- Can add/edit/delete all records- Beautiful gradient background

- Username and password authentication

**Student:**- Role-based access control

- View enrolled courses- Error handling for invalid credentials

- View teachers

- Edit own profile### 2. Dashboard

- Change password- **File**: `DashboardFrame.java`

- Side navigation menu

**Teacher:**- Welcome screen with info cards

- View assigned courses- User information display

- View all students- Logout functionality

- Edit own profile

- Change password### 3. Student Management

- **File**: `StudentManagementPanel.java`

---- View all students in a table

- Add new student with dialog form

## 🗄 Database Schema- Edit existing student information

- Delete student records

**Tables:** users, students, teachers, courses, enrollments- Fields: Name, Email, Phone, Date of Birth, Address, Enrollment Date



**Key Relationships:**### 4. Teacher Management

- `users` ← `students` (1:1)- **File**: `TeacherManagementPanel.java`

- `users` ← `teachers` (1:1)- Complete teacher record management

- `teachers` → `courses` (1:N)- Add/Edit/Delete operations

- `students` ↔ `courses` (M:N via enrollments)- Fields: Name, Email, Phone, Department, Specialization, Hire Date



---### 5. Course Management

- **File**: `CourseManagementPanel.java`

## 🎨 UI Design- Manage course information

- Assign teachers to courses

- **Color Scheme:** Blue (#3498db), Green (#2ecc71), Red (#e74c3c), Purple (#9b59b6)- Fields: Course Code, Name, Description, Credits, Teacher

- **Features:** Gradient backgrounds, hover effects, modal dialogs

- **Layout:** CardLayout for navigation, JTables for data display### 6. Profile Management

- **File**: `ProfilePanel.java`

---- View personal information

- Edit profile details

## 📝 For Presentation- Update password

- Role-specific information display

**Show:**

1. Login system with role-based access---

2. Admin managing students/teachers

3. Profile editing and password change## 📚 Code Explanation

4. Beautiful UI with gradient design

5. Database structure and relationships### Architecture Overview



**Highlight:**The application follows a **layered architecture** with clear separation of concerns:

- Clean code organization with packages

- DAO pattern for data access1. **Presentation Layer** (`ui/` package)

- Auto user creation on add   - Handles all user interface components

- Transaction-based operations   - Uses Java Swing for GUI

- Role-specific dashboards   - Displays data and captures user input



---2. **Business Logic Layer** (`dao/` package)

   - Contains Data Access Objects

## 🔧 Troubleshooting   - Implements CRUD operations

   - Manages database transactions

**Database connection error?**

- Check MySQL is running3. **Data Layer** (`models/` package)

- Verify password in `DatabaseConnection.java`   - Plain Old Java Objects (POJOs)

   - Represents database entities

**Can't compile?**   - Contains getters and setters

- Ensure JDK is installed

- Check mysql-connector-j-9.5.0.jar is present4. **Database Layer** (`database/` package)

   - Manages database connections

**Login fails?**   - Connection pooling

- Database setup complete? Run `setup_database.sql`   - JDBC configuration

- Check users table: `SELECT * FROM users;`

### Key Classes Explained

---

#### 1. Main.java

## 🚀 Future Enhancements```java

// Entry point of the application

- Password encryption (BCrypt)// Tests database connection

- Advanced search/filters// Sets look and feel

- Grade management// Launches login window

- PDF report generation```

- Email notifications

- Attendance tracking#### 2. DatabaseConnection.java

```java

---// Singleton pattern for database connection

// Methods:

## 📄 License// - getConnection(): Returns active connection

// - closeConnection(): Closes the connection

Educational project - Free to use and modify// - testConnection(): Tests if connection works

```

---

#### 3. User.java (Model)

Made with ☕ and Java```java

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
