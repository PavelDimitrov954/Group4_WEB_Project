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
VALUES (1, 'Inception!!!!!!!!!!!!!!!!!!!', 'A mind-bending journey into the world of dreams.', 1, current_timestamp);

-- Post 2
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (2, 'The Shawshank Redemption!!!!!!!!!!!!!', 'A tale of hope, friendship, and redemption in the prison walls.', 2, current_timestamp);

-- Post 3
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (3, 'The Dark Knight!!!!!!!!!!!!!', 'Witness the epic battle between Batman and the Joker in Gotham City.', 3, current_timestamp);

-- Post 4
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (4, 'Pulp Fiction!!!!!!!!!!!!!', 'A non-linear narrative that weaves together various crime stories.', 4, current_timestamp);

-- Post 5
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (5, 'The Godfather!!!!!!!!!!!!!', 'An epic portrayal of the Corleone family and their Mafia empire.', 5, current_timestamp);

-- Post 6
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (6, 'Titanic!!!!!!!!!!!!!', 'A tragic love story set against the backdrop of the ill-fated maiden voyage.', 1, current_timestamp);

-- Post 7
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (7, 'Avatar!!!!!!!!!!!!!', 'Explore the lush world of Pandora in this visually stunning sci-fi epic.', 2, current_timestamp);

-- Post 8
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (8, 'Forrest Gump!!!!!!!!!!!!!', 'Follow the extraordinary life journey of Forrest Gump.', 3, current_timestamp);

-- Post 9
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (9, 'The Matrix!!!!!!!!!!!!!', 'A mind-bending sci-fi journey into a simulated reality.', 4, current_timestamp);

-- Post 10
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (10, 'Casablanca!!!!!!!!!!!!!', 'A classic tale of love and sacrifice in wartime Morocco.', 5, current_timestamp);

-- Post 11
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (11, 'The Silence of the Lambs!!!!!!!!!!!!!', 'An intense psychological thriller featuring Hannibal Lecter.', 1, current_timestamp);

-- Post 12
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (12, 'Schindler\'s List!!!!!!!!!!!!!', 'A powerful portrayal of Oskar Schindler\'s efforts to save Jews during the Holocaust.', 2, current_timestamp);

-- Post 13
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (13, 'The Lord of the Rings: The Fellowship of the Ring!!!!!!!!!!!!!', 'Embark on a epic journey through Middle-earth with Frodo and his companions.', 3, current_timestamp);

-- Post 14
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (14, 'Eternal Sunshine of the Spotless Mind!!!!!!!!!!!!!', 'A unique romantic drama exploring the erasure of memories.', 4, current_timestamp);

-- Post 15
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (15, 'The Great Gatsby!!!!!!!!!!!!!', 'A visually stunning adaptation of F. Scott Fitzgerald\'s classic novel.', 5, current_timestamp);

-- Post 16
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (16, 'The Social Network!!!!!!!!!!!!!', 'Explore the founding and rise of Facebook in this gripping drama.', 1, current_timestamp);

-- Post 17
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (17, 'The Revenant!!!!!!!!!!!!!', 'A tale of survival and revenge in the harsh wilderness.', 2, current_timestamp);

-- Post 18
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (18, 'The Grand Budapest Hotel!!!!!!!!!!!!!', 'A quirky and stylish comedy set in a fictional European hotel.', 3, current_timestamp);

-- Post 19
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (19, 'La La Land!!!!!!!!!!!!!', 'A modern musical that captures the dreams and challenges of aspiring artists.', 4, current_timestamp);

-- Post 20
INSERT INTO forum.posts (post_id, title, content, user_id, create_date)
VALUES (20, 'Jaws!!!!!!!!!!!!!', 'Experience the suspense and terror of a great white shark terrorizing a seaside town.', 5, current_timestamp);


