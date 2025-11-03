# System Architecture Diagrams

## 1. Application Flow Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                        APPLICATION START                         │
│                          (Main.java)                            │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                   Test Database Connection                       │
│                 (DatabaseConnection.java)                        │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                      LOGIN SCREEN                                │
│                    (LoginFrame.java)                             │
│                                                                   │
│  ┌──────────────────────────────────────────────────────┐      │
│  │  Username: [_______________]                         │      │
│  │  Password: [_______________]                         │      │
│  │                  [  Login  ]                         │      │
│  └──────────────────────────────────────────────────────┘      │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │  Authenticate User   │
              │    (UserDAO.java)    │
              └──────────┬───────────┘
                         │
           ┌─────────────┴─────────────┐
           │                           │
      ✓ Success                   ✗ Failed
           │                           │
           ▼                           ▼
   ┌───────────────┐         ┌────────────────┐
   │   DASHBOARD   │         │  Error Message │
   │   (Dashboard  │         │  "Login Failed"│
   │    Frame)     │         └────────────────┘
   └───────┬───────┘
           │
           ├─────────────────┬─────────────────┬─────────────────┐
           │                 │                 │                 │
           ▼                 ▼                 ▼                 ▼
    ┌──────────┐      ┌──────────┐    ┌──────────┐      ┌──────────┐
    │ Students │      │ Teachers │    │ Courses  │      │ Profile  │
    │  Panel   │      │  Panel   │    │  Panel   │      │  Panel   │
    └──────────┘      └──────────┘    └──────────┘      └──────────┘
```

---

## 2. Database Schema Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         DATABASE SCHEMA                          │
│                    (student_info_system)                         │
└─────────────────────────────────────────────────────────────────┘

┌──────────────────┐
│     USERS        │
├──────────────────┤
│ PK user_id       │◄──────┐
│    username      │       │ 1:1
│    password      │       │
│    role          │       │
│    email         │       │
└──────────────────┘       │
                           │
         ┌─────────────────┼─────────────────┐
         │                 │                 │
         ▼                 │                 ▼
┌──────────────────┐       │        ┌──────────────────┐
│    STUDENTS      │       │        │    TEACHERS      │
├──────────────────┤       │        ├──────────────────┤
│ PK student_id    │       │        │ PK teacher_id    │
│ FK user_id       │───────┘        │ FK user_id       │──────┐
│    first_name    │                │    first_name    │      │
│    last_name     │                │    last_name     │      │
│    email         │                │    email         │      │
│    phone         │                │    phone         │      │
│    dob           │                │    department    │      │
│    address       │                │    specialization│      │
│    enroll_date   │                │    hire_date     │      │
└────────┬─────────┘                └──────────────────┘      │
         │                                                     │
         │                                                     │ 1:N
         │ M:N                                                 │
         │                                                     ▼
         │                          ┌──────────────────┐
         │                          │    COURSES       │
         │                          ├──────────────────┤
         │                          │ PK course_id     │
         │                          │    course_code   │
         │                          │    course_name   │
         │                          │    description   │
         │                          │    credits       │
         │                          │ FK teacher_id    │◄──────┘
         │                          └────────┬─────────┘
         │                                   │
         │                                   │
         │                                   │
         │               ┌───────────────────┘
         │               │
         ▼               ▼
┌──────────────────────────────┐
│      ENROLLMENTS             │
├──────────────────────────────┤
│ PK enrollment_id             │
│ FK student_id                │
│ FK course_id                 │
│    enrollment_date           │
│    grade                     │
└──────────────────────────────┘
```

---

