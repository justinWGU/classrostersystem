# Class Roster System

## Author
Justin Ortiz

## Description
My Class Roster System is a web application that is designed for school 
administrators, teachers, and students to perform functions related to class
roster management. 


## Features
- Admins can perform CRUD operations on teachers, courses, and students as well 
as assign courses to teachers and students.
- Teachers can take attendance, post grades, and view their courses and students.
- Students can view grades, attendance history, and assigned courses. 

## Installation Instructions
### Prerequisites
- JDK version 17 or higher 
- Spring Framework
- MySQL
- Maven
- Git
### Steps:
1. Clone the repository:
    ```
    git clone https://github.com/justinWGU/classrostersystem.git
    cd /classrostersystem
   Set up the database:

Create a MySQL database.
Run the SQL script located in /src/main/resources/db to set up the database structure.
Configure application properties:

Update src/main/resources/application.properties with your local MySQL credentials:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
Build the project:

bash
Copy code
mvn clean install
Run the Spring Boot application:

bash
Copy code
mvn spring-boot:run
Access the application:

Open your browser at http://localhost:8080 or use Postman for API testing.

## Technologies
- Spring Boot 
- Maven
- Thymeleaf
- Java
- IntelliJ
- Git
- GitHub
- HTML
- CSS

## License
- GNU GENERAL PUBLIC LICENSE
