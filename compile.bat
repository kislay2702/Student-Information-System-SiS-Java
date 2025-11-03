@echo off
echo ==========================================
echo  Student Information System - Compiler
echo ==========================================
echo.

REM Check if mysql-connector-java jar exists
if not exist "mysql-connector-java-8.0.33.jar" (
    echo ERROR: mysql-connector-java-8.0.33.jar not found!
    echo Please download the MySQL JDBC driver and place it in this directory.
    echo Download from: https://dev.mysql.com/downloads/connector/j/
    echo.
    pause
    exit
)

echo [1/3] Cleaning old compiled files...
if exist "bin" rmdir /s /q bin
mkdir bin

echo [2/3] Compiling Java files...
javac -d bin -cp "mysql-connector-java-8.0.33.jar" ^
    Main.java ^
    database\DatabaseConnection.java ^
    models\User.java ^
    models\Student.java ^
    models\Teacher.java ^
    models\Course.java ^
    dao\UserDAO.java ^
    dao\StudentDAO.java ^
    dao\TeacherDAO.java ^
    dao\CourseDAO.java ^
    ui\LoginFrame.java ^
    ui\DashboardFrame.java ^
    ui\StudentManagementPanel.java ^
    ui\TeacherManagementPanel.java ^
    ui\CourseManagementPanel.java ^
    ui\ProfilePanel.java

if %errorlevel% neq 0 (
    echo.
    echo Compilation FAILED! Please check the errors above.
    pause
    exit
)

echo [3/3] Compilation successful!
echo.
echo ==========================================
echo  Ready to run!
echo ==========================================
echo.

pause