## 3. Class Structure Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                      CLASS HIERARCHY                             │
└─────────────────────────────────────────────────────────────────┘

                        ┌────────────┐
                        │  Main.java │
                        └──────┬─────┘
                               │
                               │ creates
                               ▼
                      ┌─────────────────┐
                      │  LoginFrame     │
                      │  extends JFrame │
                      └────────┬────────┘
                               │
                               │ creates on success
                               ▼
                      ┌─────────────────┐
                      │ DashboardFrame  │
                      │  extends JFrame │
                      └────────┬────────┘
                               │
                               │ contains
            ┌──────────────────┼──────────────────┐
            │                  │                  │
            ▼                  ▼                  ▼
  ┌───────────────────┐ ┌───────────────┐ ┌───────────────┐
  │ StudentManagement │ │ TeacherManage │ │ CourseManage  │
  │ Panel             │ │ ment Panel    │ │ ment Panel    │
  │ extends JPanel    │ │ extends JPanel│ │ extends JPanel│
  └─────────┬─────────┘ └───────┬───────┘ └───────┬───────┘
            │                   │                  │
            │ uses              │ uses             │ uses
            ▼                   ▼                  ▼
  ┌───────────────┐   ┌───────────────┐   ┌───────────────┐
  │  StudentDAO   │   │  TeacherDAO   │   │  CourseDAO    │
  └───────┬───────┘   └───────┬───────┘   └───────┬───────┘
          │                   │                    │
          │ uses              │ uses               │ uses
          ▼                   ▼                    ▼
  ┌───────────────┐   ┌───────────────┐   ┌───────────────┐
  │    Student    │   │    Teacher    │   │    Course     │
  │  (Model)      │   │   (Model)     │   │   (Model)     │
  └───────────────┘   └───────────────┘   └───────────────┘

          └───────────────────┬───────────────────┘
                              │
                              │ all use
                              ▼
                   ┌──────────────────────┐
                   │ DatabaseConnection   │
                   │   (Singleton)        │
                   └──────────┬───────────┘
                              │
                              │ connects to
                              ▼
                      ┌────────────────┐
                      │  MySQL Server  │
                      │    Database    │
                      └────────────────┘
```

---

## 4. MVC Pattern Implementation

```
┌─────────────────────────────────────────────────────────────────┐
│                      MVC ARCHITECTURE                            │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                        VIEW LAYER                                │
│              (ui/ package - User Interface)                      │
├─────────────────────────────────────────────────────────────────┤
│  LoginFrame | DashboardFrame | StudentManagementPanel           │
│  TeacherManagementPanel | CourseManagementPanel | ProfilePanel  │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         │ User Actions
                         │ (Button Clicks, Form Submits)
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                     CONTROLLER LAYER                             │
│           (Event Handlers + DAO methods)                         │
├─────────────────────────────────────────────────────────────────┤
│  UserDAO | StudentDAO | TeacherDAO | CourseDAO                  │
│                                                                   │
│  Methods:                                                        │
│  - authenticateUser()                                            │
│  - getAllStudents()                                              │
│  - addStudent()                                                  │
│  - updateStudent()                                               │
│  - deleteStudent()                                               │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         │ Data Operations
                         │ (CRUD)
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                      MODEL LAYER                                 │
│                (models/ package - Data)                          │
├─────────────────────────────────────────────────────────────────┤
│  User.java | Student.java | Teacher.java | Course.java          │
│                                                                   │
│  Contains:                                                       │
│  - Private fields                                                │
│  - Getters and Setters                                          │
│  - toString() methods                                           │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         │ Mapped to
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                      DATABASE                                    │
│               (MySQL - Persistence)                              │
├─────────────────────────────────────────────────────────────────┤
│  Tables: users, students, teachers, courses, enrollments        │
└─────────────────────────────────────────────────────────────────┘
```

---

## 5. User Interaction Flow - Student Management

```
┌─────────────────────────────────────────────────────────────────┐
│                  STUDENT MANAGEMENT FLOW                         │
└─────────────────────────────────────────────────────────────────┘

USER                  VIEW                CONTROLLER           DATABASE
 │                     │                      │                    │
 │  Clicks "Students" │                      │                    │
 ├───────────────────►│                      │                    │
 │                    │  loadStudents()      │                    │
 │                    ├─────────────────────►│                    │
 │                    │                      │ getAllStudents()   │
 │                    │                      ├───────────────────►│
 │                    │                      │                    │
 │                    │                      │  SELECT * FROM...  │
 │                    │                      │◄───────────────────┤
 │                    │  List<Student>       │                    │
 │                    │◄─────────────────────┤                    │
 │  Views Table       │                      │                    │
 │◄───────────────────┤                      │                    │
 │                    │                      │                    │
 │  Clicks "Add"      │                      │                    │
 ├───────────────────►│                      │                    │
 │                    │  Show Dialog         │                    │
 │◄───────────────────┤                      │                    │
 │                    │                      │                    │
 │  Fills Form        │                      │                    │
 │  Clicks "Save"     │                      │                    │
 ├───────────────────►│                      │                    │
 │                    │  addStudent(student) │                    │
 │                    ├─────────────────────►│                    │
 │                    │                      │ INSERT INTO...     │
 │                    │                      ├───────────────────►│
 │                    │                      │                    │
 │                    │                      │  Success           │
 │                    │                      │◄───────────────────┤
 │                    │  Success Message     │                    │
 │                    │◄─────────────────────┤                    │
 │  "Student Added"   │                      │                    │
 │◄───────────────────┤                      │                    │
 │                    │                      │                    │
