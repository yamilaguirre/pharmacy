# Pharmacy Management System

A Spring Boot REST API for managing pharmacy suppliers.

## Features

- CRUD operations for suppliers
- RESTful API endpoints
- Spring Boot framework
- Maven build system

## API Endpoints

- `GET /api/suppliers` - Get all suppliers
- `GET /api/suppliers/{id}` - Get supplier by ID
- `POST /api/suppliers` - Create new supplier
- `PUT /api/suppliers/{id}` - Update supplier
- `DELETE /api/suppliers/{id}` - Delete supplier

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Maven

## How to Run

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn spring-boot:run`
4. The API will be available at `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/com/pharmacy/
│   │   ├── controller/     # REST controllers
│   │   ├── model/          # Entity models
│   │   ├── repository/     # Data repositories
│   │   ├── service/        # Business logic
│   │   └── PharmacyApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/pharmacy/
        └── PharmacyApplicationTests.java
```