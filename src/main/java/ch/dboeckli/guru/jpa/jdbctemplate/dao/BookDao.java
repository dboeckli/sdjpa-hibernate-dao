package ch.dboeckli.guru.jpa.jdbctemplate.dao;

import ch.dboeckli.guru.jpa.jdbctemplate.domain.Book;

public interface BookDao {
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}
