-- Setup script for Student Information System
-- This will be run automatically

CREATE DATABASE IF NOT EXISTS student_info_system;
USE student_info_system;

-- Create tables
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    date_of_birth DATE,
    address TEXT,
    enrollment_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS teachers (
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    department VARCHAR(100),
    specialization VARCHAR(100),
    hire_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    description TEXT,
    credits INT,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date DATE,
    grade VARCHAR(5),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-- Insert sample data
INSERT IGNORE INTO users (username, password, role, email) 
VALUES ('admin', 'admin123', 'ADMIN', 'admin@school.com');

INSERT IGNORE INTO users (username, password, role, email) 
VALUES ('john.doe', 'pass123', 'STUDENT', 'john.doe@school.com');

INSERT IGNORE INTO students (user_id, first_name, last_name, email, phone, date_of_birth, address, enrollment_date)
SELECT 2, 'John', 'Doe', 'john.doe@school.com', '1234567890', '2000-05-15', '123 Main St', '2023-09-01'
WHERE NOT EXISTS (SELECT 1 FROM students WHERE user_id = 2);

INSERT IGNORE INTO users (username, password, role, email) 
VALUES ('dr.smith', 'teach123', 'TEACHER', 'smith@school.com');

INSERT IGNORE INTO teachers (user_id, first_name, last_name, email, phone, department, specialization, hire_date)
SELECT 3, 'Robert', 'Smith', 'smith@school.com', '9876543210', 'Computer Science', 'Data Structures', '2020-08-15'
WHERE NOT EXISTS (SELECT 1 FROM teachers WHERE user_id = 3);

INSERT IGNORE INTO courses (course_code, course_name, description, credits, teacher_id)
VALUES ('CS101', 'Introduction to Programming', 'Basic programming concepts using Java', 3, 1);

INSERT IGNORE INTO courses (course_code, course_name, description, credits, teacher_id)
VALUES ('CS201', 'Data Structures', 'Advanced data structures and algorithms', 4, 1);

SELECT 'Database setup complete!' as Status;
