CREATE DATABASE IF NOT EXISTS bookdb;
USE bookdb;

-- insert categories
INSERT INTO category (id, name) VALUES
(1, 'Programming'),
(2, 'Fantasy'),
(3, 'Psychology'),
(4, 'Classic'),
(5, 'Biography'),
(6, 'Horror'),
(7, 'Romance'),
(8, 'History'),
(9, 'Self-Help');

-- insert books
INSERT INTO book (id, title, author, description) VALUES
(1, 'Clean Code', 'Robert C. Martin', 'A book about writing clean code'),
(2, 'The Pragmatic Programmer', 'Andrew Hunt', 'A classic programming book'),
(3, 'Harry Potter', 'J.K. Rowling', 'A fantasy book'),
(4, 'Thinking, Fast and Slow', 'Daniel Kahneman', 'Psychology book'),
(5, 'To Kill a Mockingbird', 'Harper Lee', 'Classic novel'),
(6, 'Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy novel'),
(7, 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy novel'),
(8, '1984', 'George Orwell', 'Classic dystopian novel'),
(9, 'The Diary of a Young Girl', 'Anne Frank', 'Biography'),
(10, 'Dracula', 'Bram Stoker', 'Horror novel'),
(11, 'Pride and Prejudice', 'Jane Austen', 'Romance novel'),
(12, 'Sapiens', 'Yuval Noah Harari', 'History book'),
(13, '7 Habits', 'Stephen Covey', 'Self-Help book'),
(14, 'Brave New World', 'Aldous Huxley', 'Classic dystopian novel');

-- insert book_category
INSERT INTO book_category (book_id, category_id) VALUES
(1, 1),  -- Clean Code -> Programming
(2, 1),  -- The Pragmatic Programmer -> Programming
(3, 2),  -- Harry Potter -> Fantasy
(4, 3),  -- Thinking, Fast and Slow -> Psychology
(5, 4),  -- To Kill a Mockingbird -> Classic
(6, 2),  -- Lord of the Rings -> Fantasy
(6, 4),  -- Lord of the Rings -> Classic
(7, 2),  -- The Hobbit -> Fantasy
(8, 4),  -- 1984 -> Classic
(9, 5),  -- The Diary of a Young Girl -> Biography
(10, 6), -- Dracula -> Horror
(11, 7), -- Pride and Prejudice -> Romance
(12, 8), -- Sapiens -> History
(13, 9), -- 7 Habits -> Self-Help
(14, 4), -- Brave New World -> Classic
(14, 2); -- Brave New World -> Fantasy