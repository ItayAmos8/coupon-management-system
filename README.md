# Coupon Management System

A backend coupon management system built with **Java**, **Spring Boot**, and **MySQL**.  
This project was developed as part of my software engineering studies and showcases core backend principles, including clean architecture, RESTful API design, and layered business logic.

## ✨ Features

- REST API with role-based access: **Admin**, **Company**, **Customer**
- Full CRUD operations for coupons, companies, and customers
- Role-based login with session handling
- Business rules and constraints (e.g., coupon expiration, ownership)
- Daily job (threaded) to remove expired coupons
- Organized by layered architecture (Controllers, Services, DAO, Entities)

## 🛠️ Tech Stack

- Java 17  
- Spring Boot  
- MySQL  
- JDBC / DAO Pattern  
- Maven  
- Postman (for testing)  
- Git

## 📁 Project Structure

coupon-management-system/
│
├── beans/                 # Entity classes (Coupon, Company, Customer, Category)
├── controller/            # REST API controllers for Admin, Company, Customer
├── dao/                   # DAO interfaces and their implementations
├── services/              # Business logic for each user type
├── login/                 # Login manager and session handling
├── DailyJob/              # Scheduled job to delete expired coupons
├── FinalProjectBApplication.java   # Main Spring Boot application
└── FinalProject.jar       # Compiled JAR for running the project

## 🚀 How to Run

1. Clone the repository:

git clone https://github.com/itayamos8/coupon-management-system.git
cd coupon-management-system

2. Set up MySQL:

Create a database named coupon_system

3. Build and run:

mvn clean install
java -jar FinalProject.jar

Configure DB credentials inside your JDBC utility or Spring configuration (depending on your implementation)

4.Test endpoints using Postman or curl.

🧪 Example API Actions

User | Method | Endpoint | Description
Admin | POST | /admin/addCompany | Add new company
Company | GET | /company/getCompanyCoupons | Get all company coupons
Customer | POST | /customer/purchaseCoupon/{id} | Purchase a coupon

📌 Notes
Coupon expiration is automatically handled daily via a background thread.

The system enforces business rules such as unique coupon titles per company and expiration validation.

🙋‍♂️ About Me
I’m a software engineering student passionate about backend development and system architecture. I built this project to deepen my understanding of data modeling, user access flows, and layered application structure.

📫 Contact
LinkedIn: https://www.linkedin.com/in/itayamos
Email: itayitayy@gmail.com

