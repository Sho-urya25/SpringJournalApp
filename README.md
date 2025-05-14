# Spring Journal App

The **Spring Journal App** is a Spring Boot-based application designed for managing journal entries. This application provides RESTful APIs for CRUD operations on journal entries and user management. It leverages MongoDB as a database and ensures secure access using Spring Security.

## Features

- **User Management**:
  - Create, update, delete, and fetch users.
  - Secure authentication using Spring Security and BCrypt password encryption.

- **Journal Management**:
  - Create, update, delete, and fetch journal entries.
  - Journals are linked to individual users for personalized data access.

- **Security**:
  - Stateless session management with Basic Authentication.
  - Custom authentication provider to load users and roles.

## Tech Stack

- **Backend**: Java, Spring Boot
- **Database**: MongoDB
- **Security**: Spring Security
- **Build Tool**: Maven
- **Containerization**: Docker

## Prerequisites

Before running this application, ensure you have:

- Java 17 or later
- Maven
- Docker (if you want to use Docker)
- A running MongoDB instance

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Sho-urya25/SpringJournalApp.git
   ```

2. Navigate to the project directory:
   ```bash
   cd SpringJournalApp
   ```

3. Configure the application:
   - Update the MongoDB URI and database name in `application.properties`:
     ```properties
     spring.application.name=demo
     spring.data.mongodb.uri=<your-mongo-uri>
     spring.data.mongodb.database=journaldb
     spring.data.mongodb.auto-index-creation=true
     ```

4. Build the application:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

6. Access the application:
   - The APIs are accessible at `http://localhost:8080`.

## Dockerization

To run the application using Docker:

1. Create a Dockerfile in the root directory:
   ```dockerfile
   FROM openjdk:22
   VOLUME /tmp
   ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
   COPY ${JAR_FILE} app.jar
   ENTRYPOINT ["java","-jar","/app.jar"]
   ```

2. Build the Docker image:
   ```bash
   docker build -t spring-journal-app .
   ```

3. Run the Docker container:
   ```bash
   docker run -d -p 8080:8080 --name journal-app spring-journal-app
   ```

4. The APIs will be accessible at `http://localhost:8080`.

## API Endpoints

### Public Endpoints (No Authentication)
- **POST** `/public/user`: Create a new user.
- **GET** `/public/user`: Fetch all users.

### Protected Endpoints (Authentication Required)
- **POST** `/user/journal/create`: Create a journal entry.
- **GET** `/user/journal/getAllJournalEntries`: Fetch all journal entries for the authenticated user.
- **PUT** `/user/journal/{id}`: Update a journal entry.
- **DELETE** `/user/journal/deleteAll`: Delete all journal entries for the authenticated user.

## Contribution

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes.
4. Push the branch and create a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions or feedback, please contact [Sho-urya25](https://github.com/Sho-urya25).
