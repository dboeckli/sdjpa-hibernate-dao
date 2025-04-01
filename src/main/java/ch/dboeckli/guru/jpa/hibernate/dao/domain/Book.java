package ch.dboeckli.guru.jpa.hibernate.dao.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = Book.FIND_ALL_QUERY, query = "FROM Book")
@NamedQuery(name = Book.FIND_BY_TITLE_QUERY, query = "FROM Book book where book.title = :title")
public class Book {

    public static final String FIND_ALL_QUERY = "book_find_all";
    public static final String FIND_BY_TITLE_QUERY = "book_find_by_title";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private String publisher;
    private Long authorId;

    public Book(String title, String isbn, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
