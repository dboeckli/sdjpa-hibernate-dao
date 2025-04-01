package ch.dboeckli.guru.jpa.hibernate.dao.dao;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static ch.dboeckli.guru.jpa.hibernate.dao.domain.Author.FIND_ALL_QUERY;

@Component
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory emf;

    @Override
    public Author getById(Long id) {
        try (EntityManager em = getEntityManager()) {
            return em.find(Author.class, id);
        }
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Author> query = em.createQuery(
                "SELECT author FROM Author author WHERE author.firstName = :first_name and author.lastName = :last_name", Author.class);
            query.setParameter("first_name", firstName);
            query.setParameter("last_name", lastName);
            return query.getSingleResult();
        }
    }

    @Override
    public List<Author> listAuthorByLastNameLike(String lastName) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Author> query = em.createQuery(
                "SELECT author FROM Author author WHERE author.lastName LIKE :last_name", Author.class);
            query.setParameter("last_name", lastName + "%");
            return query.getResultList();
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Author> typedQuery = em.createNamedQuery(FIND_ALL_QUERY, Author.class);
            return typedQuery.getResultList();
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(author);
            em.flush();
            em.getTransaction().commit();
            return author;
        }
    }

    @Override
    public Author updateAuthor(Author author) {
        try (EntityManager em = getEntityManager()) {
            em.joinTransaction();
            em.merge(author);
            em.flush();
            em.clear();
            em.getTransaction().commit();
            return em.find(Author.class, author.getId());
        }
    }

    @Override
    public void deleteAuthorById(Long id) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            Author author = em.find(Author.class, id);
            em.remove(author);
            em.flush();
            em.getTransaction().commit();
        }
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
