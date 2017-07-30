DROP DATABASE IF EXISTS USCbuyandsell;

CREATE DATABASE USCbuyandsell;

USE USCbuyandsell;

CREATE TABLE UserTable (
	
    uname VARCHAR(20) NOT NULL,
    pword VARCHAR(100) NOT NULL,
    fname VARCHAR(20) NOT NULL,
    lname VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    phoneNum VARCHAR(15) NOT NULL,
	rating FLOAT,
    image VARCHAR(100),
	userID INT (5) PRIMARY KEY AUTO_INCREMENT

);

CREATE TABLE ItemsTable (
	
    sellingUser INT(5) NOT NULL,
	title VARCHAR(20) NOT NULL,
	price FLOAT NOT NULL,
    category VARCHAR(20) NOT NULL, -- need to decide if I want to make a CategoriesTable to store the ones we have
	quantity INT(3) NOT NULL, -- on front end, if they don't enter a quantiy we must set it to 1
    description VARCHAR(100),
    itemID INT(6) PRIMARY KEY AUTO_INCREMENT,
    
    -- selling user is the auto-generated key from UserTable
	FOREIGN KEY fk1(sellingUser) REFERENCES UserTable(userID)

);	


CREATE TABLE KeywordTable (
	
	keyword VARCHAR(20) NOT NULL,
    itemID INT(6) PRIMARY KEY AUTO_INCREMENT,
    
    -- itemID from ItemsTable 
	FOREIGN KEY fk1(itemID) REFERENCES ItemsTable(itemID)

);


CREATE TABLE WishlistTable(
	
    wishingUser INT(5) NOT NULL,
    itemID INT(6) NOT NULL,
    
    FOREIGN KEY fk1(wishingUser) REFERENCES UserTable(userID),
    FOREIGN KEY fk2(itemID) REFERENCES ItemsTable(itemID)

);

CREATE TABLE WishlistMessage(

	wishingUser INT(5) NOT NULL,
    itemID int(6) NOT NULL,
    isRead bool NOT NULL,
    
    
	FOREIGN KEY fk1(wishingUser) REFERENCES UserTable(userID),
	FOREIGN KEY fk2(itemID) REFERENCES ItemsTable(itemID)

);


                    
                    
