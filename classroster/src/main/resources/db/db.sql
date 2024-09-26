-- Drop the database if it exists and recreate it
DROP DATABASE IF EXISTS classRoster;
CREATE DATABASE classRoster;
USE classRoster;

-- Create the teacher table
CREATE TABLE `teacher` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create the student table
CREATE TABLE `student` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create the course table
CREATE TABLE `course` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) DEFAULT NULL,
                          `teacher_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKsybhlxoejr4j3teomm5u2bx1n` (`teacher_id`),
                          CONSTRAINT `FKsybhlxoejr4j3teomm5u2bx1n` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create the course_student table (many-to-many relationship between course and student)
CREATE TABLE `course_student` (
                                  `course_id` bigint NOT NULL,
                                  `student_id` bigint NOT NULL,
                                  KEY `FK4xxxkt1m6afc9vxp3ryb0xfhi` (`student_id`),
                                  KEY `FKlmj50qx9k98b7li5li74nnylb` (`course_id`),
                                  CONSTRAINT `FK4xxxkt1m6afc9vxp3ryb0xfhi` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
                                  CONSTRAINT `FKlmj50qx9k98b7li5li74nnylb` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;