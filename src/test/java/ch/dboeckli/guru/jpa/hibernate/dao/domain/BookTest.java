package ch.dboeckli.guru.jpa.hibernate.dao.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BookTest {

    @Test
    void testBookConstructorAndGetters() {
        String title = "Test Book";
        String isbn = "1234567890";
        String publisher = "Test Publisher";

        Book book = new Book(title, isbn, publisher);

        assertEquals(title, book.getTitle());
        assertEquals(isbn, book.getIsbn());
        assertEquals(publisher, book.getPublisher());
    }

    @Test
    void testBookEquality() {
        Book book1 = new Book("Book 1", "ISBN1", "Publisher 1");
        Book book2 = new Book("Book 1", "ISBN1", "Publisher 1");
        Book book3 = new Book("Book 2", "ISBN2", "Publisher 2");

        book1.setId(1L);
        book2.setId(1L);
        book3.setId(2L);

        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
    }

    @Test
    void testBookHashCode() {
        Book book1 = new Book("Book 1", "ISBN1", "Publisher 1");
        Book book2 = new Book("Book 1", "ISBN1", "Publisher 1");

        book1.setId(1L);
        book2.setId(1L);

        assertEquals(book1.hashCode(), book2.hashCode());
    }

}