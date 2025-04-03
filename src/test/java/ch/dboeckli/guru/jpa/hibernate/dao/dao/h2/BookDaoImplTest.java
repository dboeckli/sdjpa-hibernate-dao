package ch.dboeckli.guru.jpa.hibernate.dao.dao.h2;

import ch.dboeckli.guru.jpa.hibernate.dao.dao.BookDao;
import ch.dboeckli.guru.jpa.hibernate.dao.dao.BookDaoImpl;
import ch.dboeckli.guru.jpa.hibernate.dao.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import({ BookDaoImpl.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Slf4j
class BookDaoImplTest {

    @Autowired
    BookDao bookDao;

    @Test
    void getById() {
        Book book = bookDao.getById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    void findBookByTitle() {
        Book book = bookDao.findBookByTitle("Domain-Driven Design");
        assertThat(book).isNotNull();
    }

    @Test
    void findBookByTitleWithNamedQuery() {
        Book book = bookDao.findBookByTitleWithNamedQuery("Domain-Driven Design");
        assertThat(book).isNotNull();
    }


    @Test
    void testGetBookByTitleCriteria() {
        Book book = bookDao.findBookByTitleCriteria("Domain-Driven Design");
        assertThat(book).isNotNull();
    }

    @Test
    void testGetBookByTitleNative() {
        Book book = bookDao.findBookByTitleNative("Domain-Driven Design");
        assertThat(book).isNotNull();
    }

    @Test
    void saveNewBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setTitle("hallo");
        book.setPublisher("newPublisher");
        book.setAuthorId(2L);
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
        assertNotNull(saved.getId());
    }

    @Test
    void updateBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setTitle("halloToUpdate");
        book.setPublisher("newPublisher");
        book.setAuthorId(2L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("updatedTitle");
        Book updated = bookDao.updateBook(book);

        assertThat(updated.getTitle()).isEqualTo("updatedTitle");
    }

    @Test
    void deleteBookById() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setTitle("halloToUpdate");
        book.setPublisher("newPublisher");
        book.setAuthorId(2L);
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());
        assertThat(deleted).isNull();
        assertNull(bookDao.getById(saved.getId()));
    }

    @Test
    void findByIsbn() {
        Book book = bookDao.findByIsbn("978-1617294945");
        assertThat(book).isNotNull();
    }

    @Test
    void findAllBooks() {
        List<Book> books = bookDao.findAllBooks();

        assertAll("Author List Assertions",
            () -> assertThat(books).isNotNull(),
            () -> assertThat(books).hasSizeGreaterThan(0)
        );
    }

    @Test
    void testFindAllBookPage1() {
        List<Book> books = bookDao.findAllBooks(10, 0);
        AssertionsForInterfaceTypes.assertThat(books).hasSize(10);
    }

    @Test
    void testFindAllBookPage2() {
        List<Book> books = bookDao.findAllBooks(10, 10);
        AssertionsForInterfaceTypes.assertThat(books).hasSize(10);
    }

    @Test
    void testFindAllBookPage10() {
        List<Book> books = bookDao.findAllBooks(10, 100);
        AssertionsForInterfaceTypes.assertThat(books).isEmpty();
    }

    @Test
    void testFindAllBookPage1WithPageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 10));
        AssertionsForInterfaceTypes.assertThat(books).hasSize(10);
    }

    @Test
    void testFindAllBookPage2WithPageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(1, 10));
        AssertionsForInterfaceTypes.assertThat(books).hasSize(10);
    }

    @Test
    void testFindAllBookPage10WithPageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(10, 10));
        AssertionsForInterfaceTypes.assertThat(books).isEmpty();
    }

    @Test
    void testFindAllBookSorted() {
        List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0, 10, Sort.by(Sort.Order.desc("title"))));
        AssertionsForInterfaceTypes.assertThat(books).isNotNull();
        AssertionsForInterfaceTypes.assertThat(books).hasSize(10);
    }
}