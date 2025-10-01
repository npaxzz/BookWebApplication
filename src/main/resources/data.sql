-- ใช้ database ถ้ายังไม่มี
CREATE DATABASE IF NOT EXISTS itemdb;
USE itemdb;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM category;
DELETE FROM item_categories;
DELETE FROM review;
DELETE FROM item;

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
INSERT INTO item (id, dtype, type, title, creator, description, imageUrl) VALUES
(1, 'BOOK', 'BOOK', 'Clean Code', 'Robert C. Martin', 'A book about writing clean code',
 'https://m.media-amazon.com/images/I/41xShlnTZTL._SX374_BO1,204,203,200_.jpg'),
(2, 'BOOK', 'BOOK', 'The Pragmatic Programmer', 'Andrew Hunt', 'Programming best practices',
 'https://m.media-amazon.com/images/I/41as+WafrFL._SX396_BO1,204,203,200_.jpg'),
(3, 'BOOK', 'BOOK', 'Harry Potter', 'J.K. Rowling', 'Wizard adventure',
 'https://m.media-amazon.com/images/I/81YOuOGFCJL.jpg');

-- Movies
INSERT INTO item (id, dtype, type, title, creator, description, imageUrl) VALUES
(4, 'MOVIE', 'MOVIE', 'Inception', 'Christopher Nolan', 'Mind-bending thriller',
 'https://m.media-amazon.com/images/I/91pR9wKJ3zL._AC_SY679_.jpg'),
(5, 'MOVIE', 'MOVIE', 'The Matrix', 'Lana Wachowski', 'Sci-fi action movie',
 'https://m.media-amazon.com/images/I/51EG732BV3L.jpg');

-- Cartoons
INSERT INTO item (id, dtype, type, title, creator, description, studio, imageUrl) VALUES
(6, 'CARTOON', 'CARTOON', 'Tom and Jerry', 'William Hanna', 'Classic cartoon', 'Hanna-Barbera', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8IfDoflVbvIAC1vSqzNY7OFBWhkKXdSHSyw&s'),
(7, 'CARTOON', 'CARTOON', 'SpongeBob SquarePants', 'Stephen Hillenburg', 'Funny underwater cartoon', 'Nickelodeon', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4P4cZGOpN_qDtf_Xzr9pFqXHtyuxlzmcnhQ&s');

-- Games
INSERT INTO item (id, dtype, type, title, creator, description, price, imageUrl) VALUES
(8, 'GAME', 'GAME', 'The Legend of Zelda', 'Nintendo', 'Adventure game', 60, 'https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg'),
(9, 'GAME', 'GAME', 'Minecraft', 'Mojang', 'Sandbox building game', 30, 'https://image.api.playstation.com/vulcan/ap/rnd/202407/0401/670c294ded3baf4fa11068db2ec6758c63f7daeb266a35a1.png');

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
INSERT INTO review (id, reviewerName, rating, comment, item_id) VALUES
(1, 'Alice', 5, 'Excellent coding book', 1),
(2, 'Bob', 4, 'Very practical', 2),
(3, 'Charlie', 5, 'Magical story!', 3),
(4, 'Dave', 5, 'Amazing movie!', 4),
(5, 'Eve', 4, 'Classic sci-fi', 5),
(6, 'Frank', 5, 'Funny cartoon', 6),
(7, 'Grace', 4, 'My kids love it', 7),
(8, 'Heidi', 5, 'Epic adventure game', 8),
(9, 'Ivan', 4, 'Addictive sandbox', 9)
