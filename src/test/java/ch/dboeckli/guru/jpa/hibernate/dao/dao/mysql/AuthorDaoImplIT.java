package ch.dboeckli.guru.jpa.hibernate.dao.dao.mysql;

import ch.dboeckli.guru.jpa.hibernate.dao.dao.AuthorDao;
import ch.dboeckli.guru.jpa.hibernate.dao.dao.AuthorDaoImpl;
import ch.dboeckli.guru.jpa.hibernate.dao.domain.Author;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test_mysql")
@Import(AuthorDaoImpl.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class AuthorDaoImplIT {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());


        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();
        assertNull(authorDao.getById(saved.getId()));
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
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
        assertNotNull(saved.getId());
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorByNameWithNamedQuery() {
        Author author = authorDao.findAuthorByNameWithNamedQuery("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorByNameCriteria() {
        Author author = authorDao.findAuthorByNameCriteria("Craig", "Walls");
        assertThat(author).isNotNull();
    }


    @Test
    void testGetAuthorByNameNative() {
        Author author = authorDao.findAuthorByNameNative("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthor() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    void testListAuthorByLastNameLike() {
        List<Author> authors = authorDao.listAuthorByLastNameLike("Wall");

        assertAll("Author List Assertions",
            () -> assertThat(authors).isNotNull(),
            () -> assertThat(authors).hasSizeGreaterThan(0)
        );
    }

    @Test
    void findAllAuthors() {
        List<Author> authors = authorDao.findAllAuthors();

        assertAll("Author List Assertions",
            () -> assertThat(authors).isNotNull(),
            () -> assertThat(authors).hasSizeGreaterThan(0)
        );
    }

    @Test
    void findAllAuthorsByLastName() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 10));

        assertAll("Author List Assertions",
            () -> assertThat(authors).isNotNull(),
            () -> assertThat(authors).hasSize(10)
        );
    }

    @Test
    void findAllAuthorsByLastNameSortLastNameDesc() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith",
            PageRequest.of(0, 10, Sort.by(Sort.Order.desc("firstname"))));

        assertAll("Author List Assertions",
            () -> assertThat(authors).isNotNull(),
            () -> assertThat(authors).hasSize(10),
            () -> assertThat(authors.getFirst().getFirstName()).isEqualTo("Yugal")
        );
    }

    @Test
    void findAllAuthorsByLastNameSortLastNameAsc() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith",
            PageRequest.of(0, 10, Sort.by(Sort.Order.asc("firstname"))));

        assertAll("Author List Assertions",
            () -> assertThat(authors).isNotNull(),
            () -> assertThat(authors).hasSize(10),
            () -> assertThat(authors.getFirst().getFirstName()).isEqualTo("Ahmed")
        );
    }

    @Test
    void findAllAuthorsByLastNameAllRecs() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 100));

        assertAll("Author List Assertions",
            () -> assertThat(authors).isNotNull(),
            () -> assertThat(authors).hasSize(40)
        );
    }
}
