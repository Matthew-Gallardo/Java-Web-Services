package org.acumen.training.codes.test;

import org.acumen.training.codes.dao.BookDao;
import org.acumen.training.codes.model.data.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBookDao {

	private BookDao dao;

	@BeforeEach
	public void setup() {
		dao = new BookDao("bookstore_pu");
	}

	@Test
	public void testInsert() {

		Book book = new Book();
		book.setIsbn("999-8-77-666666-5");
		book.setTitle("Ang Panday");
		book.setAuthor("Coco Martin");
		book.setPrice(12.1);
		book.setQuantity(100);

		boolean inserted = dao.insert(book);

		assertTrue(inserted, "The book should be inserted successfully.");
		System.out.println("Inserted 1 record with title: " + book.getTitle());
	}

	@Test
	public void testSelectAll() {
		Book book = new Book();
		book.setIsbn("999-8-77-666666-6");
		book.setTitle("Book for SelectAll Test");
		book.setAuthor("Test Author");
		book.setPrice(15.5);
		book.setQuantity(50);
		dao.insert(book);

		List<Book> books = dao.selectAll();

		assertNotNull(books, "The books list should not be null.");
		assertTrue(books.size() > 0, "The books list should contain at least one book.");
		System.out.println("Total books: " + books.size());
	}

	@Test
	public void testDeleteByTitle() {
		Book book = new Book();
		book.setIsbn("999-8-77-666666-7");
		book.setTitle("Delete Me");
		book.setAuthor("Test Author");
		book.setPrice(11.5);
		book.setQuantity(150);
		dao.insert(book);

		boolean deleted = dao.deleteByTitle("Delete Me");

		assertTrue(deleted, "The book should be deleted successfully.");
		System.out.println("Delete status: " + deleted);
	}

	@Test
	public void testUpdateByIsbn() {

		Book book = new Book();
		book.setIsbn("999-8-77-666666-8");
		book.setTitle("Update Me");
		book.setAuthor("Test Author");
		book.setPrice(20.5);
		book.setQuantity(200);
		dao.insert(book);

		Book updatedBook = new Book();
		updatedBook.setTitle("Updated Title");
		updatedBook.setAuthor("Updated Author");
		updatedBook.setPrice(25.5);
		updatedBook.setQuantity(250);

		boolean updated = dao.updateByIsbn("999-8-77-666666-8", updatedBook);

		assertTrue(updated, "The book should be updated successfully.");
		System.out.println("Book updated with ISBN: 999-8-77-666666-8");
	}

	@Test
	public void testFindByIsbn() {
		Book book = new Book();
		book.setIsbn("999-8-77-666666-9");
		book.setTitle("Find Me By ISBN");
		book.setAuthor("Test Author");
		book.setPrice(22.5);
		book.setQuantity(300);
		dao.insert(book);

		Book foundBook = dao.findByIsbn("999-8-77-666666-9");

		assertNotNull(foundBook, "The book should be found by ISBN.");
		assertEquals("Find Me By ISBN", foundBook.getTitle(), "The book title should match.");
		System.out.println("Found book with ISBN: 999-8-77-666666-9");
	}

	@Test
	public void testFindByTitle() {
		Book book = new Book();
		book.setIsbn("999-8-77-666667-0");
		book.setTitle("Find Me By Title");
		book.setAuthor("Test Author");
		book.setPrice(18.5);
		book.setQuantity(120);
		dao.insert(book);

		List<Book> books = dao.findByTitle("Find Me By Title");

		assertNotNull(books, "The books list should not be null.");
		assertFalse(books.isEmpty(), "The books list should not be empty.");
		assertEquals("Find Me By Title", books.get(0).getTitle(), "The book title should match.");
		System.out.println("Found " + books.size() + " books with title: Find Me By Title");
	}

	@Test
	public void testPartialUpdateByIsbn() {
		Book book = new Book();
		book.setIsbn("999-8-77-666667-1");
		book.setTitle("Partial Update Book");
		book.setAuthor("Test Author");
		book.setPrice(30.5);
		book.setQuantity(400);
		dao.insert(book);

		Book partialBook = new Book();
		partialBook.setPrice(35.5);

		boolean updated = dao.partialUpdateByIsbn("999-8-77-666667-1", partialBook);

		assertTrue(updated, "The book should be partially updated successfully.");
		System.out.println("Book partially updated with ISBN: 999-8-77-666667-1");
	}
}
