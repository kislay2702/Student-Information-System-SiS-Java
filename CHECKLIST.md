# ✅ PROJECT SETUP CHECKLIST

Print this and check off each item as you complete it!

---

## 📋 **PRE-SETUP CHECKLIST**

### Software Requirements:
- [ ] Java JDK 8+ installed
  - Test: `java -version` in PowerShell
  - Download: https://www.oracle.com/java/technologies/downloads/

- [ ] MySQL Server installed (or XAMPP)
  - Test: `mysql --version` in PowerShell
  - MySQL: https://dev.mysql.com/downloads/mysql/
  - XAMPP: https://www.apachefriends.org/

- [ ] MySQL JDBC Driver extracted
  - [ ] mysql-connector-j-9.5.0.jar in project folder
  - Extract from the .tar.gz file you have

---

## 🗄️ **DATABASE SETUP CHECKLIST**

### 1. Start MySQL:
- [ ] MySQL service is running
  - Services → MySQL → Started
  - Or XAMPP Control Panel → MySQL → Start

### 2. Open MySQL Command Line:
- [ ] MySQL command line opened
- [ ] Entered root password (or press Enter if using XAMPP)

### 3. Create Database:
```sql
CREATE DATABASE student_info_system;
```
- [ ] Command executed successfully
- [ ] No errors shown

### 4. Select Database:
```sql
USE student_info_system;
```
- [ ] Database selected
- [ ] Shows "Database changed"

### 5. Import Schema:
```sql
SOURCE "C:\\Users\\Kislay\\OneDrive\\Desktop\\Java lab\\schema.sql";
```
- [ ] Schema imported successfully
- [ ] Shows multiple "Query OK" messages

### 6. Verify Tables:
```sql
SHOW TABLES;
```
- [ ] Shows 5 tables:
  - [ ] users
  - [ ] students
  - [ ] teachers
  - [ ] courses
  - [ ] enrollments

### 7. Verify Sample Data:
```sql
SELECT * FROM users;
```
- [ ] Shows 3 users (admin, john.doe, dr.smith)

---

## ⚙️ **CONFIGURATION CHECKLIST**

### 1. Database Connection:
- [ ] Opened `database\DatabaseConnection.java`
- [ ] Found line 13 with PASSWORD
- [ ] Updated password to match your MySQL password
- [ ] Saved the file

Example:
```java
// If your MySQL password is "root123"
private static final String PASSWORD = "root123";

// If using XAMPP (usually no password)
private static final String PASSWORD = "";
```

---

## 🔨 **COMPILATION CHECKLIST**

### 1. File Structure:
- [ ] mysql-connector-j-9.5.0.jar is in root folder
- [ ] All Java files are in correct packages
- [ ] Main.java is in root folder

### 2. Compile Project:

**Option A - Using batch file:**
- [ ] Double-clicked `compile.bat`
- [ ] Saw compilation messages
- [ ] No error messages
- [ ] `bin` folder created

**Option B - Manual:**
```powershell
mkdir bin
javac -d bin -cp "mysql-connector-j-9.5.0.jar" Main.java database\DatabaseConnection.java models\*.java dao\*.java ui\*.java
```
- [ ] Command executed
- [ ] No errors shown
- [ ] .class files in bin folder

### 3. Verify Compilation:
- [ ] bin\Main.class exists
- [ ] bin\database\ has .class files
- [ ] bin\models\ has .class files
- [ ] bin\dao\ has .class files
- [ ] bin\ui\ has .class files

---

## 🚀 **RUN CHECKLIST**

### 1. Start Application:

**Option A - Using batch file:**
- [ ] Double-clicked `run.bat`

**Option B - Manual:**
```powershell
java -cp "bin;mysql-connector-j-9.5.0.jar" Main
```

### 2. Check Console Output:
- [ ] "Testing database connection..."
- [ ] "Database connected successfully!"
- [ ] "Student Information System started successfully!"
- [ ] Shows default credentials

### 3. Login Window:
- [ ] Beautiful login window appears
- [ ] Gradient blue-purple background
- [ ] Username and password fields visible
- [ ] Login button visible

---

## 🧪 **TESTING CHECKLIST**

### 1. Login Test:
- [ ] Entered username: `admin`
- [ ] Entered password: `admin123`
- [ ] Clicked Login button
- [ ] Login successful message appears
- [ ] Dashboard opens

### 2. Dashboard Test:
- [ ] Top bar shows "Welcome, admin (ADMIN)"
- [ ] Left navigation menu visible
- [ ] Welcome panel in center
- [ ] All menu items visible:
  - [ ] 🏠 Home
  - [ ] 👨‍🎓 Students
  - [ ] 👨‍🏫 Teachers
  - [ ] 📚 Courses
  - [ ] 👤 Profile

