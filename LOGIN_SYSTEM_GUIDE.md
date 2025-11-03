# 🔐 Login System - Quick Reference Guide

## Overview
All users can now login with their credentials and access role-specific features!

---

## 🔑 Default Passwords by Role

### **Students**
- **Default Password:** `student123`
- When you add a new student, they automatically get:
  - Username: First part of their email (e.g., john.doe@school.com → username: john.doe)
  - Password: `student123`

### **Teachers**
- **Default Password:** `teacher123`
- When you add a new teacher, they automatically get:
  - Username: First part of their email (e.g., smith@school.com → username: smith)
  - Password: `teacher123`

### **Admin**
- **Username:** `admin`
- **Default Password:** `admin123`

---

## 👥 What Each Role Can See

### 📊 **ADMIN Dashboard**
- ✅ **Home** - Welcome screen
- ✅ **Students** - Manage all students (Add, Edit, Delete, Search)
- ✅ **Teachers** - Manage all teachers (Add, Edit, Delete, Search)
- ✅ **Courses** - Manage all courses (Add, Edit, Delete, Search)
- ✅ **Profile** - View/Edit admin profile & change password

### 👨‍🎓 **STUDENT Dashboard**
- ✅ **Home** - Welcome screen
- ✅ **My Courses** - View enrolled courses with grades and teacher info
- ✅ **My Teachers** - View all teachers and their details
- ✅ **Profile** - View/Edit student profile & change password

### 👨‍🏫 **TEACHER Dashboard**
- ✅ **Home** - Welcome screen
- ✅ **My Courses** - View courses they teach
- ✅ **My Students** - View all students enrolled
- ✅ **Profile** - View/Edit teacher profile & change password

---

## 🔄 How to Change Password

1. Login with your current credentials
2. Click on **Profile** tab
3. Click **Change Password** button (purple button)
4. Enter:
   - Current Password
   - New Password
   - Confirm New Password
5. Click **OK**

✅ Password changed successfully!

---

## 📝 Step-by-Step: Adding a New Student

1. Login as **Admin** (admin/admin123)
2. Click **Students** tab
3. Click **Add Student** button (blue)
4. Fill in the form:
   - First Name
   - Last Name
   - Email (e.g., jane.smith@school.com)
   - Phone
   - Date of Birth (YYYY-MM-DD format)
   - Enrollment Date (YYYY-MM-DD format)
5. Click **Save**

✅ **Automatic User Account Created!**
- Username: `jane.smith` (from email)
- Password: `student123` (default)

---

## 📝 Step-by-Step: Adding a New Teacher

1. Login as **Admin** (admin/admin123)
2. Click **Teachers** tab
3. Click **Add Teacher** button (blue)
4. Fill in the form:
   - First Name
   - Last Name
   - Email (e.g., dr.johnson@school.com)
   - Phone
   - Department
   - Specialization
   - Hire Date (YYYY-MM-DD format)
5. Click **Save**

✅ **Automatic User Account Created!**
- Username: `dr.johnson` (from email)
- Password: `teacher123` (default)

---

## 🎯 Testing the System

### Test as Admin:
```
Username: admin
Password: admin123
```
- You can manage students, teachers, and courses
- Full administrative access

### Test as Existing Student:
```
Username: john.doe
Password: pass123
```
- View courses and teachers
- Edit your profile
- Change your password

### Test as Existing Teacher:
```
Username: dr.smith
Password: teach123
```
- View your courses
- View students
- Edit your profile
- Change your password

### Test as New Student (after you add one):
```
Username: [email prefix]
Password: student123
```

### Test as New Teacher (after you add one):
```
Username: [email prefix]
Password: teacher123
```

---

## 🔒 Security Features

✅ **Automatic Account Creation** - Every student/teacher gets a login account
✅ **Role-Based Access** - Different dashboards for different roles
✅ **Password Change** - Users can change their own passwords
✅ **Transaction Safety** - Database transactions ensure data integrity
✅ **Error Handling** - Rollback on failures

---

## 💡 Important Notes

1. **Email is Required** - Email is used to generate the username
2. **Unique Emails** - Each user must have a unique email address
3. **Default Passwords** - All new users get default passwords based on role
4. **First Login** - Users should change their password after first login
5. **Profile Updates** - Users can update their own information
6. **Admin Control** - Only admins can add/edit/delete users

---

## 🆘 Common Issues

### "Duplicate entry" error when adding student/teacher
- **Problem:** Email already exists in the system
- **Solution:** Use a different email address

### Cannot login with new student/teacher
- **Problem:** Check username format (email prefix only)
- **Solution:** If email is john.doe@school.com, username is just `john.doe`

### Forgot password
- **Problem:** No automated password reset (simplified system)
- **Solution:** Admin can update the password in the database directly

---

## 📁 File Changes Summary

### Modified Files:
1. `dao/StudentDAO.java` - Auto-creates user account with student123 password
2. `dao/TeacherDAO.java` - Auto-creates user account with teacher123 password
3. `dao/UserDAO.java` - Added changePassword() method
4. `ui/DashboardFrame.java` - Role-specific navigation tabs
5. `ui/ProfilePanel.java` - Added Change Password button

### New Files:
1. `ui/MyCoursesPanel.java` - Shows courses for students/teachers
2. `ui/MyTeachersPanel.java` - Shows teachers for students
3. `ui/MyStudentsPanel.java` - Shows students for teachers

---

## 🎉 Ready to Use!

The system is now fully functional with:
- ✅ Automatic user account creation
- ✅ Role-based dashboards
- ✅ Password management
- ✅ Profile editing
- ✅ Secure access control

**Start by logging in as admin and adding some students and teachers!**
