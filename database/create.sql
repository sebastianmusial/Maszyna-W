DROP TABLE IF EXISTS ProgramMMLibrary CASCADE
;
DROP TABLE IF EXISTS CommandMMLibrary CASCADE
;
DROP TABLE IF EXISTS Programs CASCADE
;
DROP TABLE IF EXISTS Commands CASCADE
;
DROP TABLE IF EXISTS ProgramsLibrary CASCADE
;
DROP TABLE IF EXISTS CommandsLibrary CASCADE
;
DROP TABLE IF EXISTS CommandsLists CASCADE
;
DROP TABLE IF EXISTS Reply CASCADE
;
DROP TABLE IF EXISTS Topics CASCADE
;
DROP TABLE IF EXISTS Categories CASCADE
;
DROP TABLE IF EXISTS Users CASCADE
;
DROP TABLE IF EXISTS Language CASCADE
;


CREATE TABLE ProgramMMLibrary
(
	programLibraryID BIGINT UNSIGNED NOT NULL,
	programID BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (programLibraryID, programID),
	KEY (programID),
	KEY (programLibraryID)

) 
ENGINE=InnoDB
;


CREATE TABLE CommandsLists
(
	commandListID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	userID BIGINT UNSIGNED,
	isPublic BOOL NOT NULL,
	name VARCHAR(50),
	PRIMARY KEY (commandListID),
	KEY (userID)

) 
ENGINE=InnoDB
;


CREATE TABLE Programs
(
	programID BIGINT UNSIGNED NOT NULL,
	userID BIGINT UNSIGNED NOT NULL,
	programName VARCHAR(30) NOT NULL,
	programCode TEXT NOT NULL,
	isPublic BOOL NOT NULL DEFAULT false,
	PRIMARY KEY (programID),
	KEY (userID)

) 
ENGINE=InnoDB
;


CREATE TABLE Commands
(
	commandID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	commandName VARCHAR(10) NOT NULL,
	commandCode TEXT NOT NULL,
	arguments BOOL NOT NULL DEFAULT false,
	commandListID BIGINT UNSIGNED,
	commandIndex SMALLINT UNSIGNED NOT NULL,
	PRIMARY KEY (commandID),
	KEY (commandListID)

) 
ENGINE=InnoDB
;


CREATE TABLE ProgramsLibrary
(
	programLibraryID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	userID BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (programLibraryID),
	KEY (userID)

) 
ENGINE=InnoDB
;





CREATE TABLE Categories
(
	categoryID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	categoryName VARCHAR(50) NOT NULL,
	PRIMARY KEY (categoryID)

) 
ENGINE=InnoDB
;


CREATE TABLE Reply
(
	replyID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	userID BIGINT UNSIGNED NOT NULL,
	topicID BIGINT UNSIGNED NOT NULL,
	replyText TEXT NOT NULL,
	date DATETIME NOT NULL,
	PRIMARY KEY (replyID),
	KEY (topicID),
	KEY (userID)

) 
ENGINE=InnoDB
;


CREATE TABLE Topics
(
	topicID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	userID BIGINT UNSIGNED NOT NULL,
	categoryID BIGINT UNSIGNED NOT NULL,
	topicName VARCHAR(50) NOT NULL,
	date DATETIME NOT NULL,
	PRIMARY KEY (topicID),
	KEY (categoryID),
	KEY (userID)
) 
ENGINE=InnoDB
;


CREATE TABLE Users
(
	userID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	login VARCHAR(30) NOT NULL,
	password VARCHAR(64) NOT NULL,
	emailAddress VARCHAR(30) NOT NULL,
	privilegesLevel SMALLINT NOT NULL DEFAULT 10,
	PRIMARY KEY (userID),
	UNIQUE INDEX `login` (`login`)

)
ENGINE=InnoDB 
;

CREATE TABLE Language
(
	language VARCHAR(10) NOT NULL,
	context VARCHAR(30) NOT NULL,
	textID VARCHAR(50) NOT NULL,
	textValue VARCHAR(100) NOT NULL
)
ENGINE=InnoDB 
;


ALTER TABLE ProgramMMLibrary ADD CONSTRAINT FK_ProgramMMLibrary_Program 
	FOREIGN KEY (programID) REFERENCES Programs (programID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE ProgramMMLibrary ADD CONSTRAINT FK_ProgramMMLibrary_ProgramsLibrary 
	FOREIGN KEY (programLibraryID) REFERENCES ProgramsLibrary (programLibraryID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE CommandsLists ADD CONSTRAINT FK_CommandsLists_Users 
	FOREIGN KEY (userID) REFERENCES Users (userID)
	ON DELETE SET NULL ON UPDATE CASCADE
;

ALTER TABLE Commands ADD CONSTRAINT FK_Commands_CommandsLists 
	FOREIGN KEY (commandListID) REFERENCES CommandsLists (commandListID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Programs ADD CONSTRAINT FK_Program_User 
	FOREIGN KEY (userID) REFERENCES Users (userID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE ProgramsLibrary ADD CONSTRAINT FK_ProgramsLibrary_User 
	FOREIGN KEY (userID) REFERENCES Users (userID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Reply ADD CONSTRAINT FK_Reply_Topics 
	FOREIGN KEY (topicID) REFERENCES Topics (topicID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Reply ADD CONSTRAINT FK_Reply_User 
	FOREIGN KEY (userID) REFERENCES Users (userID)
;

ALTER TABLE Topics ADD CONSTRAINT FK_Topics_Categories 
	FOREIGN KEY (categoryID) REFERENCES Categories (categoryID)
;

ALTER TABLE Topics ADD CONSTRAINT FK_Topics_User 
	FOREIGN KEY (userID) REFERENCES Users (userID)
	ON DELETE NO ACTION ON UPDATE NO ACTION
;
