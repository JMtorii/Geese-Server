CREATE DATABASE IF NOT EXISTS geese_db;

use geese_db;

CREATE TABLE IF NOT EXISTS Goose
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	uuid CHAR(36) NOT NULL,
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
	range DOUBLE,
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

INSERT INTO Goose (uuid)
VALUES ('f81d4fae-7dec-11d0-a765-00a0c91e6bf6');

INSERT INTO Flock (authorid, name, description, latitude, longitude, range)
VALUES (1, 'New Flock', 'POST test', 12.34567, -1.123456, 1);

INSERT INTO Membership (gooseid, flockid)
VALUES (1, 1);

INSERT INTO Topic (flockid, authorid, title, description)
VALUES (1, 1, 'First', 'Insert a joke here');

INSERT INTO Post (topicid, authorid, text)
VALUES (1, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
