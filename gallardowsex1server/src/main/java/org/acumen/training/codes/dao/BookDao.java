package org.acumen.training.codes.dao;

import java.util.List;

import org.acumen.training.codes.model.data.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public class BookDao {
	private EntityManager em;
	private static final Logger LOGGER = LogManager.getLogger(BookDao.class);

	public BookDao(String pu) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(pu);
		em = factory.createEntityManager();
		LOGGER.info("BookDao initialized with persistence unit: {}", pu);
	}

	// insert book
	@Transactional
	public boolean insert(Book book) {
		LOGGER.info("Attempting to insert book with title: {}", book.getTitle());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(book);
			tx.commit();
			LOGGER.info("Successfully inserted book with title: {}", book.getTitle());
			return true;
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			LOGGER.error("Error inserting book with title: {}. Transaction rolled back.", book.getTitle(), e);
			return false;
		}
	}

	// Select all books
	@Transactional
	public List<Book> selectAll() {
		LOGGER.info("Fetching all books from the database.");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		query.select(root);
		List<Book> books = em.createQuery(query).getResultList();
		LOGGER.info("Fetched {} books from the database.", books.size());
		return books;
	}

	// Delete book by title
	@Transactional
	public boolean deleteByTitle(String title) {
		LOGGER.info("Attempting to delete book with title: {}", title);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Book> query = cb.createQuery(Book.class);
			Root<Book> root = query.from(Book.class);
			query.select(root).where(cb.equal(root.get("title"), title));

			Book book = em.createQuery(query).getSingleResult();
			if (book != null) {
				em.remove(book);
				LOGGER.info("Successfully deleted book with title: {}", title);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			LOGGER.error("Error deleting book with title: {}. Transaction rolled back.", title, e);
			return false;
		}
	}

	// update transaction by isbn
	@Transactional
	public boolean updateByIsbn(String isbn, Book updatedBook) {
		LOGGER.info("Attempting to update book with ISBN: {}", isbn);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Book> query = cb.createQuery(Book.class);
			Root<Book> root = query.from(Book.class);
			query.select(root).where(cb.equal(root.get("isbn"), isbn));

			Book book = em.createQuery(query).getSingleResult();
			if (book != null) {
				book.setTitle(updatedBook.getTitle());
				book.setAuthor(updatedBook.getAuthor());
				book.setPrice(updatedBook.getPrice());
				book.setQuantity(updatedBook.getQuantity());

				em.merge(book);
				tx.commit();
				LOGGER.info("Successfully updated book with ISBN: {}", isbn);
				return true;
			}
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			LOGGER.error("Error updating book with ISBN: {}. Transaction rolled back.", isbn, e);
		}
		return false;
	}

	// Find book by ISBN
	public Book findByIsbn(String isbn) {
		LOGGER.info("Searching for book with ISBN: {}", isbn);
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Book> query = cb.createQuery(Book.class);
			Root<Book> root = query.from(Book.class);
			query.select(root).where(cb.equal(root.get("isbn"), isbn));
			Book book = em.createQuery(query).getSingleResult();
			LOGGER.info("Found book with ISBN: {}", isbn);
			return book;
		} catch (Exception e) {
			LOGGER.error("Error finding book with ISBN: {}", isbn, e);
			return null;
		}
	}

	// Find book list by title
	public List<Book> findByTitle(String title) {
		LOGGER.info("Searching for books with title: {}", title);
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Book> query = cb.createQuery(Book.class);
			Root<Book> root = query.from(Book.class);
			query.select(root).where(cb.equal(root.get("title"), title));
			List<Book> books = em.createQuery(query).getResultList();
			LOGGER.info("Found {} books with title: {}", books.size(), title);
			return books;
		} catch (Exception e) {
			LOGGER.error("Error finding books with title: {}", title, e);
			return null;
		}
	}

	// Partial Update
	@Transactional
	public boolean partialUpdateByIsbn(String isbn, Book partialBook) {
		LOGGER.info("Attempting partial update for book with ISBN: {}", isbn);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Book> query = cb.createQuery(Book.class);
			Root<Book> root = query.from(Book.class);
			query.select(root).where(cb.equal(root.get("isbn"), isbn));

			Book book = em.createQuery(query).getSingleResult();

			if (book != null) {
				if (partialBook.getTitle() != null) {
					book.setTitle(partialBook.getTitle());
				}
				if (partialBook.getAuthor() != null) {
					book.setAuthor(partialBook.getAuthor());
				}
				if (partialBook.getPrice() != null) {
					book.setPrice(partialBook.getPrice());
				}
				if (partialBook.getQuantity() != null) {
					book.setQuantity(partialBook.getQuantity());
				}

				em.merge(book);
				tx.commit();
				LOGGER.info("Successfully partially updated book with ISBN: {}", isbn);
				return true;
			}
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			LOGGER.error("Error partially updating book with ISBN: {}. Transaction rolled back.", isbn, e);
		}
		return false;
	}
}
