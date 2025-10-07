-- ใช้ database ถ้ายังไม่มี
CREATE DATABASE IF NOT EXISTS itemdb;
USE itemdb;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM item_categories;
DELETE FROM category;
DELETE FROM review;
DELETE FROM item;

-- ======================
-- CATEGORY
-- ======================
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

-- ======================
-- ITEMS
-- ======================

-- ===== BOOKS =====
INSERT INTO item (id, dtype, type, title, creator, description, price, imageUrl) VALUES
(1, 'BOOK', 'BOOK', 'Clean Code', 'Robert C. Martin', 'A book about writing clean code', 25.00,
 'https://m.media-amazon.com/images/I/41xShlnTZTL._SX374_BO1,204,203,200_.jpg'),
(2, 'BOOK', 'BOOK', 'The Pragmatic Programmer', 'Andrew Hunt', 'Programming best practices', 30.00,
 'https://m.media-amazon.com/images/I/41as+WafrFL._SX396_BO1,204,203,200_.jpg'),
(3, 'BOOK', 'BOOK', 'Harry Potter', 'J.K. Rowling', 'Wizard adventure', 20.00,
 'https://m.media-amazon.com/images/I/81YOuOGFCJL.jpg'),
(10, 'BOOK', 'BOOK', 'Thinking, Fast and Slow', 'Daniel Kahneman', 'A deep dive into how we think', 18.00,
 'https://image.makewebcdn.com/makeweb/m_1920x0/Z9S9L5BrM/DefaultData/Cover_Fast_Slow_Final_%E0%B8%AB%E0%B8%99%E0%B9%89%E0%B8%B2__2_.jpg'),
(11, 'BOOK', 'BOOK', 'Pride and Prejudice', 'Jane Austen', 'Classic romance novel', 15.00,
 'https://m.media-amazon.com/images/I/81Scutrtj4L._UF1000,1000_QL80_.jpg'),
(12, 'BOOK', 'BOOK', 'Steve Jobs', 'Walter Isaacson', 'Biography of Apple founder Steve Jobs', 28.00,
 'https://cdn.prod.website-files.com/659d508b6c4d0c10e7f62648/659d508b6c4d0c10e7f62715_book-main-02.jpg');

-- ===== MOVIES =====
INSERT INTO item (id, dtype, type, title, creator, description, price, imageUrl) VALUES
(4, 'MOVIE', 'MOVIE', 'Inception', 'Christopher Nolan', 'Mind-bending thriller', 12.50,
 'https://m.media-amazon.com/images/I/91pR9wKJ3zL._AC_SY679_.jpg'),
(5, 'MOVIE', 'MOVIE', 'The Matrix', 'Lana Wachowski', 'Sci-fi action movie', 10.00,
 'https://m.media-amazon.com/images/I/51EG732BV3L.jpg'),
