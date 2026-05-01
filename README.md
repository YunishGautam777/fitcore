# FitCore — Gym Management System

A comprehensive web-based GMS built on **Java Servlets + JSP + JDBC + MySQL** following strict **MVC architecture**, styled in **pure CSS** (no Bootstrap).

## Tech Stack

- Java 11
- Servlet API 4.0 / JSP 2.3 / JSTL 1.2
- MySQL 8.x + JDBC
- BCrypt (jBCrypt) password hashing
- Maven build → deployable to Apache Tomcat 9

## Quick Start

1. **Create the database**
   ```bash
   mysql -u root -p < sql/fitcore_schema.sql
   ```
2. **Configure DB credentials** — edit `src/main/java/com/fitcore/util/DBUtil.java`
3. **Build the WAR**
   ```bash
   mvn clean package
   ```
4. **Deploy** `target/fitcore.war` to Tomcat's `webapps/` folder.
5. Open `http://localhost:8080/fitcore/`
6. **Default admin login:** `admin` / `admin123`

## Project Structure

```
fitcore/
├── pom.xml
├── sql/fitcore_schema.sql
└── src/main/
    ├── java/com/fitcore/
    │   ├── model/        # POJOs
    │   ├── dao/          # JDBC data access
    │   ├── service/      # Business logic
    │   ├── controller/   # Servlets
    │   ├── filter/       # Auth filters
    │   └── util/         # DB, hashing, helpers
    └── webapp/
        ├── css/          # Pure CSS
        ├── js/           # Minimal UI JS
        ├── *.jsp         # Public pages
        └── WEB-INF/
            ├── web.xml
            └── views/    # Protected JSPs
```

See `docs/FitCore_Project_Proposal.md` for the full feature breakdown.
