CREATE DATABASE IF NOT EXISTS carnet_contacts;
USE carnet_contacts;

CREATE TABLE IF NOT EXISTS contacts (
    id VARCHAR(36) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    email VARCHAR(100)
);
