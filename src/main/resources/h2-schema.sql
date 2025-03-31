DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

CREATE TABLE author (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        first_name VARCHAR(255),
                        last_name VARCHAR(255)
);

CREATE TABLE book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      isbn VARCHAR(255),
                      publisher VARCHAR(255),
                      title VARCHAR(255),
                      author_id BIGINT
);