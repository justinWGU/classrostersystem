# *Class Roster System*

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
    
2. Navigate to the project:
   ``` 
   cd /classrostersystem
      
3. Set up the database:

4. Create a MySQL database.
   To set up the database structure, run the SQL script located in "/src/main/resources/db".

5. Configure application properties:

   Update "src/main/resources/application.properties" with your local MySQL credentials:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=create

7. Build the project:
   ```
   mvn clean install

8. Run the Spring Boot application:
   ```
   mvn spring-boot:run

9. Access the application:

- Open your browser at http://localhost:8080/home

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
