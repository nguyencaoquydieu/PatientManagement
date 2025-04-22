# Patient Information Management API

This project is a RESTful API for managing patient information. It is built using Java and Spring Boot, providing a robust and scalable solution for handling patient data.

## Project Structure

```
PatientManagement
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── hospital
│   │   │           ├── PatientApplication.java
│   │   │           ├── controller
│   │   │           │   └── PatientController.java
│   │   │           ├── model
│   │   │           │   └── Patient.java
│   │   │           ├── repository
│   │   │           │   └── PatientRepository.java
│   │   │           └── service
│   │   │               └── PatientService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── hospital
│                   └── PatientApplicationTests.java
├── pom.xml
└── README.md
```

## Database Structure

### Table: patients

| Column         | Type         | Constraints       | Description                  |
|---------------|--------------|------------------|--------------------------------|
| id            | BIGINT       | PK, AUTO_INCREMENT| Unique identifier            |
| age           | VARCHAR(3)   | NOT NULL         | Patient's age                 |
| name          | VARCHAR(50)  | NOT NULL         | Patient's name                |
| medical_history| TEXT        |                  | Patient's medical history     |
| created_at    | TIMESTAMP    | DEFAULT NOW()    | Record creation timestamp     |
| updated_at    | TIMESTAMP    | ON UPDATE NOW()  | Record update timestamp       |

### SQL Creation Script

```sql
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    age VARCHAR(3) NOT NULL,
    name VARCHAR(50) NOT NULL,
    medical_history TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## Indexes

```sql
CREATE INDEX idx_patient_name ON patients(name);
```

## Environment Setup

1. Create MySQL database:

```bash
mysql -u root -p -e "CREATE DATABASE patient_db"
```

2. Run the schema creation script:

```bash
mysql -u root -p patient_db < schema.sql
```

3. Configure environment variables in `.env` file:

```properties
DB_URL=jdbc:mysql://localhost:3306/<database_name>
DB_USERNAME=<username>
DB_PASSWORD=<password>
```

## Setup Instructions

1. **Clone the repository:**

   ```
   git clone <repository-url>
   cd PatientManagement
   ```

2. **Build the project:**

   ```
   mvn clean install
   ```

3. **Run the application:**

   ```
   mvn spring-boot:run
   ```

## Usage

The API provides the following endpoints for managing patient information:

- `GET /patients`: Retrieve a list of all patients.
- `GET /patients/{id}`: Retrieve a specific patient by ID.
- `POST /patients`: Create a new patient.
- `PUT /patients/{id}`: Update an existing patient.
- `DELETE /patients/{id}`: Delete a patient.

## Dependencies

This project uses Maven for dependency management. The required dependencies are specified in the `pom.xml` file.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
