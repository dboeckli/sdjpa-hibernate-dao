drop table if exists book cascade;
drop table if exists author;

create table book
(
    id        bigint not null auto_increment primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    author_id BIGINT
) engine = InnoDB;

create table author
(
    id         bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name  varchar(255)
) engine = InnoDB;

alter table book
    add constraint book_author_fk foreign key (author_id) references author (id);

-- Autoren
INSERT INTO author (id, first_name, last_name) VALUES (1, 'Craig', 'Walls');
INSERT INTO author (id, first_name, last_name) VALUES (2, 'Eric', 'Evans');
INSERT INTO author (id, first_name, last_name) VALUES (3, 'Robert', 'Martin');
INSERT INTO author (id, first_name, last_name) VALUES (4, 'Martin', 'Fowler');
INSERT INTO author (id, first_name, last_name) VALUES (5, 'Kent', 'Beck');
INSERT INTO author (id, first_name, last_name) VALUES (6, 'Joshua', 'Bloch');
INSERT INTO author (id, first_name, last_name) VALUES (7, 'Gayle', 'McDowell');

-- Autoren Smith
insert into author (first_name, last_name) values ('Robert', 'Smith');
insert into author (first_name, last_name) values ('Alexander', 'Smith');
insert into author (first_name, last_name) values ('Krausz', 'Smith');
insert into author (first_name, last_name) values ('Valentin', 'Smith');
insert into author (first_name, last_name) values ('Ahmed', 'Smith');
insert into author (first_name, last_name) values ('Tishara', 'Smith');
insert into author (first_name, last_name) values ('Yakovlev', 'Smith');
insert into author (first_name, last_name) values ('Claudiu', 'Smith');
insert into author (first_name, last_name) values ('Dinesh', 'Smith');
insert into author (first_name, last_name) values ('Arimardan', 'Smith');
insert into author (first_name, last_name) values ('Prasath', 'Smith');
insert into author (first_name, last_name) values ('Mekandor', 'Smith');
insert into author (first_name, last_name) values ('Valentin', 'Smith');
insert into author (first_name, last_name) values ('Ahmed', 'Smith');
insert into author (first_name, last_name) values ('Tishara', 'Smith');
insert into author (first_name, last_name) values ('Alberto', 'Smith');
insert into author (first_name, last_name) values ('Roberto', 'Smith');
insert into author (first_name, last_name) values ('Barbara', 'Smith');
insert into author (first_name, last_name) values ('Stepan', 'Smith');
insert into author (first_name, last_name) values ('Dhanunjaya', 'Smith');
insert into author (first_name, last_name) values ('Christian', 'Smith');
insert into author (first_name, last_name) values ('Marcel', 'Smith');
insert into author (first_name, last_name) values ('Silvio', 'Smith');
insert into author (first_name, last_name) values ('Mitesh', 'Smith');
insert into author (first_name, last_name) values ('Jayesh', 'Smith');
insert into author (first_name, last_name) values ('Pooja', 'Smith');
insert into author (first_name, last_name) values ('Woraphat', 'Smith');
insert into author (first_name, last_name) values ('Yugal', 'Smith');
insert into author (first_name, last_name) values ('Jasonk', 'Smith');
insert into author (first_name, last_name) values ('Serdar', 'Smith');
insert into author (first_name, last_name) values ('Ugur', 'Smith');
insert into author (first_name, last_name) values ('Vera', 'Smith');
insert into author (first_name, last_name) values ('Surendra', 'Smith');
insert into author (first_name, last_name) values ('Serhad', 'Smith');
insert into author (first_name, last_name) values ('Patrick', 'Smith');
insert into author (first_name, last_name) values ('Yeum', 'Smith');
insert into author (first_name, last_name) values ('Benjamin', 'Smith');
insert into author (first_name, last_name) values ('Jasmine', 'Smith');
insert into author (first_name, last_name) values ('Ganesh', 'Smith');
insert into author (first_name, last_name) values ('Manuel', 'Smith');

-- Bücher
INSERT INTO book (isbn, publisher, title, author_id) VALUES
                                                         ('978-1617294945', 'Simon & Schuster', 'Spring in Action, 5th Edition', 1),
                                                         ('978-1617292545', 'Simon & Schuster', 'Spring Boot in Action, 1st Edition', 1),
                                                         ('978-1617297571', 'Simon & Schuster', 'Spring in Action, 6th Edition', 1),
                                                         ('978-0321125217', 'Addison Wesley', 'Domain-Driven Design', 2),
                                                         ('978-0134494166', 'Addison Wesley', 'Clean Code', 3),
                                                         ('978-1234567890', 'Simon & Schuster', 'Spring Security in Action', 1),
                                                         ('978-2345678901', 'Simon & Schuster', 'Spring Cloud in Action', 1),
                                                         ('978-3456789012', 'Addison Wesley', 'Implementing Domain-Driven Design', 2),
                                                         ('978-4567890123', 'Addison Wesley', 'The Clean Coder', 3),
                                                         ('978-5678901234', 'Addison Wesley', 'Clean Architecture', 3),
                                                         ('978-6789012345', 'Addison Wesley', 'Refactoring', 4),
                                                         ('978-7890123456', 'Addison Wesley', 'Patterns of Enterprise Application Architecture', 4),
                                                         ('978-8901234567', 'Addison Wesley', 'UML Distilled', 4),
                                                         ('978-9012345678', 'Addison Wesley', 'Test-Driven Development', 5),
                                                         ('978-0123456789', 'Addison Wesley', 'Extreme Programming Explained', 5),
                                                         ('978-1234567891', 'Addison Wesley', 'Effective Java', 6),
                                                         ('978-2345678902', 'Addison Wesley', 'Java Concurrency in Practice', 6),
                                                         ('978-3456789013', 'Addison Wesley', 'Java Puzzlers', 6),
                                                         ('978-4567890124', 'CareerCup', 'Cracking the Coding Interview', 7),
                                                         ('978-5678901235', 'CareerCup', 'The Google Resume', 7),
                                                         ('978-6789012346', 'Simon & Schuster', 'Spring Data in Action', 1),
                                                         ('978-7890123457', 'Addison Wesley', 'Domain-Driven Design Distilled', 2),
                                                         ('978-8901234568', 'Addison Wesley', 'Clean Agile', 3),
                                                         ('978-9012345679', 'Addison Wesley', 'NoSQL Distilled', 4),
                                                         ('978-0123456780', 'Addison Wesley', 'Implementation Patterns', 5);