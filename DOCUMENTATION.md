# Gym Management System - Complete Documentation

## 📋 Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technology Stack](#technology-stack)
4. [System Architecture](#system-architecture)
5. [Database Structure](#database-structure)
6. [User Roles & Permissions](#user-roles--permissions)
7. [How to Use](#how-to-use)
8. [Setup & Installation](#setup--installation)
9. [API Endpoints](#api-endpoints)
10. [Future Enhancements](#future-enhancements)

---

## 🎯 Project Overview

**Gym Management System** is a comprehensive web-based application designed to manage gym operations efficiently. It allows administrators to manage members, trainers, and classes, while members can view their profiles, book classes, track payments, and interact with their assigned trainers.

### Purpose
- **Streamline gym operations** by centralizing member and trainer management
- **Enhance member experience** with easy access to classes and payment information
- **Improve administrative efficiency** through automated systems
- **Maintain professional record-keeping** for all gym activities

### Key Objectives
✅ Manage gym members and their memberships  
✅ Maintain trainer profiles and assignments  
✅ Track member payments and billing  
✅ Allow members to book fitness classes  
✅ Provide secure login and role-based access control  
✅ Generate reports and analytics (future)

---

## ✨ Features

### Admin Features
**Member Management:**
- ➕ Add new members with details (name, email, phone, plan)
- 📋 View all members in a clean table format
- 🔍 Search members by name or email
- ✏️ Edit member information
- 🗑️ Delete members from the system

**Trainer Management:**
- ➕ Add new trainers
- 👨‍🏫 View all trainers
- ✏️ Manage trainer details
- 🗑️ Remove trainers

**Dashboard:**
- 📊 Quick access to main functions via dashboard
- 👤 View logged-in admin details
- 🚪 Secure logout functionality

### Member Features
**Account Management:**
- 👤 View personal profile
- 💳 Track payment history
- 🔐 Secure login & logout

**Class Management:**
- 📅 Browse available fitness classes
- 🎯 Book classes easily
- ❌ Cancel bookings if needed

**Trainer Access:**
- 👨‍🏫 View assigned trainer information
- 💬 Access trainer contact details

**Dashboard:**
- 📊 Quick access to frequently used functions
- 📌 My Account section
- 🏋️ Classes & Training section

---

## 🛠️ Technology Stack

### Backend
- **Language:** Java 24
- **Framework:** Jakarta EE / Servlets
- **Build Tool:** Maven
- **ORM:** JDBC with PreparedStatements

### Frontend
- **Language:** HTML5, CSS3
- **Styling:** Custom CSS (No Bootstrap)
- **Templating:** JSP (JavaServer Pages)
- **Design:** Responsive, Mobile-friendly

### Database
- **DBMS:** MySQL 8.0+
- **Connection:** MySQL Connector/J 8.0.33
- **Architecture:** Relational Database

### Server
- **Web Server:** Apache Tomcat 10.x (Jakarta EE)
- **Protocol:** HTTP/HTTPS

### Additional Libraries
- Jersey (REST support)
- Jackson (JSON processing)
- JUnit 5 (Testing)

---

## 🏗️ System Architecture

### Layered Architecture

```
┌─────────────────────────────────────┐
│         Presentation Layer          │
│      (JSP Pages & HTML/CSS)        │
└──────────────────┬──────────────────┘
                   │
┌──────────────────▼──────────────────┐
│      Controller Layer               │
│   (Servlets - Business Logic)       │
└──────────────────┬──────────────────┘
                   │
┌──────────────────▼──────────────────┐
│        Data Access Layer            │
│    (DAO Classes - Database Ops)     │
└──────────────────┬──────────────────┘
                   │
┌──────────────────▼──────────────────┐
│      Database Layer                 │
│        (MySQL Database)             │
└─────────────────────────────────────┘
```

### Key Components

#### 1. **Presentation Layer (JSP Pages)**
- `login.jsp` - Login page with gradient background
- `adminDashboard.jsp` - Admin dashboard with sidebar
- `memberDashboard.jsp` - Member dashboard
- `addMember.jsp` - Member registration form
- `viewMembers.jsp` - Members list with CRUD operations
- `editMember.jsp` - Member edit form
- `searchMember.jsp` - Member search functionality

#### 2. **Controller Layer (Servlets)**
- `LoginServlet` - Handle user authentication
- `LogoutServlet` - Handle user logout and session cleanup
- `AddMemberServlet` - Process member registration
- `ViewMembersServlet` - Fetch and display members
- `DeleteMemberServlet` - Delete member records
- `UpdateMemberServlet` - Update member information
- `SearchMemberServlet` - Search members by keyword

#### 3. **Model Layer (POJOs)**
- `Member` - Member data model with getters/setters
- `Trainer` - Trainer data model (extensible)
- `User` - User authentication model

#### 4. **Data Access Layer (DAO)**
- `MemberDao` - Database operations for members
  - `addMember(Member m)`
  - `getAllMembers()`
  - `updateMember(Member m)`
  - `deleteMember(int id)`
  - `searchMembers(String keyword)`

#### 5. **Utility Layer**
- `DBUtil` - JDBC connection management
  - Centralized database connection
  - Driver loading
  - Connection pooling support

---

## 🗄️ Database Structure

### Database: `gym_system`

#### Table: `users`
```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);
```
**Columns:**
- `id` - Primary key
- `email` - User email (unique)
- `password` - User password (plaintext - should be hashed in production)
- `role` - User role (admin/member/trainer)

#### Table: `members`
```sql
CREATE TABLE members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    plan VARCHAR(50)
);
```
**Columns:**
- `id` - Primary key
- `name` - Member full name
- `email` - Member email
- `phone` - Contact number
- `plan` - Membership plan (Basic/Premium/Elite)

#### Table: `trainers` (Future Implementation)
```sql
CREATE TABLE trainers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    specialty VARCHAR(100),
    phone VARCHAR(20)
);
```

#### Table: `classes` (Future Implementation)
```sql
CREATE TABLE classes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(100) NOT NULL,
    trainer_id INT,
    time VARCHAR(50),
    max_members INT,
    FOREIGN KEY (trainer_id) REFERENCES trainers(id)
);
```

---

## 👥 User Roles & Permissions

### Admin Role
**Responsibilities:**
- Manage member database
- Add/edit/delete members
- Search and filter members
- Manage trainers and classes
- View system reports
- Manage billing and payments

**Access:**
- ✅ Admin Dashboard
- ✅ Member Management (CRUD)
- ✅ Trainer Management (CRUD)
- ✅ Class Management
- ✅ All search features
- ✅ Logout

**Default Credentials:**
- Email: `admin@gym.com`
- Password: `admin`

### Member Role
**Responsibilities:**
- Maintain personal profile
- Book fitness classes
- Track payments
- View trainer assignments
- Update profile information

**Access:**
- ✅ Member Dashboard
- ✅ View Profile
- ✅ Book Classes
- ✅ View Payments
- ✅ View Trainer Info
- ✅ Logout

**Sample Credentials:**
- Email: `member@gym.com`
- Password: `member123`

### Trainer Role (Future)
**Responsibilities:**
- Create and manage classes
- Track member attendance
- Update class schedules
- View class roster

---

## 📖 How to Use

### For Administrators

#### 1. **Login to System**
- Visit: `http://localhost:8080/GymManagementSystem-1.0-SNAPSHOT/`
- Enter credentials: `admin@gym.com` / `admin`
- Click Login

#### 2. **Add New Member**
- Navigate to: Admin Dashboard → "Add Member"
- Fill in member details:
  - Name: Full name
  - Email: Valid email address
  - Phone: Contact number
  - Plan: Select membership plan
- Click "Add Member"
- System confirms with redirect to members list

#### 3. **View All Members**
- Navigate to: Admin Dashboard → "View Members"
- See all members in a table format
- Available columns: ID, Name, Email, Phone, Plan
- Actions: Edit, Delete

#### 4. **Search Members**
- Navigate to: Admin Dashboard → "Search Member"
- Enter search keyword (name or email)
- Click Search
- System displays matching members
- Edit/Delete options available

#### 5. **Edit Member Information**
- View Members → Click "Edit" button on member row
- Update desired fields
- Click "Update Member"
- System confirms changes

#### 6. **Delete Member**
- View Members → Click "Delete" button
- Confirm deletion when prompted
- Member removed from system

#### 7. **Logout**
- Click "Logout" in sidebar
- Session ends and redirected to login page

### For Members

#### 1. **Login to System**
- Visit: `http://localhost:8080/GymManagementSystem-1.0-SNAPSHOT/`
- Enter your email and password
- Click Login
- Redirected to Member Dashboard

#### 2. **View Your Profile**
- Member Dashboard → "View Profile"
- See your account details
- Option to edit profile (future enhancement)

#### 3. **Book a Class**
- Member Dashboard → "Book Class"
- Browse available classes
- Select desired class and time
- Confirm booking
- Receive confirmation message

#### 4. **View Payment History**
- Member Dashboard → "View Payments"
- See all payments made
- Check payment dates and amounts
- Download invoices (future feature)

#### 5. **Contact Your Trainer**
- Member Dashboard → "View Trainer"
- See assigned trainer details
- Contact information available
- Schedule sessions (future feature)

#### 6. **Logout**
- Click "Logout" in sidebar
- Your session ends securely

---

## 🚀 Setup & Installation

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Apache Tomcat 10.x
- Maven 3.6+
- Git (optional)

### Step 1: Clone/Download Project
```bash
cd E:\GymManagementSystem
```

### Step 2: Setup MySQL Database
```sql
-- Create database
CREATE DATABASE gym_system;

-- Create users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    role VARCHAR(20)
);

-- Create members table
CREATE TABLE members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    plan VARCHAR(50)
);

-- Insert sample data
INSERT INTO users (email, password, role) VALUES 
('admin@gym.com', 'admin', 'admin');

INSERT INTO members (name, email, phone, plan) VALUES 
('John Doe', 'john@example.com', '1234567890', 'Premium');
```

### Step 3: Configure Database Connection
Edit `src/main/java/util/DBUtil.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/gym_system";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### Step 4: Build Project
```bash
mvn clean package
```

### Step 5: Deploy to Tomcat
1. Copy `target/GymManagementSystem-1.0-SNAPSHOT.war` to `TOMCAT_HOME/webapps/`
2. Start Tomcat:
   ```bash
   TOMCAT_HOME\bin\startup.bat  (Windows)
   TOMCAT_HOME/bin/startup.sh   (Linux/Mac)
   ```

### Step 6: Access Application
- Open browser: `http://localhost:8080/GymManagementSystem-1.0-SNAPSHOT/`
- Login with credentials

---

## 🔌 API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/LoginServlet` | User login |
| GET | `/LogoutServlet` | User logout |

### Member Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/AddMemberServlet` | Add new member |
| GET | `/ViewMembersServlet` | Get all members |
| POST | `/UpdateMemberServlet` | Update member |
| GET | `/DeleteMemberServlet?id=1` | Delete member |
| GET | `/SearchMemberServlet?keyword=john` | Search members |

### Pages
| Path | Description |
|------|-------------|
| `/jsp/login.jsp` | Login page |
| `/jsp/adminDashboard.jsp` | Admin dashboard |
| `/jsp/memberDashboard.jsp` | Member dashboard |
| `/jsp/addMember.jsp` | Add member form |
| `/jsp/viewMembers.jsp` | View members list |
| `/jsp/editMember.jsp` | Edit member form |
| `/jsp/searchMember.jsp` | Search members |

---

## 🔮 Future Enhancements

### Phase 2 Features
- [ ] **Payment Management System**
  - Online payment integration (Stripe/PayPal)
  - Invoice generation
  - Payment receipts
  - Subscription management

- **Class Management**
  - Create and schedule classes
  - Class capacity management
  - Attendance tracking
  - Class cancellation system

- **Trainer Management**
  - Trainer profiles with specialties
  - Class assignment
  - Performance metrics
  - Availability calendar

- **Member Features**
  - Profile picture upload
  - Workout history tracking
  - Progress reports
  - Goal setting and tracking
  - Fitness assessments

### Phase 3 Features
- [ ] **Advanced Analytics & Reporting**
  - Membership statistics
  - Revenue reports
  - Member attendance reports
  - Performance dashboards

- **Communication**
  - Email notifications
  - SMS alerts
  - In-app notifications
  - Member newsletters

- **Mobile App**
  - iOS/Android native apps
  - Mobile-friendly responsive design
  - Push notifications
  - Mobile booking

### Phase 4 Features
- [ ] **Advanced Security**
  - Password encryption (bcrypt)
  - Two-factor authentication
  - Role-based access control (RBAC)
  - Audit logging

- **Integration Features**
  - Google Calendar integration
  - QR code attendance
  - Fitness tracker integration
  - Social media integration

- **Machine Learning**
  - Member retention prediction
  - Personalized class recommendations
  - Workout plan suggestions
  - Churn analysis

---

## 🎓 Learning Resources

### Concepts Used
1. **MVC Architecture** - Separation of concerns
2. **JDBC** - Database connectivity
3. **Servlets** - Server-side processing
4. **JSP** - Dynamic web pages
5. **PreparedStatements** - SQL injection prevention
6. **Session Management** - User authentication
7. **Responsive Design** - Mobile-friendly UI
8. **RESTful Principles** - API design

### Technologies Covered
- Jakarta EE (formerly Java EE)
- Maven project management
- MySQL database design
- HTML/CSS web design
- Form handling and validation
- Error handling and logging

---

## 🐛 Troubleshooting

### Issue: "Cannot connect to database"
**Solution:**
- Ensure MySQL is running
- Verify database name is `gym_system`
- Check credentials in `DBUtil.java`
- Run `DBTest.java` to test connection

### Issue: "Login page doesn't redirect"
**Solution:**
- Verify credentials are correct
- Check web.xml for servlet mappings
- Ensure WAR file is deployed properly
- Clear browser cache

### Issue: "404 Not Found errors"
**Solution:**
- Check servlet paths in web.xml
- Verify JSP file locations
- Ensure application name in URL is correct

---

## 📞 Support

For issues or questions:
1. Check this documentation
2. Review error messages carefully
3. Check MySQL error logs
4. Verify file permissions
5. Test database connection with DBTest.java

---

## 📜 License

This is an educational project for learning Java web development.

---

**Last Updated:** April 15, 2026  
**Version:** 1.0-SNAPSHOT  
**Status:** In Development

