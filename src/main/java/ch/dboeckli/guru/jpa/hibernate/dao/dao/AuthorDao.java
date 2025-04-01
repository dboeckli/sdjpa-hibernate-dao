package ch.dboeckli.guru.jpa.hibernate.dao.dao;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    List<Author> listAuthorByLastNameLike(String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
