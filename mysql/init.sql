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
	CONSTRAINT Goose_pk PRIMARY KEY (id),
	CONSTRAINT Goose_Email_unique UNIQUE (email)
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

-- Table for a post within a Flock
CREATE TABLE IF NOT EXISTS Post
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	flockid INT(16) NOT NULL,
	authorid INT(16) NOT NULL,
	title VARCHAR(32) NOT NULL, -- Title to be displayed
	description VARCHAR(255) NOT NULL, -- Short description of post
	pinned BOOLEAN NOT NULL, -- Display post at the top of the list
	score INT(16) NOT NULL, -- Up/down votes
	createdTime DATETIME NOT NULL, -- Time of post creation
	startTime DATETIME, -- Start of event (if relevant)
	endTime DATETIME, -- End of event (if relevant)
	CONSTRAINT Post_pk PRIMARY KEY (id),
	CONSTRAINT Post_Flock_fk FOREIGN KEY (flockid) REFERENCES Flock (id),
	CONSTRAINT Post_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

-- Table mapping Users to Events they belong to
CREATE TABLE IF NOT EXISTS EventMembership
(
	gooseid INT(16) NOT NULL,
	postid INT(16) NOT NULL,
	CONSTRAINT EventMembership_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT EventMembership_Event_fk FOREIGN KEY (postid) REFERENCES Post (id)
);

-- Table for comment of a comment
CREATE TABLE IF NOT EXISTS Comment
(
	id INT(16) NOT NULL AUTO_INCREMENT,
	commentid INT(16) NOT NULL,
	authorid INT(16) NOT NULL,
	text TEXT NOT NULL, -- Content of comment
	score INT(16) NOT NULL, -- Up/down votes
	createdTime DATETIME NOT NULL, -- Time of comment creation
	expireTime DATETIME NOT NULL, -- Time of comment expiry
	CONSTRAINT Comment_pk PRIMARY KEY (id),
	CONSTRAINT Comment_Post_fk FOREIGN KEY (commentid) REFERENCES Post (id),
	CONSTRAINT Comment_Author_fk FOREIGN KEY (authorid) REFERENCES Goose (id)
);

-- Table for Post votes
CREATE TABLE IF NOT EXISTS PostVote
(
	gooseid INT(16) NOT NULL,
	postid INT(16) NOT NULL,
	CONSTRAINT PostVote_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT PostVote_Post_fk FOREIGN KEY (postid) REFERENCES Post (id)
);

-- Table for Comment votes
CREATE TABLE IF NOT EXISTS CommentVote
(
	gooseid INT(16) NOT NULL,
	commentid INT(16) NOT NULL,
	CONSTRAINT CommentVote_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT CommentVote_Comment_fk FOREIGN KEY (commentid) REFERENCES Comment (id)
);

-- Table for FavouritedFlocks
CREATE TABLE IF NOT EXISTS FavouritedFlocks
(
	gooseid INT(16) NOT NULL,
	flockid INT(16) NOT NULL,
	CONSTRAINT FavouritedFlocks_Goose_fk FOREIGN KEY (gooseid) REFERENCES Goose (id),
	CONSTRAINT FavouritedFlocks_Flock_fk FOREIGN KEY (flockid) REFERENCES Flock (id)
);