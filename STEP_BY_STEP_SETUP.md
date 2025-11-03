# 🚀 COMPLETE SETUP & RUN GUIDE

## **Follow These Exact Steps to Get Your Project Running**

---

## ✅ **STEP 1: Verify Java Installation**

### Check if Java is installed:
```powershell
java -version
javac -version
```

### ✓ Expected Output:
```
java version "1.8.0_xxx" or higher
javac version "1.8.0_xxx" or higher
```

### ❌ If Not Installed:
1. Download Java JDK from: https://www.oracle.com/java/technologies/downloads/
2. Install it
3. Verify installation again

---

## ✅ **STEP 2: Extract MySQL JDBC Driver**

### You already have the connector file!
I can see `mysql-connector-j-9.5.0.tar.gz` in your folder.

### Extract the JAR file:

**Option A - Using 7-Zip (Recommended):**
1. Download 7-Zip from: https://www.7-zip.org/
2. Right-click `mysql-connector-j-9.5.0.tar.gz`
3. Select "7-Zip → Extract Here"
4. It will create a folder, inside you'll find `mysql-connector-j-9.5.0.jar`
5. Copy that JAR file to your main project folder

**Option B - Using Windows Built-in:**
1. Rename file to `.tar` (remove .gz)
2. Extract the `.tar` file
3. Find the JAR file inside
4. Copy to your main folder

**Option C - Download ZIP version:**
1. Go to: https://dev.mysql.com/downloads/connector/j/
2. Select "Platform Independent (ZIP)"
3. Download and extract
4. Copy the JAR file to your project folder

### ✓ After extraction, you should have:
```
Java lab/
├── mysql-connector-j-9.5.0.jar  ← This file!
├── Main.java
├── schema.sql
└── ...
```

---

## ✅ **STEP 3: Install and Start MySQL**

### Check if MySQL is installed:
```powershell
mysql --version
```

### If Not Installed:

**Option A - Install MySQL (Recommended):**
1. Download: https://dev.mysql.com/downloads/installer/
2. Choose "MySQL Installer for Windows"
3. Install MySQL Server
4. Remember the root password you set!

**Option B - Install XAMPP (Easier):**
1. Download: https://www.apachefriends.org/
2. Install XAMPP
3. Open XAMPP Control Panel
4. Click "Start" next to MySQL

---

## ✅ **STEP 4: Create Database**

### Open MySQL Command Line:

**If using MySQL:**
- Start Menu → MySQL → MySQL Command Line Client
- Enter your root password

**If using XAMPP:**
- Open XAMPP Control Panel
- Click "Shell" button
- Type: `mysql -u root -p`
- Press Enter (default password is empty)

### Run these commands one by one:

```sql
CREATE DATABASE student_info_system;
```

```sql
USE student_info_system;
```

```sql
SOURCE "C:\\Users\\Kislay\\OneDrive\\Desktop\\Java lab\\schema.sql";
```
*(Note: Use double backslashes in the path)*

### Verify it worked:
```sql
SHOW TABLES;
```

### ✓ Expected Output:
You should see 5 tables:
- users
- students
- teachers
- courses
- enrollments

### Check sample data:
```sql
SELECT * FROM users;
```

You should see 3 users including 'admin'.

---

## ✅ **STEP 5: Configure Database Password**

### Open this file:
```
database\DatabaseConnection.java
```

### Find line 13 (around line 13):
```java
private static final String PASSWORD = "";
```

### Update with YOUR MySQL password:
```java
private static final String PASSWORD = "your_mysql_password_here";
```

### Example:
If your MySQL password is "root123", it should be:
```java
private static final String PASSWORD = "root123";
```

### If using XAMPP:
Usually the password is empty, so leave it as:
```java
private static final String PASSWORD = "";
```

### Save the file!

---

## ✅ **STEP 6: Compile the Project**

### Option A - Using the batch file (Easiest):
```powershell
compile.bat
```

### Option B - Manual compilation:
```powershell
# Create bin directory
mkdir bin

# Compile all files
javac -d bin -cp "mysql-connector-j-9.5.0.jar" Main.java database\DatabaseConnection.java models\*.java dao\*.java ui\*.java
```

### ✓ Expected Result:
- No error messages
- `bin` folder created with .class files

### ❌ If you get errors:
1. **"cannot find symbol" or "package does not exist"**
   - Make sure all Java files are in correct folders
   - Check that JDBC driver JAR is in the project folder

2. **"javac is not recognized"**
   - Java JDK not in PATH
   - Use full path: `"C:\Program Files\Java\jdk-xx\bin\javac"`

---

## ✅ **STEP 7: Run the Application**

