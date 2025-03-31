package ch.dboeckli.guru.jpa.hibernate.dao.repository;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