(13, 'MOVIE', 'MOVIE', 'Titanic', 'James Cameron', 'Romantic tragedy on a doomed ship', 9.00,
 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlcQXin_CNozAFkv_SSihe5eZ_lvDD5nBqCmQT3xPf6KLlqHloIo5cBRGqwuy8pjuIiZrqoA'),
(14, 'MOVIE', 'MOVIE', 'The Conjuring', 'James Wan', 'Horror film based on true events', 8.50,
 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQhI3XoLyBJ9lHf-U8v4udQNmtNcM6iwuF-skSUrc7NxKhwg7FoWzTV2b-POylrd8hgW7_i'),
(15, 'MOVIE', 'MOVIE', 'Gladiator', 'Ridley Scott', 'Epic historical action film', 11.00,
 'https://upload.wikimedia.org/wikipedia/en/f/fb/Gladiator_%282000_film_poster%29.png');

-- ===== CARTOONS =====
INSERT INTO item (id, dtype, type, title, creator, description, price, imageUrl) VALUES
(6, 'CARTOON', 'CARTOON', 'Tom and Jerry', 'William Hanna', 'Classic cartoon', 8.00,
 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8IfDoflVbvIAC1vSqzNY7OFBWhkKXdSHSyw&s'),
(7, 'CARTOON', 'CARTOON', 'SpongeBob SquarePants', 'Stephen Hillenburg', 'Funny underwater cartoon', 7.50,
 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4P4cZGOpN_qDtf_Xzr9pFqXHtyuxlzmcnhQ&s'),
(16, 'CARTOON', 'CARTOON', 'Demon Slayer', 'Koyoharu Gotouge', 'Anime about demon hunters', 10.50,
 'https://a.storyblok.com/f/178900/750x1061/2698146e2e/a31cb4c1286b9186050265c156a547741624314075_main.png/m/filters:quality(95)format(webp)'),
(17, 'CARTOON', 'CARTOON', 'Attack on Titan', 'Hajime Isayama', 'Humanity fights titans', 11.00,
 'https://images.penguinrandomhouse.com/cover/9781612620244');

-- ===== GAMES =====
INSERT INTO item (id, dtype, type, title, creator, description, price, imageUrl) VALUES
(8, 'GAME', 'GAME', 'The Legend of Zelda', 'Nintendo', 'Adventure game', 60.00,
 'https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg'),
(9, 'GAME', 'GAME', 'Minecraft', 'Mojang', 'Sandbox building game', 30.00,
 'https://image.api.playstation.com/vulcan/ap/rnd/202407/0401/670c294ded3baf4fa11068db2ec6758c63f7daeb266a35a1.png'),
(18, 'GAME', 'GAME', 'Resident Evil 4', 'Capcom', 'Horror survival game', 45.00,
 'https://game.capcom.com/residentevil/pc/img/lineup/en/lineup-re4-ge.jpg'),
(19, 'GAME', 'GAME', 'Assassin’s Creed', 'Ubisoft', 'Historical action-adventure', 50.00,
 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZkih1gal9lBNig0xIf1bDjsudXOLAFQdjCw&s');

-- ======================
-- ITEM_CATEGORIES
-- ======================
INSERT INTO item_categories (item_id, category_id) VALUES
-- Books
(1, 1),
(2, 1),
(3, 2),
(10, 4),
(11, 8),
(12, 6),

-- Movies
(4, 11),
(5, 3),
(13, 8),
(14, 7),
(15, 9),

-- Cartoons
(6, 12),
(7, 12),
(16, 2),
(17, 13),

-- Games
(8, 13),
(9, 13),
(18, 7),
(19, 9);

-- ======================
-- REVIEWS
-- ======================
INSERT INTO review (id, reviewerName, rating, comment, item_id) VALUES
-- Books
(1, 'Alice', 5, 'Excellent coding book', 1),
(2, 'Bob', 4, 'Very practical and insightful', 2),
(3, 'Charlie', 5, 'Magical story with great characters', 3),
(4, 'Diana', 5, 'Makes you think differently about thinking', 10),
(5, 'Eva', 5, 'Romantic and timeless classic', 11),
(6, 'Frank', 4, 'Inspiring story of a visionary', 12),

-- Movies
(7, 'Grace', 5, 'Nolan never disappoints', 4),
(8, 'Henry', 4, 'Mind-blowing sci-fi', 5),
(9, 'Ivy', 5, 'Heartbreaking but beautiful love story', 13),
(10, 'Jack', 4, 'Terrifying atmosphere, great acting', 14),
(11, 'Karen', 5, 'Epic and emotional historical film', 15),

-- Cartoons
(12, 'Leo', 5, 'Classic humor that never gets old', 6),
(13, 'Mia', 4, 'Fun for all ages', 7),
(14, 'Nina', 5, 'Visually stunning and emotional', 16),
(15, 'Oscar', 5, 'Incredible plot and animation', 17),

-- Games
(16, 'Paul', 5, 'An unforgettable adventure', 8),
(17, 'Quinn', 5, 'Endless creativity in one game', 9),
(18, 'Rita', 4, 'Scary but fun gameplay', 18),
(19, 'Sam', 5, 'Beautiful world and storytelling', 19);

select * from item;