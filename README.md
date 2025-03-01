# JWT Authentication with Spring Boot

This project demonstrates how to implement JSON Web Token (JWT) authentication in a Spring Boot application. It includes user authentication, request authorization, and token validation.

## Features
- User Registration and Login
- JWT Token Generation
- Secure Endpoints with Role-Based Access Control
- Token Validation and Expiry Handling

## Technologies Used
- Java
- Spring Boot
- Spring Security
- JSON Web Token (JWT)
- Maven
- MySQL (or any other database)

## Installation and Setup
### Prerequisites
- Java 17+
- Maven
- MySQL (or any database of choice)

### Steps to Run the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/2000sagarr/jwt-authentication-spring-boot-java.git
   ```
2. Navigate to the project directory:
   ```bash
   cd jwt-authentication-spring-boot-java
   ```
3. Update database configurations in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints
### Authentication
- **POST** `/api/auth/register` - Register a new user
- **POST** `/api/auth/login` - Authenticate user and get JWT token

## JWT Token Usage
Once authenticated, include the JWT token in the `Authorization` header of API requests:
```http
Authorization: Bearer <your_token>
```

## License
This project is open-source and available under the MIT License.

## Author
[Sagar](https://github.com/2000sagarr)

