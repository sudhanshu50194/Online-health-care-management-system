# Online Healthcare Management System

## 1. Project Overview

The **Online Healthcare Management System** is a Java-based desktop application designed to streamline hospital administrative tasks such as patient registration, room allocation, ambulance management, employee handling, and patient discharge. The project demonstrates core Java concepts, Object-Oriented Programming, Swing-based GUI design, and complete **JDBC + MySQL database integration**.

This system enables receptionists and healthcare staff to manage patient and hospital-related data efficiently in a digital format.

## 2. Features Implemented in Review‑1

### **A. Core Java + OOP Concepts**

* Class-based modular structure
* Object creation and event-driven programming
* Encapsulation through separate UI and database logic
* Reusable components such as connection handlers and GUI forms
* Swing-based GUI: JFrame, JPanel, JLabel, JTextField, JTable, JComboBox, JButton, etc.
* Exception handling and input validation

### **B. JDBC + MySQL Integration**

* MySQL connection using JDBC driver
* Insert, update, delete, and search operations
* PreparedStatement usage for secure SQL operations
* ResultSet integration with GUI components
* DbUtils used for loading MySQL result sets into JTable tables

### **C. Database Schema (Review‑1)**

Database tables implemented include:

* **patient_info** – Stores new patient records
* **room** – Hospital room details and availability
* **ambulance** – Ambulance availability and driver info
* **employee_info** – Hospital staff information
* **login / register** – User authentication data
* **department** – Hospital department list
* **patient_discharge** – Records of discharged patients

### **D. Servlet Setup (Required for Review‑1)**

Even though this version focuses primarily on Core Java + Swing, a **basic servlet structure** is prepared for future web migration:

* Directory structure prepared for web modules
* Placeholder servlet classes for upcoming review phases
* Web.xml placeholder for deployment planning

*(Note: Actual servlet implementation is part of later reviews.)*

---

## 3. Included Files

The project contains the following Java classes:

* **ALL_Patient_Info.java** – Displays all patient details in a table
* **Ambulance.java** – Manages ambulance data
* **Conn.java** – Central JDBC connection class
* **Department.java** – Shows hospital departments
* **Employee_info.java** – Displays and manages employee records
* **Login.java** – Login form for users
* **New_Patient.java** – Register new patient entries
* **Patient_Discharge.java** – Handles patient discharge operations
* **Reception.java** – Main dashboard for navigation
* **Register.java** – New user registration form
* **Room.java** – Room and bed allocation management
* **SearchRoom.java** – Search rooms by availability or type
* **Update_Patient_Details.java** – Update existing patient details

---

## 4. Team Members

* **Sudhanshu Singh**
* **Navdeep Patel**

---

## How to Run the Project

1. Install **Java JDK (8 or above)**
2. Install **MySQL** and create database tables
3. Add **MySQL JDBC Driver** to project libraries
4. Clone or download the project
5. Run any main UI class such as `Reception.java`

---

## Future Enhancements

* Full servlet + JSP-based web version
* Role-based access control
* Cloud-managed database
* Enhanced UI styling

---

## License

This project is developed for academic purposes and can be extended for real-world applications.