### Option A - Using the batch file (Easiest):
```powershell
run.bat
```

### Option B - Manual run:
```powershell
java -cp "bin;mysql-connector-j-9.5.0.jar" Main
```

### ✓ Expected Output:
```
Testing database connection...
Database connected successfully!
Student Information System started successfully!
Default login credentials:
Username: admin
Password: admin123
```

Then the login window should appear!

---

## ✅ **STEP 8: Login and Test**

### Login with default credentials:
- **Username:** `admin`
- **Password:** `admin123`

### Test the features:
1. Click "👨‍🎓 Students" - View students
2. Click "Add Student" - Add a new student
3. Try editing and deleting
4. Test Teachers and Courses similarly
5. Check your Profile

---

## 🎯 **Quick Summary of All Steps:**

```
1. ✓ Verify Java is installed (java -version)
2. ✓ Extract mysql-connector-j-9.5.0.jar from .tar.gz file
3. ✓ Install MySQL (or XAMPP)
4. ✓ Create database and run schema.sql
5. ✓ Edit DatabaseConnection.java with your password
6. ✓ Run compile.bat
7. ✓ Run run.bat
8. ✓ Login with admin/admin123
```

---

## 🔧 **Troubleshooting Common Issues**

### Problem: "Cannot connect to database"
**Solutions:**
- Check MySQL is running
- Verify password in DatabaseConnection.java
- Test connection: `mysql -u root -p`

### Problem: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solutions:**
- Make sure mysql-connector JAR is extracted
- Verify JAR file is in project root folder
- Check compile command includes the JAR

### Problem: "Table 'users' doesn't exist"
**Solutions:**
- Run schema.sql again in MySQL
- Make sure you're using the right database:
  ```sql
  USE student_info_system;
  SHOW TABLES;
  ```

### Problem: "Login fails with admin/admin123"
**Solutions:**
- Check if schema.sql was run successfully
- Verify users table has data:
  ```sql
  SELECT * FROM users;
  ```

---

## 📁 **Your Project Structure Should Look Like:**

```
Java lab/
│
├── mysql-connector-j-9.5.0.jar  ← JDBC Driver (extract this!)
├── Main.java
├── schema.sql
├── compile.bat
├── run.bat
├── setup.bat                     ← NEW! Run this first
│
├── bin/                          ← Created after compilation
│   ├── Main.class
│   ├── database/
│   ├── models/
│   ├── dao/
│   └── ui/
│
├── database/
│   └── DatabaseConnection.java
│
├── models/
│   ├── User.java
│   ├── Student.java
│   ├── Teacher.java
│   └── Course.java
│
├── dao/
│   ├── UserDAO.java
│   ├── StudentDAO.java
│   ├── TeacherDAO.java
│   └── CourseDAO.java
│
└── ui/
    ├── LoginFrame.java
    ├── DashboardFrame.java
    ├── StudentManagementPanel.java
    ├── TeacherManagementPanel.java
    ├── CourseManagementPanel.java
    └── ProfilePanel.java
```

---

## 🎬 **Video Tutorial Steps (If you want to record):**

1. **Show Java version** - `java -version`
2. **Show MySQL is running** - Task Manager or XAMPP
3. **Show database exists** - MySQL command line
4. **Show the JAR file** - In file explorer
5. **Run compile.bat** - Show compilation
6. **Run run.bat** - Show application starting
7. **Login** - Show login screen
8. **Demo features** - Add student, view tables
9. **Show code** - Open one Java file

---

## 🎓 **For VS Code Users:**

If you're using VS Code, you can also:

1. Install Java Extension Pack
2. Open the project folder
3. VS Code will detect Java project
4. Right-click Main.java → Run

---

## 💡 **Next After Running:**

Once the app is running:
1. ✅ Test all features
2. ✅ Read PRESENTATION_SCRIPT.md
3. ✅ Practice your demo
4. ✅ Prepare for questions

---

## 🆘 **Still Having Issues?**

If you're stuck on any step:
1. Check the error message carefully
2. Verify each prerequisite is installed
3. Make sure all files are in correct locations
4. Check MySQL is running
5. Verify database was created successfully

---

## ✅ **You're Ready When:**

- [ ] Java is installed and working
- [ ] MySQL JDBC JAR is extracted and in project folder
- [ ] MySQL is running
- [ ] Database `student_info_system` exists with 5 tables
- [ ] DatabaseConnection.java has correct password
- [ ] Project compiles without errors (compile.bat works)
- [ ] Application runs and login window appears
- [ ] You can login with admin/admin123

---

**🎉 Once all checks pass, you're ready to present your project!**

**Good luck! 🚀**
