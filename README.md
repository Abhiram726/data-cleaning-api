<div align="center">

# 🧹 DataCleaner

*A clean and lightweight Spring Boot application for uploading and analyzing datasets.*

![Java](https://img.shields.io/badge/Java-25-E76F00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-336791?style=flat-square&logo=postgresql&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

</div>

---

## Overview

**DataCleaner** is a Spring Boot web application that accepts dataset uploads, parses the uploaded file, and provides a quick summary including:

- Dataset ID
- File name
- Number of rows
- Number of columns
- Upload status

The project follows a layered architecture with clear separation between the controller, service, and parser components.

---

## Features

- CSV dataset upload
- Multipart file handling
- Automatic row and column counting
- Dataset summary response
- REST API built with Spring Boot
- Responsive web interface
- Dark Mode
- Thymeleaf templates
- Modular page fragments (Navbar & Footer)

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 25 | Backend |
| Spring Boot 3.5 | Web Framework |
| Spring MVC | REST APIs |
| Apache Commons CSV | CSV Parsing |
| PostgreSQL | Database |
| Thymeleaf | Server-side Rendering |
| HTML5 | Frontend |
| CSS3 | Styling |
| JavaScript (ES6) | Client-side Logic |
| Maven | Dependency Management |

---

## Project Structure

```text
src
└── main
    ├── java
    │   └── com.abhiram.datacleaner
    │       ├── controller
    │       ├── service
    │       ├── upload
    │       └── ...
    │
    └── resources
        ├── static
        │   ├── css
        │   └── js
        │
        └── templates
            ├── fragments
            ├── index.html
            ├── services.html
            └── contact.html
```

---

## REST API

### Upload Dataset

```http
POST /api/datasets
```

### Request

```text
multipart/form-data
```

Parameter

| Name | Type |
|------|------|
| file | MultipartFile |

---

## Sample Response

```json
{
  "datasetId": "77f8b22c-4b90-4e88-befa-8dc50f0f4d5e",
  "fileName": "employees.csv",
  "rowCount": 150,
  "columnCount": 8,
  "status": "UPLOADED"
}
```

---

## Running the Project

### Clone

```bash
git clone https://github.com/Abhiram726/data-cleaning-api.git
```

### Navigate

```bash
cd data-cleaning-api
```

### Run

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

Application runs at

```
http://localhost:8090
```

---

## Screenshots

Add screenshots here.

```
Home Page
<img width="1710" height="1022" alt="Screenshot 2026-07-21 at 4 59 43 PM" src="https://github.com/user-attachments/assets/634d588b-4e05-4b9e-9cb6-3ec498ca3406" />

Services Page
<img width="1710" height="1022" alt="Screenshot 2026-07-21 at 5 00 05 PM" src="https://github.com/user-attachments/assets/89d2679a-912a-4f70-b394-8449e975397e" />

Dark Mode


Contact Page
<img width="1710" height="1022" alt="Screenshot 2026-07-21 at 4 59 59 PM" src="https://github.com/user-attachments/assets/c680450f-4a60-43e6-9d88-1d4a07c90eb8" />


Upload Success
<img width="1710" height="1022" alt="Screenshot 2026-07-21 at 4 59 55 PM" src="https://github.com/user-attachments/assets/ef78c7d3-a160-4756-aca8-5079b4047b96" />

```

---

## Future Improvements

- Data cleaning operations
- Missing value handling
- Duplicate removal
- Outlier detection
- JSON support
- Excel support
- Download cleaned datasets
- Dataset history
- User authentication
- Dashboard & analytics

---

## Author

**Abhiram Gowtham**

Portfolio  
https://abhiramgowtham.netlify.app

GitHub  
https://github.com/Abhiram726

LinkedIn  
https://linkedin.com/in/abhiramgowtham

---

## License

This project is licensed under the MIT License.
