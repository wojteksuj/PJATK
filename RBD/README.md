# Project completed for the RDB (Database Systems) classes
# 🏬 Retail Store Database System

This project is a **relational database system** designed to manage a retail store’s operations. It focuses on tracking **customers**, **employees**, **transactions**, and **inventory**, with embedded business logic and analytics support.

---
## 🛠️ Technologies Used

- **SQL** – Core language for schema, queries, and logic  
- **Oracle SQL** & **T-SQL** – Compatible versions of all scripts  
- **Triggers** – Enforce business rules automatically  
- **Stored Procedures** – Encapsulate complex operations and logic  
- **Entity-Relationship Diagram (ERD)** – Visual schema design

## 🚀 Key Features

- 🧍‍♀️ **Customer Management**  
  Stores personal details, addresses, and discount eligibility.

- 🧑‍💼 **Employee Management**  
  Supports multiple roles per employee, salary tracking, and hire dates.

- 📦 **Inventory Management**  
  Tracks items with categories, conditions, and countries of origin.

- 🧾 **Transaction Tracking**  
  Supports multiple items per transaction and multiple payment methods.

- ⚙️ **Business Logic Automation**  
  Implemented using **stored procedures** and **triggers** for:
  - Discount assignment  
  - Unique email enforcement  
  - Transaction validation

---

## 🔍 Core Functionalities

- Query items sold in a specific transaction  
- Identify the most expensive transaction  
- Analyze most popular payment methods  
- List items by country of origin  
- Display employee roles and role assignments  

---

## 🧩 Schema Highlights

- ✅ **Well-normalized** schema with clear entity relationships  
- 🔁 **Many-to-many** relationships (e.g., Employee ↔ Role, Transaction ↔ Item)  
- 🔒 Data integrity enforced via foreign keys and custom triggers  
- 📈 Designed with analytics and reporting
