INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (1, 'monika', 'pass123', 'Monika', 'Tincheva', 'monika_tincheva@abv.bg', true, false);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (2, 'pavel', 'pass456', 'Pavel', 'Dimitrov', 'pavel@company.com', true, false);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (3, 'borislav', 'pass789', 'Borislav', 'Chavdarov', 'boby@company.com', true, false);

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (4, 'todor', 'pass1', 'Todor', 'Andonov', 'todor@company.com', false, false);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (5, 'vladi', 'pass2', 'Vladi', 'Venkov', 'vladi@company.com', false, false);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (6, 'pesho', 'pass3', 'Petar', 'Raykov', 'pesho@company.com', false, false);

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (7, 'ivan', 'pass4', 'Ivan', 'Georgiev', 'ivan@company.com', false, false);



INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (1, 'Title1', 'Description', 1,current_timestamp);
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (2, 'Title2', 'Description1', 2, current_timestamp);
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (3, 'Title3', 'Description2', 3,current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (1, 'TestsComment', 2, 1, current_timestamp);
