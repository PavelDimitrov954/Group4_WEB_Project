INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (1, 'todor', 'pass1', 'Todor', 'Andonov', 'todor@company.com', true, false);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (2, 'vladi', 'pass2', 'Vladi', 'Venkov', 'vladi@company.com', false, false);
INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin, is_blocked)
VALUES (3, 'pesho', 'pass3', 'Petar', 'Raykov', 'pesho@company.com', false, false);


INSERT INTO forum.posts (post_id, title, content, user_id)
VALUES (1, 'Title1', 'Description', 1);
INSERT INTO forum.posts (post_id, title, content, user_id)
VALUES (2, 'Title2', 'Description1', 2);
INSERT INTO forum.posts (post_id, title, content, user_id)
VALUES (3, 'Title3', 'Description2', 3);

INSERT INTO forum.comments (comment_id, content, user_id, post_id)
VALUES (1, 'TestsComment', 2, 1);
