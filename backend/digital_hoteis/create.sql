DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS Attraction;
DROP TABLE IF EXISTS Contact;
DROP TABLE IF EXISTS Guest;
DROP TABLE IF EXISTS Hosts;
DROP TABLE IF EXISTS Hotels;
DROP TABLE IF EXISTS Reservations;
DROP TABLE IF EXISTS Rooms;

CREATE TABLE Category (
    id BINARY(16) PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    ratings ENUM('ONE_STAR', 'TWO_STARS', 'THREE_STARS', 'FOUR_STARS', 'FIVE_STARS'),
);

CREATE TABLE Address (
    id BINARY(16) PRIMARY KEY,
    street VARCHAR(100),
    district VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(100),
    zipcode CHAR(10),
    country VARCHAR(60),
    created_At DATETIME,
    updated_At DATETIME
);

CREATE TABLE Attraction (
    id BINARY(16) PRIMARY KEY,
    address_id BINARY(16),
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (address_id) REFERENCES Address(id)
);

CREATE TABLE Contact (
    id BINARY(16) PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    created_At DATETIME,
    updated_At DATETIME
);

CREATE TABLE Guests (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    birthdate DATE,
    gender CHAR(1) NOT NULL,
    created_At DATETIME,
    updated_At DATETIME,
    address_id BINARY(16),
    contact_id BINARY(16),
    FOREIGN KEY (address_id) REFERENCES Address(id),
    FOREIGN KEY (contact_id) REFERENCES Contact(id)
);

CREATE TABLE Hosts (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(35) NOT NULL,
    surname VARCHAR(65) NOT NULL
);

CREATE TABLE Hotels (
    id BINARY(16) PRIMARY KEY,
    fk_hotel_category_id BINARY(16),
    fk_hotel_address_id BINARY(16),
    fk_hotel_contact_id BINARY(16),
    trading_name VARCHAR(65) NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    created_At DATETIME,
    updated_At DATETIME,
    description TEXT NOT NULL,
    FOREIGN KEY (fk_hotel_category_id) REFERENCES Categories(id),
    FOREIGN KEY (fk_hotel_address_id) REFERENCES Addresses(id),
    FOREIGN KEY (fk_hotel_contact_id) REFERENCES Contacts(id)
);

CREATE TABLE Reservations (
    id BINARY(16) PRIMARY KEY,
    check_in_date DATE,
    check_out_date DATE,
    requests TEXT NOT NULL,
    is_canceled BOOLEAN,
    created_At DATETIME,
    updated_At DATETIME
);

CREATE TABLE Rooms (
    id BINARY(16) PRIMARY KEY,
    hotel_id BINARY(16) NOT NULL,
    description VARCHAR(255) NOT NULL,
    max_number_of_guests INTEGER,
    has_private_bathroom BOOLEAN,
    has_bathtub BOOLEAN,
    has_kitchen BOOLEAN,
    has_stove BOOLEAN,
    has_microwave BOOLEAN,
    are_pets_allowed BOOLEAN,
    price DECIMAL(10, 2),
    FOREIGN KEY (hotel_id) REFERENCES Hotels(id)
);







