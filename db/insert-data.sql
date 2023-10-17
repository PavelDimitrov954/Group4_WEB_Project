INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (1, 'todor', 'pass1', 'Todor', 'Andonov', 'todor@company.com', true);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (2, 'vladi', 'pass2', 'Vladi', 'Venkov', 'vladi@company.com', false);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (3, 'pesho', 'pass3', 'Petar', 'Raykov', 'pesho@company.com', false);


INSERT INTO forum.posts (post_id, title, content, user_id)
VALUES (1, 'Title1', 'Description', 1);
INSERT INTO forum.posts (post_id, title, content, user_id)
VALUES (2, 'Title2', 'Description1', 2);
INSERT INTO forum.posts (post_id, title, content, user_id)
VALUES (3, 'Title3', 'Description2', 3);
