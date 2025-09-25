-- ใช้ database ถ้ายังไม่มี
CREATE DATABASE IF NOT EXISTS itemdb;
USE itemdb;

-- ====== CATEGORY ======
INSERT INTO category (id, name) VALUES
(1, 'Programming'),
(2, 'Fantasy'),
(3, 'Science Fiction'),
(4, 'Psychology'),
(5, 'Classic'),
(6, 'Biography'),
(7, 'Horror'),
(8, 'Romance'),
(9, 'History'),
(10, 'Self-Help'),
(11, 'Action'),
(12, 'Animation'),
(13, 'Adventure');

-- ====== ITEM ======
-- Books
INSERT INTO item (id, dtype, type, title, creator, description) VALUES
(1, 'BOOK', 'BOOK', 'Clean Code', 'Robert C. Martin', 'A book about writing clean code'),
(2, 'BOOK', 'BOOK', 'The Pragmatic Programmer', 'Andrew Hunt', 'Programming best practices'),
(3, 'BOOK', 'BOOK', 'Harry Potter', 'J.K. Rowling', 'Wizard adventure');

-- Movies
INSERT INTO item (id, dtype, type, title, creator, description) VALUES
(4, 'MOVIE', 'MOVIE', 'Inception', 'Christopher Nolan', 'Mind-bending thriller'),
(5, 'MOVIE', 'MOVIE', 'The Matrix', 'Lana Wachowski', 'Sci-fi action movie');

-- Cartoons
INSERT INTO item (id, dtype, type, title, creator, description, studio) VALUES
(6, 'CARTOON', 'CARTOON', 'Tom and Jerry', 'William Hanna', 'Classic cartoon', 'Hanna-Barbera'),
(7, 'CARTOON', 'CARTOON', 'SpongeBob SquarePants', 'Stephen Hillenburg', 'Funny underwater cartoon', 'Nickelodeon');

-- Games
INSERT INTO item (id, dtype, type, title, creator, description, price) VALUES
(8, 'GAME', 'GAME', 'The Legend of Zelda', 'Nintendo', 'Adventure game', 60),
(9, 'GAME', 'GAME', 'Minecraft', 'Mojang', 'Sandbox building game', 30);


-- ====== ITEM_CATEGORIES (many-to-many) ======
INSERT INTO item_categories (item_id, category_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 11),
(5, 11),
(6, 12),
(7, 12),
(8, 13),
(9, 13);

-- ====== REVIEW ======
-- id, reviewer_name, rating, comment, item_id
INSERT INTO review (id, reviewer_name, rating, comment, item_id) VALUES
(1, 'Alice', 5, 'Excellent coding book', 1),
(2, 'Bob', 4, 'Very practical', 2),
(3, 'Charlie', 5, 'Magical story!', 3),
(4, 'Dave', 5, 'Amazing movie!', 4),
(5, 'Eve', 4, 'Classic sci-fi', 5),
(6, 'Frank', 5, 'Funny cartoon', 6),
(7, 'Grace', 4, 'My kids love it', 7),
(8, 'Heidi', 5, 'Epic adventure game', 8),
(9, 'Ivan', 4, 'Addictive sandbox', 9)
