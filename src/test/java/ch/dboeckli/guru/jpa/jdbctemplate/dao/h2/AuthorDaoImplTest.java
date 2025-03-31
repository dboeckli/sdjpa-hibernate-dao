package ch.dboeckli.guru.jpa.jdbctemplate.dao.h2;

import ch.dboeckli.guru.jpa.jdbctemplate.dao.AuthorDao;
import ch.dboeckli.guru.jpa.jdbctemplate.dao.AuthorDaoImpl;
import ch.dboeckli.guru.jpa.jdbctemplate.domain.Author;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import({ AuthorDaoImpl.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Slf4j
class AuthorDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        DataIntegrityViolationException ex = assertThrows(
            DataIntegrityViolationException.class, () -> authorDao.getById(saved.getId())
        );
        log.info("### Exception message: " + ex.getMessage());
        assertThat(ex.getMessage()).contains("No data is available [2000-232]");
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Thompson");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Thompson");
    }

    @Test
    void testInsertAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorWithThreeBooks() {
        Author author = authorDao.getById(1L);
        assertThat(author.getId()).isNotNull();
        assertEquals(3, author.getBooks().size());
    }

    @Test
    void testGetAuthorWithOneBooks() {
        Author author = authorDao.getById(2L);
        assertThat(author.getId()).isNotNull();
        assertEquals(1, author.getBooks().size());
    }

    @Test
    void testGetAuthorWithoutBooks() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("belushi");

        Author saved = authorDao.saveNewAuthor(author);

        Author authorWithoutBooks = authorDao.getById(saved.getId());
        assertThat(authorWithoutBooks.getId()).isNotNull();
        assertNull(authorWithoutBooks.getBooks());
    }
}