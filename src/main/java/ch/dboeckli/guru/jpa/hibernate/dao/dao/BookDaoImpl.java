package ch.dboeckli.guru.jpa.hibernate.dao.dao;

import ch.dboeckli.guru.jpa.hibernate.dao.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory emf;

    @Override
    public Book findByIsbn(String isbn) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> query = em.createQuery("SELECT book FROM Book book WHERE book.isbn = :isbn", Book.class);
            query.setParameter("isbn", isbn);

            return query.getSingleResult();
        }
    }

    @Override
    public List<Book> findAllBooks() {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> typedQuery = em.createNamedQuery(Book.FIND_ALL_QUERY, Book.class);
            return typedQuery.getResultList();
        }
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
            query.setFirstResult(offset);
            query.setMaxResults(pageSize);
            return query.getResultList();
        }
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        try (EntityManager em = getEntityManager()) {
            String sql = "SELECT b FROM Book b ORDER BY b.title " + Objects.requireNonNull(pageable.getSort()
                .getOrderFor("title")).getDirection().name();

            TypedQuery<Book> query = em.createQuery(sql, Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }
    }

    @Override
    public Book getById(Long id) {
        try (EntityManager em = getEntityManager()) {
            return em.find(Book.class, id);
        }
    }

    @Override
    public Book findBookByTitle(String title) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> query = em.createQuery(
                "SELECT book FROM Book book WHERE book.title = :title", Book.class);
            query.setParameter("title", title);

            return query.getSingleResult();
        }
    }

    @Override
    public Book findBookByTitleWithNamedQuery(String title) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> typedQuery = em.createNamedQuery(Book.FIND_BY_TITLE_QUERY, Book.class);
            typedQuery.setParameter("title", title);
            return typedQuery.getSingleResult();
        }
    }

    @Override
    public Book findBookByTitleNative(String title) {
        try (EntityManager em = getEntityManager()) {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM book WHERE title = :title", Book.class);
            nativeQuery.setParameter("title", title);
            return (Book) nativeQuery.getSingleResult();
        }
    }

    @Override
    public Book saveNewBook(Book book) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(book);
            em.flush();
            em.getTransaction().commit();
            return book;
        }
    }

    @Override
    public Book updateBook(Book book) {
        try (EntityManager em = getEntityManager()) {
            em.joinTransaction();
            em.merge(book);
            em.flush();
            em.clear();
            em.getTransaction().commit();
            return em.find(Book.class, book.getId());
        }
    }

    @Override
    public void deleteBookById(Long id) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            Book book = em.find(Book.class, id);
            em.remove(book);
            em.flush();
            em.getTransaction().commit();
        }
    }

    @Override
    public Book findBookByTitleCriteria(String title) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

            Root<Book> fromBook = criteriaQuery.from(Book.class);

            ParameterExpression<String> titleParam = criteriaBuilder.parameter(String.class);

            Predicate titlePredicate = criteriaBuilder.equal(fromBook.get("title"), titleParam);

            criteriaQuery.select(fromBook).where(titlePredicate);

            TypedQuery<Book> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setParameter(titleParam, title);

            return typedQuery.getSingleResult();
        }
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