### 3. Student Management Test:
- [ ] Clicked "👨‍🎓 Students"
- [ ] Table shows students
- [ ] Buttons visible: Add, Edit, Delete, Refresh
- [ ] Clicked "Add Student"
- [ ] Form dialog appears
- [ ] Filled sample data:
  - First Name: Test
  - Last Name: Student
  - Email: test@test.com
  - Phone: 1234567890
  - DOB: 2000-01-01
  - Enrollment: 2024-01-01
- [ ] Clicked Save
- [ ] Success message appears
- [ ] New student in table
- [ ] Selected student and clicked Edit
- [ ] Edit form appears with data
- [ ] Made changes and saved
- [ ] Changes reflected in table

### 4. Teacher Management Test:
- [ ] Clicked "👨‍🏫 Teachers"
- [ ] Table shows teachers
- [ ] Add, Edit, Delete buttons work
- [ ] Can add new teacher
- [ ] Can edit teacher
- [ ] Can delete teacher

### 5. Course Management Test:
- [ ] Clicked "📚 Courses"
- [ ] Table shows courses
- [ ] Can add new course
- [ ] Can assign teacher to course
- [ ] Can edit course
- [ ] Can delete course

### 6. Profile Test:
- [ ] Clicked "👤 Profile"
- [ ] Shows user information
- [ ] Shows role badge
- [ ] Can edit profile fields
- [ ] Update button works
- [ ] Refresh button works

### 7. Logout Test:
- [ ] Clicked Logout button
- [ ] Confirmation dialog appears
- [ ] Clicked Yes
- [ ] Returns to login screen

---

## 🎨 **UI VERIFICATION CHECKLIST**

- [ ] Login screen has gradient background (blue to purple)
- [ ] All buttons have hover effects
- [ ] Tables have proper headers (white text on dark blue)
- [ ] Forms open in modal dialogs
- [ ] Add buttons are green
- [ ] Edit buttons are blue
- [ ] Delete buttons are red
- [ ] Navigation menu has dark background
- [ ] Top bar is dark blue
- [ ] Icons show properly (emojis)

---

## 📝 **DOCUMENTATION CHECKLIST**

- [ ] Read README.md (at least overview)
- [ ] Read SETUP_GUIDE.md
- [ ] Read STEP_BY_STEP_SETUP.md (this file)
- [ ] Glanced at PRESENTATION_SCRIPT.md
- [ ] Know where to find help (INDEX.md)

---

## 🎤 **PRESENTATION PREPARATION CHECKLIST**

- [ ] Application runs smoothly
- [ ] Know how to login
- [ ] Can add a student
- [ ] Can navigate all sections
- [ ] Can explain the code structure
- [ ] Can show one DAO class
- [ ] Can show one Model class
- [ ] Can show one UI class
- [ ] Know default credentials by heart
- [ ] Practiced demo at least once

---

## 🐛 **TROUBLESHOOTING CHECKLIST**

If something doesn't work:

### Database Connection Issues:
- [ ] Verified MySQL is running
- [ ] Checked password in DatabaseConnection.java
- [ ] Database exists: `SHOW DATABASES;`
- [ ] Tables exist: `USE student_info_system; SHOW TABLES;`

### Compilation Issues:
- [ ] JAR file is in correct location
- [ ] All Java files are in correct packages
- [ ] Java version is 8 or higher

### Runtime Issues:
- [ ] Compiled without errors
- [ ] Database has sample data
- [ ] Console shows "Database connected successfully!"

---

## ✅ **FINAL VERIFICATION**

Before presenting, verify:
- [ ] ✅ Can run application without errors
- [ ] ✅ Can login successfully
- [ ] ✅ All CRUD operations work
- [ ] ✅ UI looks professional
- [ ] ✅ No console errors
- [ ] ✅ Database persists data
- [ ] ✅ Can explain code structure
- [ ] ✅ Ready for questions

---

## 🎯 **SUCCESS CRITERIA**

You're 100% ready when:

✅ Application compiles  
✅ Application runs  
✅ Login works  
✅ All features work  
✅ UI looks beautiful  
✅ Database saves data  
✅ You understand the code  
✅ You can explain it  

---

## 🎉 **CONGRATULATIONS!**

If all items are checked, your project is:
- ✅ Complete
- ✅ Working
- ✅ Tested
- ✅ Ready to present

**You're all set! Good luck with your presentation!** 🚀

---

**Date Setup Completed:** _______________

**Time Taken:** _______________

**Notes:** 
_________________________________________
_________________________________________
_________________________________________
