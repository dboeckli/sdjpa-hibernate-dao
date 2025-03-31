package ch.dboeckli.guru.jpa.jdbctemplate.repository;

import ch.dboeckli.guru.jpa.jdbctemplate.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
