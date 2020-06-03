DROP TABLE IF EXISTS books;
CREATE TABLE books
(
 id SERIAL NOT NULL,
 title varchar(100) NOT NULL,
 author varchar(100) DEFAULT NULL,
 PRIMARY KEY (id)
);