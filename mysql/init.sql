CREATE DATABASE IF NOT EXISTS geese_db;

use geese_db;

CREATE TABLE IF NOT EXISTS Goose
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(254) NOT NULL,
	verified BOOLEAN NOT NULL,
	password CHAR(64),
	salt CHAR(64),
	CONSTRAINT Goose_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Flock
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	authorid INT(16) NOT NULL,
	name VARCHAR(32),
	description VARCHAR(255),
	latitude FLOAT(10,6),
	longitude FLOAT(10,6),
	radius DOUBLE,
	CONSTRAINT Flock_pk PRIMARY KEY (id),
	CONSTRAINT Flock_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

CREATE TABLE IF NOT EXISTS Membership
(
	gooseid INT(16) NOT NULL,
	flockid INT(16) NOT NULL,
	CONSTRAINT Membership_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT Membership_Flock_fk FOREIGN KEY (flockid) REFERENCES Flock (id)
);

CREATE TABLE IF NOT EXISTS Topic
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	flockid INT(16) NOT NULL,
	authorid INT(16) NOT NULL,
	title VARCHAR(32),
	description VARCHAR(255),
	CONSTRAINT Topic_pk PRIMARY KEY (id),
	CONSTRAINT Topic_Flock_fk FOREIGN KEY (flockid) REFERENCES Flock (id),
	CONSTRAINT Topic_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

CREATE TABLE IF NOT EXISTS Post
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	topicid INT(16) NOT NULL,
	authorid INT(16) NOT NULL,
	text TEXT,
	CONSTRAINT Post_pk PRIMARY KEY (id),
	CONSTRAINT Post_Topic_fk FOREIGN KEY (topicid) REFERENCES Topic (id),
	CONSTRAINT Post_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

CREATE TABLE IF NOT EXISTS User
{
	id INT(16) NOT NULL AUTO_INCREMENT,
	email VARCHAR(255) NOT NULL,
	password CHAR(128) NOT NULL,
	CONSTRAINT User_pk PRIMARY KEY (id)	
};