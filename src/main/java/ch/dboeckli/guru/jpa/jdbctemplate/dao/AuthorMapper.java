package ch.dboeckli.guru.jpa.jdbctemplate.dao;

import ch.dboeckli.guru.jpa.jdbctemplate.domain.Author;
import ch.dboeckli.guru.jpa.jdbctemplate.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));

        try {
            if (resultSet.getString("isbn") != null){
                author.setBooks(new ArrayList<>());
                author.getBooks().add(mapBook(resultSet));

                while (resultSet.next()){
                    author.getBooks().add(mapBook(resultSet));
                }
            }
        } catch (SQLException e) {
            //do nothing
        }
        return author;
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong(4));
        book.setIsbn(rs.getString(5));
        book.setPublisher(rs.getString(6));
        book.setTitle(rs.getString(7));
        book.setAuthorId(rs.getLong(1));
        return book;
    }
}

