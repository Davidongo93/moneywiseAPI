-- tables-creation.sql

-- Create table 'users'
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL
);

-- Crete table 'entries'
CREATE TABLE entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    date DATE NOT NULL,
    description VARCHAR(1000) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    type VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
