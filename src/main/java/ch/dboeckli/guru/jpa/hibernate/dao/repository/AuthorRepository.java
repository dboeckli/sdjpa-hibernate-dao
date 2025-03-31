package ch.dboeckli.guru.jpa.hibernate.dao.repository;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
