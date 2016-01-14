use geese_db;

-- FB authentication will only have these 3 fields
INSERT INTO Goose (name, email, verified)
VALUES ('Ni', 'johnnyz@fb.com', 'true');

-- Regular email+password login will need all fields, and start without being verified
INSERT INTO Goose (name, email, verified, password, salt)
VALUES ('Johnny', 'n37zhang@uwaterloo.ca', 'true',
 '135E4B1ADE5120B673D57018D612BD573765D822FCAEE921AB5FAE8F7492592F', 
 '24BFD740FFBFEF5353A2188EC34F921203AEBB60B33C32914311B3D05525F104'
);

INSERT INTO Flock (authorid, name, description, latitude, longitude, radius, score, createdTime)
VALUES (1, 'New Flock', 'POST test', 12.34567, -1.123456, 1, -222, NOW());

INSERT INTO Membership (gooseid, flockid)
VALUES (1, 1);

INSERT INTO Topic (flockid, authorid, title, description, pinned, score, createdTime)
VALUES (1, 1, 'First', 'Insert a joke here', false, 222, NOW());

INSERT INTO Post (topicid, authorid, text, score, createdTime, expireTime)
VALUES (1, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 222, NOW(), NOW());

INSERT INTO Comment (postid, authorid, text, score, createdTime)
VALUES (1, 1, 'Latin is my favourite Indo-European language, though I am also fond of Farsi.', 420, NOW());

INSERT INTO FlockVote (gooseid, flockid)
VALUES (1, 1);

INSERT INTO TopicVote (gooseid, topicid)
VALUES (1, 1);

INSERT INTO PostVote (gooseid, postid)
VALUES (1, 1);

insert into Goose(name, email, verified, password, salt)
  values("JMtorii", "test@test.com", true, "password", "salt");

insert into Flock(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)
  values(2, 'Waterloo Soccer', 'Description for Soccer', 43.472036, -80.544965, 2, 1, '2000-01-01 00:00:00', '2010-01-01 00:00:00');

insert into Flock(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)
  values(2, 'Waterloo Basketball', 'Description for Basketball', 43.474637, -80.548055, 2, 1, '2000-01-01 00:00:00', '2010-01-01 00:00:00');

insert into Flock(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)
  values(2, 'Waterloo Football', 'Description for Football', 43.474364, -80.543280, 2, 1, '2000-01-01 00:00:00', '2010-01-01 00:00:00');

insert into Flock(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)
  values(2, 'Waterloo Book Lovers', 'Description for Book Lovers', 43.470642, -80.534404, 2, 1, '2000-01-01 00:00:00', '2010-01-01 00:00:00');

insert into Flock(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)
  values(2, 'Waterloo Science Lovers', 'Description for Science Lovers', 43.481466, -80.548964, 2, 1, '2000-01-01 00:00:00', '2010-01-01 00:00:00');

insert into Flock(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)
  values(2, 'Waterloo Losers', 'Description for Losers', 43.472031, -80.544965, 2, 1, '2000-01-01 00:00:00', '2010-01-01 00:00:00');