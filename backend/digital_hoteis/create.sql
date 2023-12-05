CREATE DATABASE IF NOT EXISTS digital_hoteis;

USE digital_hoteis;

DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS characteristics;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS guest;
DROP TABLE IF EXISTS host;
DROP TABLE IF EXISTS policy;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS reservation_room;
DROP TABLE IF EXISTS user_details;
DROP TABLE IF EXISTS review_score;

CREATE TABLE category (
    id BINARY(16) PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    ratings ENUM('BAD','AVERAGE','GOOD','VERY_GOOD','EXCELLENT')
);

CREATE TABLE characteristics (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    icon VARCHAR(255) NOT NULL
);

CREATE TABLE hotel_characteristics (
    hotel_id BINARY(16),
    facility ENUM('BATHROOM', 'BATHTUB', 'TOILET_PAPER', 'BATHROBE', 'TOWELS', 'PRIVATE_BATHROOM', 'WC', 'SHOWER',
        'FREE_TOILETRIES', 'HAIRDRYER', 'BEDROOM', 'LINENS', 'WARDROBE', 'MEDIA_AND_TECHNOLOGY', 'STREAMING_SERVICE',
        'FLAT_SCREEN_TV', 'CABLE_TV', 'TELEPHONE', 'AIR_CONDITIONER', 'FREE_WIFI', 'PET_FRIENDLY', 'KITCHEN',
        'CLEANING_PRODUCTS', 'STOVE_TOP', 'MICROWAVE', 'REFRIGERATOR', 'LIVING_AREA', 'DINING_AREA', 'SOFA', 'DESK',
        'ROOM_AMENITIES', 'SOCKET_NEAR_BED', 'TILE_MARBLE_FLOOR', 'PRIVATE_ENTRANCE', 'IRON', 'CLEANING_SERVICES',
        'DAILY_HOUSEKEEPING', 'IRONING_SERVICE', 'DRY_CLEANING', 'LAUNDRY', 'FRONT_DESK_SERVICES', 'LOCKERS', 'CONCIERGE',
        'BAGGAGE_STORAGE', 'CURRENCY_EXCHANGE', 'TWENTY_FOUR_HOUR_FRONT_DESK', 'SPA', 'FITNESS', 'SPA_LOUNGE', 'STEAM_ROOM',
        'BEACH_LOUNGERS', 'SOLARIUM', 'SAUNA', 'ACCESSIBILITY', 'LOWERED_SINK', 'RAISED_TOILET', 'TOILET_WITH_GRAB_RAILS',
        'WHEELCHAIR_ACCESSIBLE', 'UPPER_FLOORS_ACCESSIBLE_BY_ELEVATOR', 'SAFETY_SECURITY', 'FIRE_EXTINGUISHERS',
        'CCTV_OUTSIDE_PROPERTY', 'CCTV_IN_COMMON_AREAS', 'SMOKE_ALARMS', 'SECURITY_ALARM', 'KEY_CARD_ACCESS',
        'TWENTY_FOUR_HOUR_SECURITY', 'SAFE', 'FOOD_DRINK', 'FRUIT_WITH_ADDITIONAL_CHARGE',
        'WINE_CHAMPAGNE_WITH_ADDITIONAL_CHARGE', 'BAR', 'MINIBAR', 'RESTAURANT', 'LANGUAGES_SPOKEN', 'ENGLISH', 'PORTUGUESE',
        'SPANISH') NOT NULL,
    value BOOLEAN,
    PRIMARY KEY (hotel_id, facility),
    FOREIGN KEY (hotel_id) REFERENCES characteristics(id)
);


CREATE TABLE city (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(100),
    street VARCHAR(100),
    district VARCHAR(100),
    state VARCHAR(100),
    zipcode VARCHAR(10),
    country VARCHAR(60),
    created_At DATETIME,
    updated_At DATETIME
);


CREATE TABLE contact (
    id BINARY(16) PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    created_At DATETIME,
    updated_At DATETIME
);

CREATE TABLE policy (
    id BINARY(16) PRIMARY KEY,
    check_in_date DATE,
    check_out_date DATE,
    norms_description TEXT,
    health_and_security_description TEXT,
    cancellation_description TEXT,
    cancellationPrepaymentConditions TEXT,
    refundableDeposit TEXT,
    childrenConditions TEXT,
    cribsExtraBedsConditions TEXT,
    ageRestriction TEXT,
    petPolicy TEXT,
    paymentPolicy TEXT
);

