# 🚀 YOUR QUICK START GUIDE - DO THIS NOW!

## ✅ STEP 1: JDBC Driver - DONE! ✓
The MySQL connector JAR file is now in your project folder!

---

## 🗄️ STEP 2: Setup MySQL Database (DO THIS NEXT)

### Option A: If you have MySQL installed

Open PowerShell and run:
```powershell
mysql -u root -p
```
Enter your MySQL password when prompted.

### Option B: If you have XAMPP

1. Open XAMPP Control Panel
2. Click "Start" next to MySQL
3. Click "Shell" button
4. Type: `mysql -u root -p`
5. Press Enter (password is usually empty)

### Then run these commands ONE BY ONE:

```sql
CREATE DATABASE student_info_system;
```

```sql
USE student_info_system;
```

```sql
SOURCE "C:\\Users\\Kislay\\OneDrive\\Desktop\\Java lab\\schema.sql";
```

```sql
SHOW TABLES;
```
You should see 5 tables!

```sql
SELECT * FROM users;
```
You should see 3 users including 'admin'!

---

## ⚙️ STEP 3: Configure Database Password

Open this file in any text editor:
```
database\DatabaseConnection.java
```

Find line 13 (looks like this):
```java
private static final String PASSWORD = "";
```

Change it to YOUR MySQL password:
```java
private static final String PASSWORD = "your_actual_password";
```

Examples:
- If your MySQL password is "root123": `private static final String PASSWORD = "root123";`
- If using XAMPP (no password): `private static final String PASSWORD = "";`

**SAVE THE FILE!**

---

## 🔨 STEP 4: Compile the Project

Open PowerShell in your project folder and run:

```powershell
javac -d bin -cp "mysql-connector-j-9.5.0.jar" Main.java database\DatabaseConnection.java models\User.java models\Student.java models\Teacher.java models\Course.java dao\UserDAO.java dao\StudentDAO.java dao\TeacherDAO.java dao\CourseDAO.java ui\LoginFrame.java ui\DashboardFrame.java ui\StudentManagementPanel.java ui\TeacherManagementPanel.java ui\CourseManagementPanel.java ui\ProfilePanel.java
```

Or simply double-click: **compile.bat**

### ✓ Success looks like:
- No error messages
- A `bin` folder is created
- Contains .class files

---

## 🚀 STEP 5: Run the Application!

In PowerShell, run:
```powershell
java -cp "bin;mysql-connector-j-9.5.0.jar" Main
```

Or simply double-click: **run.bat**

### ✓ Success looks like:
Console shows:
```
Testing database connection...
Database connected successfully!
Student Information System started successfully!
Default login credentials:
Username: admin
Password: admin123
```

And a beautiful login window appears!

---

## 🎯 STEP 6: Login and Test

1. **Login:**
   - Username: `admin`
   - Password: `admin123`

2. **Click "👨‍🎓 Students"**
   - See the students table
   - Click "Add Student"
   - Fill the form
   - Click Save

3. **Test other features:**
   - Teachers
   - Courses
   - Profile

---

## 🎉 YOU'RE DONE!

If you can see the login window and login successfully, **YOUR PROJECT IS WORKING!**

---

## 🆘 Quick Troubleshooting

### "java is not recognized"
- Java not installed or not in PATH
- Install Java JDK from: https://www.oracle.com/java/technologies/downloads/

### "Cannot connect to database"
- MySQL not running - start it!
- Wrong password in DatabaseConnection.java
- Database not created - run schema.sql again

### "Table 'users' doesn't exist"
- Run schema.sql in MySQL
- Make sure you ran: `USE student_info_system;`

### "ClassNotFoundException"
- JAR file not found
- Make sure mysql-connector-j-9.5.0.jar is in project folder
- Check compilation command includes the JAR

---

## 📞 Need More Help?

Read these files in order:
1. **STEP_BY_STEP_SETUP.md** - Detailed guide
2. **CHECKLIST.md** - Step-by-step checklist
3. **SETUP_GUIDE.md** - Quick reference

---

**Current Status:**
- ✅ Java files created
- ✅ JDBC driver extracted
- ⏳ Need to setup MySQL database
- ⏳ Need to configure password
- ⏳ Need to compile
- ⏳ Need to run

**Next:** Setup MySQL database (Step 2 above)

**Good luck! 🚀**
