-- Financial Tracker Database Dump
-- H2 Database Schema and Sample Data

DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(12) NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    description VARCHAR(100),
    amount DECIMAL(10,2) NOT NULL CHECK (amount >= 0),
    time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);

CREATE INDEX idx_transaction_category ON transactions(category_id);

INSERT INTO categories (type, name) VALUES ('INCOME', 'Salary');
INSERT INTO categories (type, name) VALUES ('INCOME', 'Freelance');
INSERT INTO categories (type, name) VALUES ('EXPENSE', 'Food');
INSERT INTO categories (type, name) VALUES ('EXPENSE', 'Transport');
INSERT INTO categories (type, name) VALUES ('EXPENSE', 'Entertainment');
INSERT INTO categories (type, name) VALUES ('EXPENSE', 'Utilities');

INSERT INTO transactions (category_id, description, amount, time) VALUES (1, 'Monthly salary', 3000.00, '2024-01-01 09:00:00');
INSERT INTO transactions (category_id, description, amount, time) VALUES (2, 'Website project', 500.00, '2024-01-05 14:30:00');
INSERT INTO transactions (category_id, description, amount, time) VALUES (3, 'Groceries', 150.00, '2024-01-10 18:00:00');
INSERT INTO transactions (category_id, description, amount, time) VALUES (4, 'Bus pass', 50.00, '2024-01-15 08:00:00');
INSERT INTO transactions (category_id, description, amount, time) VALUES (5, 'Cinema tickets', 25.00, '2024-01-20 20:00:00');
INSERT INTO transactions (category_id, description, amount, time) VALUES (3, 'Restaurant', 75.00, '2024-01-22 19:30:00');
INSERT INTO transactions (category_id, description, amount, time) VALUES (6, 'Electricity bill', 100.00, '2024-01-25 10:00:00');
