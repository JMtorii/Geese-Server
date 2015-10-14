use geese_db;

-- FB authentication will only have these 3 fields
INSERT INTO Goose (name, email, verified)
VALUES ('Ni', 'johnnyz@fb.com', 'true');

-- Regular email+password login will need all fields, and start without being verified
INSERT INTO Goose (name, email, verified, password, salt)
VALUES ('Johnny', 'n37zhang@uwaterloo.ca', 'true',
 '85b02396c87fde331d0dbd9e99d9fb5468924460e1b6f6efa1dea2b68a6c8c12', 
 '71680c71b244a1b88784e2890016fdb12617d35f20c01d02b25d6861304cff99'
);

INSERT INTO Flock (authorid, name, description, latitude, longitude, radius)
VALUES (1, 'New Flock', 'POST test', 12.34567, -1.123456, 1);

INSERT INTO Membership (gooseid, flockid)
VALUES (1, 1);

INSERT INTO Topic (flockid, authorid, title, description)
VALUES (1, 1, 'First', 'Insert a joke here');

INSERT INTO Post (topicid, authorid, text)
VALUES (1, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');