CREATE TABLE hotel (
    id BINARY(16) PRIMARY KEY,
    trading_name VARCHAR(65) NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    created_At DATETIME,
    updated_At DATETIME,
    description TEXT NOT NULL,
    fk_hotel_category_id BINARY(16),
    fk_hotel_contact_id BINARY(16),
    hotel_city_id BINARY(16),
    hotel_policy_id BINARY(16),
    FOREIGN KEY (fk_hotel_category_id) REFERENCES category(id),
    FOREIGN KEY (fk_hotel_contact_id) REFERENCES contact(id),
    FOREIGN KEY (hotel_city_id) REFERENCES city(id),
    FOREIGN KEY (hotel_policy_id) REFERENCES policy(id)
);


CREATE TABLE guest (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    birthdate DATE,
    created_At DATETIME,
    updated_At DATETIME,
    city_id BINARY(16),
    contact_id BINARY(16),
    hotel_id BINARY(16),
    FOREIGN KEY (city_id) REFERENCES city(id),
    FOREIGN KEY (contact_id) REFERENCES contact(id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);



CREATE TABLE host (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(35) NOT NULL,
    surname VARCHAR(65) NOT NULL,
    birthdate DATE,
    created_At DATETIME,
    updated_At DATETIME,
    host_contact_id BINARY(16),
    host_hotel_id BINARY(16),
    FOREIGN KEY (host_contact_id) REFERENCES contact(id),
    FOREIGN KEY (host_hotel_id) REFERENCES hotel(id)
);


CREATE TABLE images (
    id BINARY(16) PRIMARY KEY,
    title VARCHAR(255),
    url VARCHAR(255) NOT NULL,
    hotel_id BINARY(16),
    FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);


CREATE TABLE reservation (
    id BINARY(16) PRIMARY KEY,
    check_in_date DATE,
    check_out_date DATE,
    requests TEXT NOT NULL,
    is_canceled BOOLEAN DEFAULT false,
    created_at DATETIME,
    updated_at DATETIME,
    isVaccinatedAgainstCovid BOOLEAN DEFAULT false,
    reservation_guest_id BINARY(16),
    reservation_host_id BINARY(16),
    reservation_hotel_id BINARY(16),
    FOREIGN KEY (reservation_guest_id) REFERENCES guest(id),
    FOREIGN KEY (reservation_host_id) REFERENCES host(id),
    FOREIGN KEY (reservation_hotel_id) REFERENCES hotel(id),
    INDEX (reservation_guest_id),
    INDEX (reservation_host_id),
    INDEX (reservation_hotel_id)
);



CREATE TABLE room (
    id BINARY(16) PRIMARY KEY,
    room_hotel_id BINARY(16) NOT NULL,
    description VARCHAR(255) NOT NULL,
    max_number_of_guests INTEGER,
    has_private_bathroom BOOLEAN,
    has_bathtub BOOLEAN,
    has_kitchen BOOLEAN,
    has_stove BOOLEAN,
    has_microwave BOOLEAN,
    are_pets_allowed BOOLEAN,
    price DECIMAL(10, 2),
    FOREIGN KEY (room_hotel_id) REFERENCES hotel(id)
);


CREATE TABLE reservation_room (
    reservation_id BINARY(16),
    room_id BINARY(16),
    PRIMARY KEY (reservation_id, room_id),
    FOREIGN KEY (reservation_id) REFERENCES reservation(id),
    FOREIGN KEY (room_id) REFERENCES room(id)
);


CREATE TABLE user_detail (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255) DEFAULT 'USER',
    isEnabled BOOLEAN,
    isHost BOOLEAN,
    UNIQUE INDEX idx_user_detail_email (email)
);

CREATE TABLE review_score (
    hotel_id BINARY(16) NOT NULL,
    user_detail_id BINARY(16) NOT NULL,
    review_scores INTEGER,
    PRIMARY KEY (hotel_id, user_detail_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(id),
    FOREIGN KEY (user_detail_id) REFERENCES user_detail(id)
);

CREATE TABLE reservation_host (
    reservation_id BINARY(16),
    host_id BINARY(16),
    PRIMARY KEY (reservation_id, host_id),
    FOREIGN KEY (reservation_id) REFERENCES reservation(id),
    FOREIGN KEY (host_id) REFERENCES host(id)
);


ALTER TABLE review_score
ADD CONSTRAINT uk_review_scores UNIQUE (hotel_id, user_detail_id);

CREATE TABLE confirmationToken (
    token_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    confirmation_token VARCHAR(255) NOT NULL,
    created_date DATETIME,
    user_detail_id BINARY(16),
    FOREIGN KEY (user_detail_id) REFERENCES user_detail(id)
);



