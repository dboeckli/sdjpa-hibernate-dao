-- Autoren
INSERT INTO author (id, first_name, last_name) VALUES (1, 'Craig', 'Walls');
INSERT INTO author (id, first_name, last_name) VALUES (2, 'Eric', 'Evans');
INSERT INTO author (id, first_name, last_name) VALUES (3, 'Robert', 'Martin');

-- Bücher
INSERT INTO book (isbn, publisher, title, author_id) VALUES
                                                         ('978-1617294945', 'Simon & Schuster', 'Spring in Action, 5th Edition', 1),
                                                         ('978-1617292545', 'Simon & Schuster', 'Spring Boot in Action, 1st Edition', 1),
                                                         ('978-1617297571', 'Simon & Schuster', 'Spring in Action, 6th Edition', 1),
                                                         ('978-0321125217', 'Addison Wesley', 'Domain-Driven Design', 2),
                                                         ('978-0134494166', 'Addison Wesley', 'Clean Code', 3);