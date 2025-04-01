package ch.dboeckli.guru.jpa.hibernate.dao.dao;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Author findAuthorByNameWithNamedQuery(String firstName, String lastName) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Author> typedQuery = em.createNamedQuery(Author.FIND_BY_NAME_QUERY, Author.class);
            typedQuery.setParameter("first_name", firstName);
            typedQuery.setParameter("last_name", lastName);
            return typedQuery.getSingleResult();
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
            TypedQuery<Author> typedQuery = em.createNamedQuery(Author.FIND_ALL_QUERY, Author.class);
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

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

            Root<Author> fromAuthor = criteriaQuery.from(Author.class);

            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class);
            ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class);

            Predicate firstNamePred = criteriaBuilder.equal(fromAuthor.get("firstName"), firstNameParam);
            Predicate lastNamePred = criteriaBuilder.equal(fromAuthor.get("lastName"), lastNameParam);

            criteriaQuery.select(fromAuthor).where(criteriaBuilder.and(firstNamePred, lastNamePred));

            TypedQuery<Author> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setParameter(firstNameParam, firstName);
            typedQuery.setParameter(lastNameParam, lastName);

            return typedQuery.getSingleResult();
        }
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        try (EntityManager em = getEntityManager()) {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM author a WHERE a.first_name = ? and a.last_name = ?", Author.class);

            nativeQuery.setParameter(1, firstName);
            nativeQuery.setParameter(2, lastName);

            return (Author) nativeQuery.getSingleResult();
        }
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
