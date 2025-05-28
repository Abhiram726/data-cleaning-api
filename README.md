# 🧹 DataCleanerAPI

A lightweight and efficient REST API built with **Spring Boot** that takes raw, messy text data and returns clean, standardized output. Whether you're preprocessing data for analysis or just want structured inputs, **DataCleanerAPI** has your back!

---

## 🚀 Features

- ✅ Removes special characters
- ✅ Trims excessive whitespace
- ✅ Converts text to lowercase or uppercase (configurable)
- ✅ Stores cleaned data in a database
- ✅ Supports data loading from a `.csv` file
- ✅ Fully testable with Postman or any HTTP client

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Maven
- H2 / MySQL (database)
- Postman (for API testing)
- GitHub Desktop (for version control)

---

## 📦 API Endpoints

| Method | Endpoint       | Description                 |
|--------|----------------|-----------------------------|
| POST   | `/clean`       | Clean text data from input  |
| GET    | `/data`        | Get all cleaned data        |
| POST   | `/upload-file` | Upload a CSV & clean it     |

Example `POST /clean` request body:
```json
{
  "value": "   Hello @# World!!   "
}