```

---

## 6. File Dependencies Map

```
Main.java
  │
  ├──► database/DatabaseConnection.java
  │     └──► Requires: MySQL JDBC Driver
  │
  └──► ui/LoginFrame.java
        │
        ├──► dao/UserDAO.java
        │     ├──► models/User.java
        │     └──► database/DatabaseConnection.java
        │
        └──► ui/DashboardFrame.java
              │
              ├──► ui/StudentManagementPanel.java
              │     ├──► dao/StudentDAO.java
              │     │     ├──► models/Student.java
              │     │     └──► database/DatabaseConnection.java
              │     └──► StudentDialog (inner class)
              │
              ├──► ui/TeacherManagementPanel.java
              │     ├──► dao/TeacherDAO.java
              │     │     ├──► models/Teacher.java
              │     │     └──► database/DatabaseConnection.java
              │     └──► TeacherDialog (inner class)
              │
              ├──► ui/CourseManagementPanel.java
              │     ├──► dao/CourseDAO.java
              │     │     ├──► models/Course.java
              │     │     └──► database/DatabaseConnection.java
              │     ├──► dao/TeacherDAO.java
              │     └──► CourseDialog (inner class)
              │
              └──► ui/ProfilePanel.java
                    ├──► dao/UserDAO.java
                    ├──► dao/StudentDAO.java
                    ├──► dao/TeacherDAO.java
                    └──► models/User.java
```

---

## 7. Package Structure

```
Java lab/
│
├── (default package)
│   └── Main.java ...................... Application entry point
│
├── database/
│   └── DatabaseConnection.java ........ Manages DB connections
│
├── models/
│   ├── User.java ...................... User data model
│   ├── Student.java ................... Student data model
│   ├── Teacher.java ................... Teacher data model
│   └── Course.java .................... Course data model
│
├── dao/
│   ├── UserDAO.java ................... User CRUD operations
│   ├── StudentDAO.java ................ Student CRUD operations
│   ├── TeacherDAO.java ................ Teacher CRUD operations
│   └── CourseDAO.java ................. Course CRUD operations
│
└── ui/
    ├── LoginFrame.java ................ Login window
    ├── DashboardFrame.java ............ Main dashboard
    ├── StudentManagementPanel.java .... Student management UI
    ├── TeacherManagementPanel.java .... Teacher management UI
    ├── CourseManagementPanel.java ..... Course management UI
    └── ProfilePanel.java .............. User profile UI
```

---

## 8. Component Communication

```
┌─────────────────────────────────────────────────────────────────┐
│              HOW COMPONENTS COMMUNICATE                          │
└─────────────────────────────────────────────────────────────────┘

1. USER INTERFACE → DAO
   - UI calls DAO methods
   - Example: studentDAO.getAllStudents()

2. DAO → DATABASE CONNECTION
   - DAO gets connection
   - Example: DatabaseConnection.getConnection()

3. DAO → MODELS
   - DAO creates/populates model objects
   - Example: Student student = new Student()

4. DAO → DATABASE
   - DAO executes SQL queries
   - Example: PreparedStatement.executeQuery()

5. DATABASE → DAO
   - Returns ResultSet
   - DAO extracts data

6. DAO → MODELS
   - Populates model objects from ResultSet
   - Example: student.setName(rs.getString("name"))

7. DAO → UI
   - Returns model objects or lists
   - Example: return List<Student>

8. UI → USER
   - Displays data in tables/forms
   - Example: tableModel.addRow(...)
```

---

## 9. State Management

```
┌─────────────────────────────────────────────────────────────────┐
│                   APPLICATION STATE                              │
└─────────────────────────────────────────────────────────────────┘

┌──────────────────┐
│  Login State     │
│  - No user       │
│  - LoginFrame    │
│    visible       │
└────────┬─────────┘
         │
         │ User logs in
         ▼
┌──────────────────┐
│ Authenticated    │
│  - User object   │
│    stored        │
│  - Dashboard     │
│    opens         │
└────────┬─────────┘
         │
         │ User navigates
         ▼
┌──────────────────┐
│  Active Session  │
│  - Current user  │
│    tracked       │
│  - Panel states  │
│    maintained    │
└────────┬─────────┘
         │
         │ User logs out
         ▼
┌──────────────────┐
│  Logout State    │
│  - User cleared  │
│  - Return to     │
│    LoginFrame    │
└──────────────────┘
```

This visual guide helps understand the system architecture and how components interact!
