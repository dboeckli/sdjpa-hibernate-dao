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
@NamedQuery(name = Author.FIND_ALL_QUERY, query = "FROM Author")
@NamedQuery(name = Author.FIND_BY_NAME_QUERY, query = "FROM Author a WHERE a.firstName = :first_name and a.lastName = :last_name")
public class Author {

    public static final String FIND_ALL_QUERY = "author_find_all";
    public static final String FIND_BY_NAME_QUERY = "author_find_by_name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
