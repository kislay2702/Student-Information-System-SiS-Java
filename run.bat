@echo off
echo ==========================================
echo  Student Information System - Launcher
echo ==========================================
echo.

REM Check if compiled
if not exist "bin" (
    echo ERROR: Project not compiled yet!
    echo Please run compile.bat first.
    echo.
    pause
    exit
)

REM Check if mysql-connector jar exists
if not exist "mysql-connector-java-8.0.33.jar" (
    echo ERROR: mysql-connector-java-8.0.33.jar not found!
    echo.
    pause
    exit
)

echo Starting Student Information System...
echo.
echo Default Login Credentials:
echo Username: admin
echo Password: admin123
echo.
echo ==========================================
echo.

java -cp "bin;mysql-connector-java-8.0.33.jar" Main

echo.
echo Application closed.
pause
