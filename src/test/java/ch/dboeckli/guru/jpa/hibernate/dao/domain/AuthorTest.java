package ch.dboeckli.guru.jpa.hibernate.dao.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void testAuthorEquality() {
        Author author1 = new Author();
        Author author2 = new Author();
        Author author3 = new Author();

        author1.setId(1L);
        author2.setId(1L);
        author3.setId(2L);

        assertEquals(author1, author2);
        assertNotEquals(author1, author3);
    }

    @Test
    void testAuthorHashCode() {
        Author author1 = new Author();
        Author author2 = new Author();

        author1.setId(1L);
        author2.setId(1L);

        assertEquals(author1.hashCode(), author2.hashCode());
    }

    @Test
    void testAuthorEqualityWithNullId() {
        Author author1 = new Author();
        Author author2 = new Author();

        assertEquals(author1, author2);

        author1.setId(1L);
        assertNotEquals(author1, author2);
    }

}