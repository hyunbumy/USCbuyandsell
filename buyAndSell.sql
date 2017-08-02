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
    image VARCHAR(2083),
	userID INT (5) PRIMARY KEY AUTO_INCREMENT

);

CREATE TABLE ItemsTable (
	
    sellingUser INT(5) NOT NULL,
	title VARCHAR(20) NOT NULL,
	price FLOAT NOT NULL,
    category VARCHAR(20) NOT NULL, -- need to decide if I want to make a CategoriesTable to store the ones we have
	quantity INT(3) NOT NULL, -- on front end, if they don't enter a quantiy we must set it to 1
    description VARCHAR(100),
	image VARCHAR(2083),
    itemID INT(6) PRIMARY KEY AUTO_INCREMENT,

    -- selling user is the auto-generated key from UserTable
	FOREIGN KEY fk1(sellingUser) REFERENCES UserTable(userID)

);	


CREATE TABLE KeywordTable (
	
	keyword VARCHAR(20) NOT NULL,
    itemID INT(6) NOT NULL,
    keywordID INT(6) PRIMARY KEY AUTO_INCREMENT,
    
    -- itemID from ItemsTable 
	FOREIGN KEY fk1(itemID) REFERENCES ItemsTable(itemID)

);


CREATE TABLE WishlistTable(
	
    wishingUser INT(5) NOT NULL,
    itemID INT(6) NOT NULL,
    wishlistID INT(5) PRIMARY KEY AUTO_INCREMENT,
    
    FOREIGN KEY fk1(wishingUser) REFERENCES UserTable(userID),
    FOREIGN KEY fk2(itemID) REFERENCES ItemsTable(itemID)
);

CREATE TABLE WishlistMessage(

	wishingUser INT(5) NOT NULL,
    itemID int(6) NOT NULL,
    isRead bool NOT NULL,
    sentTime VARCHAR (100), 
    sentDate VARCHAR(100), 
    sellingUser INT(5) NOT NULL,
    wishlistID INT(5) PRIMARY KEY AUTO_INCREMENT,
    
	FOREIGN KEY fk1(wishingUser) REFERENCES UserTable(userID),
	FOREIGN KEY fk2(itemID) REFERENCES ItemsTable(itemID),
	FOREIGN KEY fk3(sellingUser) REFERENCES UserTable(userID)
);

CREATE TABLE UserRatings (
	
    userID INT(6) NOT NULL,
    rating INT(10),
    
    FOREIGN KEY fk1(userID) REFERENCES UserTable(userID)

);

CREATE TABLE RatingMessage (

	ratedUser INT(6) NOT NULL,
    ratingUser INT(6) NOT NULL,
	itemID int(6) NOT NULL,
    isRead bool NOT NULL,
    sentTime VARCHAR (100), 
    sentDate VARCHAR(100), 
    ratingID INT(5) PRIMARY KEY AUTO_INCREMENT,
    
    
    FOREIGN KEY fk1(ratedUser) REFERENCES UserTable(userID),
    FOREIGN KEY fk2(ratingUser) REFERENCES UserTable(userID)
	

);




                    
                    
