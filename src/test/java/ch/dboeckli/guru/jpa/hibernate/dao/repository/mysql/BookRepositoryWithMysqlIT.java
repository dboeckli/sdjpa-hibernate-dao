package ch.dboeckli.guru.jpa.hibernate.dao.repository.mysql;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Book;
import ch.dboeckli.guru.jpa.hibernate.dao.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test_mysql")
@DirtiesContext
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // to assure that it is not replaced with h2
class BookRepositoryWithMysqlIT {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testJpaTestSplice() {
        long countBefore = bookRepository.count();

        bookRepository.save(new Book("My Book", "1235555", "Self"));

        long countAfter = bookRepository.count();

        assertThat(countAfter).isEqualTo(countBefore + 1);
    }

}