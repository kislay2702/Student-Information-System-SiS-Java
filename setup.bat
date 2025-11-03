@echo off
echo ========================================
echo  Student Information System Setup
echo ========================================
echo.
echo This script will guide you through the setup process.
echo.

REM Step 1: Check Java
echo [Step 1/5] Checking Java installation...
java -version > nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed!
    echo Please install Java JDK 8 or higher from:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
    exit
)
echo ✓ Java is installed
echo.

REM Step 2: Extract MySQL Connector
echo [Step 2/5] Checking MySQL JDBC Driver...
if exist "mysql-connector-j-9.5.0.tar.gz" (
    echo Found mysql-connector-j-9.5.0.tar.gz
    echo Please extract this file manually and place the .jar file here.
    echo Or download a .zip version from: https://dev.mysql.com/downloads/connector/j/
    echo.
    echo Looking for existing JAR files...
    dir /b *.jar > nul 2>&1
    if %errorlevel% neq 0 (
        echo WARNING: No .jar file found!
        echo Please extract the MySQL connector and place the JAR file in this folder.
        pause
    ) else (
        echo ✓ JAR file found
    )
) else (
    echo WARNING: MySQL connector not found!
    echo Please download from: https://dev.mysql.com/downloads/connector/j/
    pause
)
echo.

REM Step 3: Database Setup Instructions
echo [Step 3/5] Database Setup Instructions
echo ========================================
echo.
echo Please run these MySQL commands:
echo.
echo 1. Open MySQL Command Line or MySQL Workbench
echo 2. Run these commands:
echo.
echo    CREATE DATABASE student_info_system;
echo    USE student_info_system;
echo    SOURCE "C:\Users\Kislay\OneDrive\Desktop\Java lab\schema.sql";
echo.
echo 3. Verify tables were created:
echo    SHOW TABLES;
echo.
echo Have you completed the database setup? (Press any key when done)
pause > nul
echo.

REM Step 4: Configure Database Connection
echo [Step 4/5] Database Configuration
echo ========================================
echo.
echo Please edit: database\DatabaseConnection.java
echo.
echo Update line 13 with your MySQL password:
echo    private static final String PASSWORD = "your_mysql_password";
echo.
echo Have you updated the password? (Press any key when done)
pause > nul
echo.

REM Step 5: Ready to compile
echo [Step 5/5] Ready to Compile!
echo ========================================
echo.
echo Setup is complete! Next steps:
echo.
echo 1. Run compile.bat to compile the project
echo 2. Run run.bat to start the application
echo 3. Login with: username=admin, password=admin123
echo.
echo ========================================
echo.
pause
