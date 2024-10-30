CREATE DATABASE IF NOT EXISTS WishList;

USE WishList;


CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(255),
    user_password VARCHAR(255),
    PRIMARY KEY(id)
    );
    
    
CREATE TABLE wish_list (
	id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    list_name VARCHAR(255),
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
    );
    
    
CREATE TABLE wish (
	id INT NOT NULL AUTO_INCREMENT,
    wish_name VARCHAR(255),
    wish_url VARCHAR(255),
    wish_price FLOAT(15),
    wish_reserved TINYINT DEFAULT 0,
    wish_list_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(wish_list_id) REFERENCES wish_list(id)
    );
    
    
CREATE TABLE wish_item_reservation (
	id INT NOT NULL AUTO_INCREMENT,
    wish_id INT,
    user_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(wish_id) REFERENCES wish(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
    );

    
    