-- Comments for Post 1
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (1, 'Inception is a masterpiece! The concept of dreams within dreams was mind-blowing.', 2, 1, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (2, 'Leonardo DiCaprio\'s performance in Inception was outstanding. He truly brought the character to life.', 3, 1, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (3, 'The ending of Inception is still a topic of debate among my friends. What are your thoughts?', 4, 1, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (4, 'Hans Zimmer\'s score for Inception added so much to the atmosphere. Do you have a favorite track?', 5, 1, current_timestamp);

-- Comments for Post 2
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (5, 'The Shawshank Redemption is a cinematic gem. The storytelling is powerful and moving.', 2, 2, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (6, 'Morgan Freeman\'s narration in The Shawshank Redemption is iconic. It adds a whole new layer to the film.', 3, 2, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (7, 'The friendship between Andy and Red is one of the most heartwarming aspects of the movie. Agree?', 4, 2, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (8, 'The ending of The Shawshank Redemption left a lasting impression on me. What about you?', 5, 2, current_timestamp);

-- Comments for Post 3
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (9, 'Heath Ledger\'s portrayal of the Joker in The Dark Knight was absolutely mesmerizing.', 2, 3, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (10, 'Christopher Nolan\'s direction in The Dark Knight elevated the superhero genre. Thoughts?', 3, 3, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (11, 'Christian Bale as Batman brought a depth to the character. Do you have a favorite Batman moment in the film?', 4, 3, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (12, 'The Dark Knight\'s impact on the superhero genre is undeniable. What other superhero movies do you enjoy?', 5, 3, current_timestamp);

-- Comments for Post 4
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (13, 'Pulp Fiction\'s non-linear storytelling is both chaotic and brilliant. What\'s your favorite scene?', 2, 4, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (14, 'The ensemble cast in Pulp Fiction delivered outstanding performances. Who was your favorite character?', 3, 4, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (15, 'Quentin Tarantino\'s unique style shines in Pulp Fiction. Did you enjoy the eclectic soundtrack?', 4, 4, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (16, 'Pulp Fiction\'s dialogue is sharp and memorable. Share your favorite quote from the movie!', 5, 4, current_timestamp);

-- Comments for Post 5
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (17, 'The Godfather is a cinematic masterpiece. Marlon Brando\'s performance is iconic.', 2, 5, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (18, 'The Corleone family saga is both tragic and compelling. Which Godfather film is your favorite?', 3, 5, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (19, 'The Godfather\'s influence on the gangster genre is undeniable. How did you feel about the ending?', 4, 5, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (20, 'Al Pacino\'s transformation as Michael Corleone is remarkable. Thoughts on his character arc?', 5, 5, current_timestamp);
-- Comments for Post 6
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (21, 'Titanic\'s love story touched hearts worldwide. Did you find the ending emotional?', 2, 6, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (22, 'Leonardo DiCaprio and Kate Winslet\'s chemistry was captivating. Have you watched Titanic more than once?', 3, 6, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (23, 'The sinking of the Titanic was portrayed with incredible detail. What was the most memorable scene for you?', 4, 6, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (24, 'Titanic\'s cinematography and visual effects were groundbreaking for its time. Agree?', 5, 6, current_timestamp);

-- Comments for Post 7
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (25, 'Avatar\'s world of Pandora was visually stunning. Did you watch it in 3D?', 2, 7, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (26, 'James Cameron\'s vision for Avatar was ahead of its time. Are you excited for the upcoming sequels?', 3, 7, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (27, 'The environmental message in Avatar was powerful. What are your thoughts on the film\'s themes?', 4, 7, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (28, 'Avatar\'s success at the box office made it a cultural phenomenon. Did it live up to the hype for you?', 5, 7, current_timestamp);

-- Comments for Post 8
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (29, 'Forrest Gump\'s journey through history is heartwarming. Which historical moment touched you the most?', 2, 8, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (30, 'Tom Hanks\' performance as Forrest Gump is iconic. What aspect of his character resonated with you?', 3, 8, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (31, 'The soundtrack of Forrest Gump adds so much to the film\'s emotional impact. Do you have a favorite song?', 4, 8, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (32, 'Forrest Gump\'s simplicity and innocence are a refreshing contrast to the complex world around him. Thoughts?', 5, 8, current_timestamp);

-- Comments for Post 9
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (33, 'The Matrix revolutionized action cinema. What was your favorite action sequence?', 2, 9, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (34, 'Keanu Reeves as Neo was a perfect casting choice. Did you know he did most of his own stunts?', 3, 9, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (35, 'The concept of the Matrix is mind-bending. How did it influence your perception of reality?', 4, 9, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (36, 'The Matrix\'s visual effects were groundbreaking. Do you think it still holds up today?', 5, 9, current_timestamp);

-- Comments for Post 10
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (37, 'The Silence of the Lambs is a psychological thriller masterpiece. How did Anthony Hopkins\' performance impact you?', 2, 10, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (38, 'Jodie Foster as Clarice Starling was exceptional. What did you think of the dynamic between her and Hannibal Lecter?', 3, 10, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (39, 'The suspense in The Silence of the Lambs is palpable. Did it keep you on the edge of your seat?', 4, 10, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (40, 'The Silence of the Lambs\' impact on the thriller genre is undeniable. How do you think it compares to other psychological thrillers?', 5, 10, current_timestamp);

-- Comments for Post 11
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (41, 'Schindler\'s List is a powerful portrayal of humanity. What scene had the most impact on you?', 2, 11, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (42, 'Liam Neeson\'s performance as Oskar Schindler was remarkable. How did the character resonate with you?', 3, 11, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (43, 'The black-and-white cinematography in Schindler\'s List adds to its emotional weight. Agree?', 4, 11, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (44, 'Schindler\'s List is a difficult watch but an essential one. How do you think it contributes to Holocaust education?', 5, 11, current_timestamp);

-- Comments for Post 12
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (45, 'Forrest Gump\'s journey through history is heartwarming. Which historical moment touched you the most?', 2, 12, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (46, 'Tom Hanks\' performance as Forrest Gump is iconic. What aspect of his character resonated with you?', 3, 12, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (47, 'The soundtrack of Forrest Gump adds so much to the film\'s emotional impact. Do you have a favorite song?', 4, 12, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (48, 'Forrest Gump\'s simplicity and innocence are a refreshing contrast to the complex world around him. Thoughts?', 5, 12, current_timestamp);

-- Comments for Post 13
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (49, 'The Matrix revolutionized action cinema. What was your favorite action sequence?', 2, 13, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (50, 'Keanu Reeves as Neo was a perfect casting choice. Did you know he did most of his own stunts?', 3, 13, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (51, 'The concept of the Matrix is mind-bending. How did it influence your perception of reality?', 4, 13, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (52, 'The Matrix\'s visual effects were groundbreaking. Do you think it still holds up today?', 5, 13, current_timestamp);

-- Comments for Post 14
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (53, 'Inglourious Basterds is a Tarantino masterpiece. What was your favorite dialogue-heavy scene?', 2, 14, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (54, 'Brad Pitt\'s portrayal of Lt. Aldo Raine was memorable. Which Basterd was your favorite character?', 3, 14, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (55, 'Quentin Tarantino\'s signature style shines in Inglourious Basterds. Did you appreciate the alternate history twist?', 4, 14, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (56, 'The tension in the bar scene was palpable. How did it make you feel knowing the undercover plan?', 5, 14, current_timestamp);

-- Comments for Post 15
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (57, 'Fight Club\'s plot twists are mind-bending. Did you see them coming?', 2, 15, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (58, 'Edward Norton and Brad Pitt\'s performances in Fight Club were exceptional. Which character resonated with you more?', 3, 15, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (59, 'The cinematography and visual style of Fight Club are unique. How did it contribute to the film\'s atmosphere?', 4, 15, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (60, 'Fight Club\'s commentary on consumerism is thought-provoking. What aspect of the film stood out to you the most?', 5, 15, current_timestamp);

-- Comments for Post 16
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (61, 'Forrest Gump\'s simplicity and innocence are a refreshing contrast to the complex world around him. Thoughts?', 2, 16, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (62, 'Tom Hanks\' performance as Forrest Gump is iconic. What aspect of his character resonated with you?', 3, 16, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (63, 'The soundtrack of Forrest Gump adds so much to the film\'s emotional impact. Do you have a favorite song?', 4, 16, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (64, 'Forrest Gump\'s journey through history is heartwarming. Which historical moment touched you the most?', 5, 16, current_timestamp);

-- Comments for Post 17
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (65, 'The Matrix revolutionized action cinema. What was your favorite action sequence?', 2, 17, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (66, 'Keanu Reeves as Neo was a perfect casting choice. Did you know he did most of his own stunts?', 3, 17, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (67, 'The concept of the Matrix is mind-bending. How did it influence your perception of reality?', 4, 17, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (68, 'The Matrix\'s visual effects were groundbreaking. Do you think it still holds up today?', 5, 17, current_timestamp);

-- Comments for Post 18
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (69, 'The Dark Knight is a cinematic masterpiece. What was your favorite performance in the film?', 2, 18, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (70, 'Heath Ledger\'s portrayal of the Joker is legendary. How did it impact your perception of the character?', 3, 18, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (71, 'Christopher Nolan\'s direction in The Dark Knight is phenomenal. What other Nolan films do you enjoy?', 4, 18, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (72, 'The Dark Knight\'s impact on the superhero genre is undeniable. How do you think it changed the landscape of comic book movies?', 5, 18, current_timestamp);

-- Comments for Post 19
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (73, 'The Godfather is a classic. Which scene left a lasting impression on you?', 2, 19, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (74, 'Marlon Brando\'s performance as Vito Corleone is iconic. What did you think of his portrayal?', 3, 19, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (75, 'The Godfather trilogy is hailed as one of the greatest. Which installment is your favorite?', 4, 19, current_timestamp);

INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (76, 'The Godfather\'s influence on mob movies is evident. How do you think it compares to other films in the genre?', 5, 19, current_timestamp);

-- Comments for Post 20
INSERT INTO forum.comments (comment_id, content, user_id, post_id, create_date)
VALUES (77, 'Shawshank Redemption is a timeless classic. What resonated with you the most in the film?', 2, 20, current_timestamp);







