CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;

CREATE TABLE IF NOT EXISTS students (
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(100) NOT NULL,
    course VARCHAR(50)  NOT NULL,
    marks  DOUBLE       NOT NULL,
    email  VARCHAR(100) UNIQUE
);

INSERT INTO students (name, course, marks, email) VALUES
('Aman Singh',  'BCA',  82.5, 'aman@example.com'),
('Simran Kaur', 'BCA',  91.0, 'simran@example.com'),
('Rohit Verma', 'BTech', 74.0, 'rohit@example.com'),
('Neha Sharma', 'BTech', 88.5, 'neha@example.com'),
('Gurpreet',    'MCA',  67.0, 'gurpreet@example.com');
