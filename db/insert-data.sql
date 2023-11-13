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
VALUES (21, 'Title1', 'Description', 1,current_timestamp);
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (22, 'Title2', 'Description1', 2, current_timestamp);
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (23, 'Title3', 'Description2', 3,current_timestamp);
-- Post 1
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (1, 'Inception', 'A mind-bending journey into the world of dreams.', 1, current_timestamp);

-- Post 2
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (2, 'The Shawshank Redemption', 'A tale of hope, friendship, and redemption in the prison walls.', 2, current_timestamp);

-- Post 3
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (3, 'The Dark Knight', 'Witness the epic battle between Batman and the Joker in Gotham City.', 3, current_timestamp);

-- Post 4
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (4, 'Pulp Fiction', 'A non-linear narrative that weaves together various crime stories.', 4, current_timestamp);

-- Post 5
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (5, 'The Godfather', 'An epic portrayal of the Corleone family and their Mafia empire.', 5, current_timestamp);

-- Post 6
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (6, 'Titanic', 'A tragic love story set against the backdrop of the ill-fated maiden voyage.', 1, current_timestamp);

-- Post 7
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (7, 'Avatar', 'Explore the lush world of Pandora in this visually stunning sci-fi epic.', 2, current_timestamp);

-- Post 8
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (8, 'Forrest Gump', 'Follow the extraordinary life journey of Forrest Gump.', 3, current_timestamp);

-- Post 9
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (9, 'The Matrix', 'A mind-bending sci-fi journey into a simulated reality.', 4, current_timestamp);

-- Post 10
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (10, 'Casablanca', 'A classic tale of love and sacrifice in wartime Morocco.', 5, current_timestamp);

-- Post 11
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (11, 'The Silence of the Lambs', 'An intense psychological thriller featuring Hannibal Lecter.', 1, current_timestamp);

-- Post 12
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (12, 'Schindler\'s List', 'A powerful portrayal of Oskar Schindler\'s efforts to save Jews during the Holocaust.', 2, current_timestamp);

-- Post 13
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (13, 'The Lord of the Rings: The Fellowship of the Ring', 'Embark on a epic journey through Middle-earth with Frodo and his companions.', 3, current_timestamp);

-- Post 14
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (14, 'Eternal Sunshine of the Spotless Mind', 'A unique romantic drama exploring the erasure of memories.', 4, current_timestamp);

-- Post 15
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (15, 'The Great Gatsby', 'A visually stunning adaptation of F. Scott Fitzgerald\'s classic novel.', 5, current_timestamp);

-- Post 16
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (16, 'The Social Network', 'Explore the founding and rise of Facebook in this gripping drama.', 1, current_timestamp);

-- Post 17
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (17, 'The Revenant', 'A tale of survival and revenge in the harsh wilderness.', 2, current_timestamp);

-- Post 18
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (18, 'The Grand Budapest Hotel', 'A quirky and stylish comedy set in a fictional European hotel.', 3, current_timestamp);

-- Post 19
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (19, 'La La Land', 'A modern musical that captures the dreams and challenges of aspiring artists.', 4, current_timestamp);

-- Post 20
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (20, 'Jaws', 'Experience the suspense and terror of a great white shark terrorizing a seaside town.', 5, current_timestamp);


INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (1, 'TestsComment', 2, 1, current_timestamp);
