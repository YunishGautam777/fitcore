-- =====================================================================
-- FitCore Gym Management System -- Schema & Seed Data
-- MySQL 8.x
-- =====================================================================

DROP DATABASE IF EXISTS fitcore_db;
CREATE DATABASE fitcore_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE fitcore_db;

-- ---------------------------------------------------------------------
-- 1. User Accounts
-- ---------------------------------------------------------------------
CREATE TABLE UserAccounts (
    user_id        INT AUTO_INCREMENT PRIMARY KEY,
    username       VARCHAR(50)  NOT NULL UNIQUE,
    password_hash  VARCHAR(255) NOT NULL,
    role           ENUM('ADMIN','MEMBER') NOT NULL,
    status         ENUM('PENDING','ACTIVE','INACTIVE','SUSPENDED') NOT NULL DEFAULT 'PENDING',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 2. Membership Plans
-- ---------------------------------------------------------------------
CREATE TABLE MembershipPlans (
    plan_id     INT AUTO_INCREMENT PRIMARY KEY,
    plan_name   VARCHAR(100) NOT NULL,
    duration    INT          NOT NULL COMMENT 'duration in days',
    price       DECIMAL(10,2) NOT NULL,
    features    TEXT
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 3. Members
-- ---------------------------------------------------------------------
CREATE TABLE Members (
    member_id        INT AUTO_INCREMENT PRIMARY KEY,
    user_id          INT,
    name             VARCHAR(100) NOT NULL,
    dob              DATE,
    contact          VARCHAR(20),
    email            VARCHAR(100) UNIQUE,
    gender           ENUM('MALE','FEMALE','OTHER'),
    address          VARCHAR(255),
    membership_type  VARCHAR(50),
    plan_id          INT,
    plan_expiry      DATE,
    fitness_goal     VARCHAR(255),
    join_date        DATE NOT NULL DEFAULT (CURRENT_DATE),
    status           ENUM('ACTIVE','INACTIVE','SUSPENDED') NOT NULL DEFAULT 'ACTIVE',
    CONSTRAINT fk_member_user FOREIGN KEY (user_id) REFERENCES UserAccounts(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_member_plan FOREIGN KEY (plan_id) REFERENCES MembershipPlans(plan_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 4. Trainers
-- ---------------------------------------------------------------------
CREATE TABLE Trainers (
    trainer_id       INT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    specialization   VARCHAR(100),
    experience       INT COMMENT 'years',
    contact          VARCHAR(20),
    assigned_shift   ENUM('MORNING','EVENING','FULL_DAY')
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 5. Equipment
-- ---------------------------------------------------------------------
CREATE TABLE Equipment (
    equipment_id   INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    category       VARCHAR(50),
    purchase_date  DATE,
    `condition`    ENUM('GOOD','NEEDS_SERVICE','DAMAGED') NOT NULL DEFAULT 'GOOD',
    quantity       INT NOT NULL DEFAULT 0
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 6. Workout Sessions / Classes
-- ---------------------------------------------------------------------
CREATE TABLE WorkoutSessions (
    session_id   INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    trainer_id   INT,
    schedule     DATETIME NOT NULL,
    capacity     INT NOT NULL DEFAULT 20,
    enrolled     INT NOT NULL DEFAULT 0,
    type         VARCHAR(50),
    CONSTRAINT fk_session_trainer FOREIGN KEY (trainer_id) REFERENCES Trainers(trainer_id) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE SessionBookings (
    booking_id  INT AUTO_INCREMENT PRIMARY KEY,
    session_id  INT NOT NULL,
    member_id   INT NOT NULL,
    booked_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uniq_session_member (session_id, member_id),
    CONSTRAINT fk_booking_session FOREIGN KEY (session_id) REFERENCES WorkoutSessions(session_id) ON DELETE CASCADE,
    CONSTRAINT fk_booking_member  FOREIGN KEY (member_id)  REFERENCES Members(member_id)         ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 7. Attendance
-- ---------------------------------------------------------------------
CREATE TABLE Attendance (
    attendance_id   INT AUTO_INCREMENT PRIMARY KEY,
    member_id       INT NOT NULL,
    date            DATE NOT NULL,
    check_in_time   TIME,
    check_out_time  TIME,
    CONSTRAINT fk_attendance_member FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 8. Payments
-- ---------------------------------------------------------------------
CREATE TABLE Payments (
    payment_id      INT AUTO_INCREMENT PRIMARY KEY,
    member_id       INT NOT NULL,
    plan_id         INT,
    amount          DECIMAL(10,2) NOT NULL,
    date            DATE NOT NULL,
    status          ENUM('PENDING','PAID','OVERDUE','REFUNDED') NOT NULL DEFAULT 'PENDING',
    payment_method  ENUM('CASH','CARD','ONLINE') NOT NULL DEFAULT 'CASH',
    CONSTRAINT fk_payment_member FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_payment_plan   FOREIGN KEY (plan_id)   REFERENCES MembershipPlans(plan_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 9. Diet Plans
-- ---------------------------------------------------------------------
CREATE TABLE DietPlans (
    diet_id        INT AUTO_INCREMENT PRIMARY KEY,
    member_id      INT NOT NULL,
    trainer_id     INT,
    calories       INT,
    meal_details   TEXT,
    assigned_date  DATE NOT NULL,
    CONSTRAINT fk_diet_member  FOREIGN KEY (member_id)  REFERENCES Members(member_id)  ON DELETE CASCADE,
    CONSTRAINT fk_diet_trainer FOREIGN KEY (trainer_id) REFERENCES Trainers(trainer_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 10. Announcements
-- ---------------------------------------------------------------------
CREATE TABLE Announcements (
    announcement_id INT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(150) NOT NULL,
    body            TEXT NOT NULL,
    category        VARCHAR(50),
    posted_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pinned          TINYINT(1) DEFAULT 0
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 11. Contact / Inquiry messages
-- ---------------------------------------------------------------------
CREATE TABLE ContactMessages (
    msg_id     INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    subject    VARCHAR(150),
    message    TEXT NOT NULL,
    sent_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 12. Equipment Maintenance Logs
-- ---------------------------------------------------------------------
CREATE TABLE MaintenanceLogs (
    log_id        INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id  INT NOT NULL,
    log_date      DATE NOT NULL,
    note          VARCHAR(500),
    cost          DECIMAL(10,2),
    CONSTRAINT fk_log_equip FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =====================================================================
-- INDEXES on frequently queried columns
-- =====================================================================
CREATE INDEX idx_member_status      ON Members(status);
CREATE INDEX idx_payment_status     ON Payments(status);
CREATE INDEX idx_payment_member     ON Payments(member_id);
CREATE INDEX idx_attendance_date    ON Attendance(date);
CREATE INDEX idx_attendance_member  ON Attendance(member_id);
CREATE INDEX idx_session_type       ON WorkoutSessions(type);
CREATE INDEX idx_trainer_spec       ON Trainers(specialization);

-- =====================================================================
-- SEED DATA
-- =====================================================================

-- Default admin user (password: admin123 -- BCrypt hash)
INSERT INTO UserAccounts (username, password_hash, role, status) VALUES
('admin', '$2a$12$nTskiqhP3.5MKQGlCQTbUuY51vM4aUjhxL2mzQ4P/zCSbglNvLqSW', 'ADMIN', 'ACTIVE');

INSERT INTO MembershipPlans (plan_name, duration, price, features) VALUES
('Monthly Basic',    30,  1500.00, 'Gym access, locker'),
('Quarterly Pro',    90,  4000.00, 'Gym + 2 group classes/week'),
('Annual Elite',    365, 14000.00, 'Gym + unlimited classes + 1 PT/month');

INSERT INTO Trainers (name, specialization, experience, contact, assigned_shift) VALUES
('Susham Parajuli',  'Strength & Conditioning', 6, '9876501001', 'MORNING'),
('Yunish Gautam',    'Yoga',                    5, '9876501002', 'EVENING'),
('Nishan Karki',     'HIIT',                    4, '9876501003', 'FULL_DAY'),
('Tapas Sitaula',    'Zumba',                   5, '9876501004', 'EVENING'),
('Hridaya Shiwakoti',          'Functional Training',     3, '9876501005', 'MORNING');

INSERT INTO Equipment (name, category, purchase_date, `condition`, quantity) VALUES
('Treadmill',          'Cardio',     '2024-01-15', 'GOOD',          8),
('Olympic Barbell',    'Strength',   '2023-09-10', 'GOOD',          6),
('Yoga Mat',           'Accessory',  '2024-03-22', 'GOOD',         30),
('Stationary Bike',    'Cardio',     '2022-11-05', 'NEEDS_SERVICE', 4),
('Dumbbell Set 5-50',  'Strength',   '2023-06-18', 'GOOD',          2);

INSERT INTO WorkoutSessions (name, trainer_id, schedule, capacity, type) VALUES
('Morning Yoga Flow', 2, '2026-05-01 06:30:00', 20, 'Yoga'),
('HIIT Burn',         3, '2026-05-01 18:00:00', 15, 'HIIT'),
('Power Lifting 101', 1, '2026-05-02 07:00:00', 10, 'Strength'),
('Zumba Party',       4, '2026-05-02 19:00:00', 25, 'Zumba');

INSERT INTO Announcements (title, body, category, pinned) VALUES
('Welcome to FitCore!',     'New members get a complimentary fitness assessment in their first week.', 'GENERAL', 1),
('Holiday Closure',         'The gym will be closed on May 1st for Labour Day.',                       'NOTICE',  0),
('New Class: Spin Cycle',   'Spin classes start every Tuesday and Thursday at 7 PM.',                  'OFFER',   0);
