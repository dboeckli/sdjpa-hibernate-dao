package ch.dboeckli.guru.jpa.hibernate.dao.dao;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Author;

public interface AuthorDao {
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
