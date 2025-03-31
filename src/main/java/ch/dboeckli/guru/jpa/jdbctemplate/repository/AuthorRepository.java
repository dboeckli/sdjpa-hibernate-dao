package ch.dboeckli.guru.jpa.jdbctemplate.repository;

import ch.dboeckli.guru.jpa.jdbctemplate.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
