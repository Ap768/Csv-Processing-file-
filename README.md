# Csv file processing System

A full-stack web application for uploading, validating, processing, and managing employee data from CSV files. The application is built using **React.js**, **Spring Boot**, and **MySQL**, and is deployed on an **External Apache Tomcat Server**.

---

## 🚀 Features

- Upload employee data using CSV files
- Validate uploaded CSV files
- Process employee records automatically
- Store valid records in MySQL database
- Display uploaded file status
- View successfully processed files
- View failed files with error handling
- RESTful API integration between React and Spring Boot
- External Apache Tomcat deployment

---

## 🛠️ Technology Stack

### Frontend
- React.js
- HTML5
- CSS3
- JavaScript
- Bootstrap

### Backend
- Java 8
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

### Database
- MySQL

### Server
- Apache Tomcat 9 (External)

### Build Tool
- Maven

### Version Control
- Git
- GitHub

---

## 📁 Project Structure

```
BulkUploadDocumentManagementSystem
│
├── Backend
│   ├── Controller
│   ├── Service
│   ├── Repository
│   ├── Entity
│   ├── DTO
│   ├── Utility
│   ├── Scheduler
│   └── Configuration
│
├── Frontend
│   ├── Components
│   ├── Pages
│   ├── Services
│   └── Assets
│
└── Database
```

---

## ⚙️ Prerequisites

- Java 8
- Maven
- Node.js
- React
- MySQL 8
- Apache Tomcat 9
- Eclipse IDE / VS Code
- Git

---

## 🗄️ Database Configuration

Create a MySQL database.

```sql
CREATE DATABASE filedb;
```

Update your `application.properties` file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/filedb
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ Running the Backend

1. Import the project into Eclipse or VS Code.
2. Update the MySQL credentials in `application.properties`.
3. Build the project using Maven.
4. Deploy the generated WAR file to Apache Tomcat.
5. Start the Tomcat server.

---

## ▶️ Running the Frontend

```bash
npm install
npm run dev
```

The React application will start on:

```
http://localhost:5173
```

---

## 📡 REST APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/upload` | Upload CSV file |
| GET | `/files` | Retrieve uploaded files |
| GET | `/success` | View successfully processed files |
| GET | `/failure` | View failed files |

---

## 📂 CSV Processing Workflow

1. User uploads a CSV file.
2. Spring Boot validates the file.
3. CSV records are processed.
4. Valid employee records are stored in MySQL.
5. Successfully processed files are moved to the Success folder.
6. Invalid files are moved to the Error folder.
7. Processing status is displayed in the React application.

---

## 📸 Application Features

- CSV File Upload
- Employee Data Processing
- File Validation
- Success & Failure Tracking
- REST API Integration
- MySQL Database Storage
- Responsive React User Interface

---

## 📌 Future Enhancements

- User Authentication & Authorization
- Role-Based Access Control
- File Download
- Search & Filter
- Email Notifications
- Audit Logging
- Dashboard & Reports
- Docker Deployment
- Cloud Deployment (AWS/Azure)

---

## 👨‍💻 Developed By

**Amaan Patel**

Intern

---

## 📄 License

This project is developed for educational and internship purposes.
