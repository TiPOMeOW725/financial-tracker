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
