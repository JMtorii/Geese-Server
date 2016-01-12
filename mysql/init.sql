CREATE DATABASE IF NOT EXISTS geese_db;

use geese_db;

-- Table for Users of the app
CREATE TABLE IF NOT EXISTS Goose
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL, -- First/Last/Screen name
	email VARCHAR(254) NOT NULL, -- Registered email
	verified BOOLEAN NOT NULL, -- Whether the email is verified
	password CHAR(64), -- 32 byte hash (sha256)
	salt CHAR(64), -- random 32 bytes
	CONSTRAINT Goose_pk PRIMARY KEY (id)
);

-- Table for Groups in the app
CREATE TABLE IF NOT EXISTS Flock
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	authorid INT(16) NOT NULL,
	name VARCHAR(32) NOT NULL, -- Name of Flock
	description VARCHAR(255) NOT NULL, -- Short description of Flock
	latitude FLOAT(10,6) NOT NULL, -- Latitude of Flock creation point
	longitude FLOAT(10,6) NOT NULL, -- Longitude of Flock creation point
	radius DOUBLE NOT NULL, -- Range in meters for discovery of Flock from creation point
	score INT(16) NOT NULL, -- Up/down votes
	createdTime DATETIME NOT NULL, -- Time of flock creation (NOTE: ALL TIMES ARE IN UTC-0)
	expireTime DATETIME, -- Time the flock expires, null if never
	CONSTRAINT Flock_pk PRIMARY KEY (id),
	CONSTRAINT Flock_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

-- Table mapping Users to Flocks they belong to
CREATE TABLE IF NOT EXISTS Membership
(
	gooseid INT(16) NOT NULL,
	flockid INT(16) NOT NULL,
	CONSTRAINT Membership_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT Membership_Flock_fk FOREIGN KEY (flockid) REFERENCES Flock (id)
);

-- Table for a topic or thread within a Flock
CREATE TABLE IF NOT EXISTS Topic
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	flockid INT(16) NOT NULL,
	authorid INT(16) NOT NULL,
	title VARCHAR(32) NOT NULL, -- Title to be displayed
	description VARCHAR(255) NOT NULL, -- Short description of topic
	pinned BOOLEAN NOT NULL, -- Display topic at the top of the list
	score INT(16) NOT NULL, -- Up/down votes
	createdTime DATETIME NOT NULL, -- Time of topic creation
	startTime DATETIME NOT NULL, -- Start of event (if relevant)
	endTime DATETIME NOT NULL, -- End of event (if relevant)
	CONSTRAINT Topic_pk PRIMARY KEY (id),
	CONSTRAINT Topic_Flock_fk FOREIGN KEY (flockid) REFERENCES Flock (id),
	CONSTRAINT Topic_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

-- Table mapping Users to Events they belong to
CREATE TABLE IF NOT EXISTS EventMembership
(
	gooseid INT(16) NOT NULL,
	topicid INT(16) NOT NULL,
	CONSTRAINT EventMembership_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT EventMembership_Event_fk FOREIGN KEY (topicid) REFERENCES Topic (id)
);

-- Table for posts of a topic
CREATE TABLE IF NOT EXISTS Post
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	topicid INT(16) NOT NULL,
	authorid INT(16) NOT NULL,
	text TEXT NOT NULL, -- Content of post
	score INT(16) NOT NULL, -- Up/down votes
	createdTime DATETIME NOT NULL, -- Time of post creation
	expireTime DATETIME NOT NULL, -- Time of post expiry
	CONSTRAINT Post_pk PRIMARY KEY (id),
	CONSTRAINT Post_Topic_fk FOREIGN KEY (topicid) REFERENCES Topic (id),
	CONSTRAINT Post_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

-- Table for comments on a post
CREATE TABLE IF NOT EXISTS Comment
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	postid INT(16) NOT NULL,
	authorid INT(16) NOT NULL,
	text TEXT NOT NULL, -- Content of post
	score INT(16) NOT NULL, -- Up/down votes
	createdTime DATETIME NOT NULL, -- Time of post creation
	CONSTRAINT Comment_pk PRIMARY KEY (id),
	CONSTRAINT Comment_Post_fk FOREIGN KEY (postid) REFERENCES Post (id),
	CONSTRAINT Comment_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

-- Table for Flock votes
CREATE TABLE IF NOT EXISTS FlockVote
(
	gooseid INT(16) NOT NULL,
	flockid INT(16) NOT NULL,
	CONSTRAINT FlockVote_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT FlockVote_Flock_fk FOREIGN KEY (flockid) REFERENCES Flock (id)
);

-- Table for Topic votes
CREATE TABLE IF NOT EXISTS TopicVote
(
	gooseid INT(16) NOT NULL,
	topicid INT(16) NOT NULL,
	CONSTRAINT TopicVote_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT TopicVote_Topic_fk FOREIGN KEY (topicid) REFERENCES Topic (id)
);

-- Table for Post votes
CREATE TABLE IF NOT EXISTS PostVote
(
	gooseid INT(16) NOT NULL,
	postid INT(16) NOT NULL,
	CONSTRAINT PostVote_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT PostVote_Post_fk FOREIGN KEY (postid) REFERENCES Post (id)
);

