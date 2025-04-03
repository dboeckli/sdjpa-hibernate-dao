package ch.dboeckli.guru.jpa.hibernate.dao.dao;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {

    Book findByIsbn(String isbn);

    List<Book> findAllBooks();

    List<Book> findAllBooks(int pageSize, int offset);

    List<Book> findAllBooks(Pageable pageable);

    List<Book> findAllBooksSortByTitle(Pageable pageable);

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book findBookByTitleWithNamedQuery(String title);

    Book findBookByTitleNative(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
    Book findBookByTitleCriteria(String title);

}
