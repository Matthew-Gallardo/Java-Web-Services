package org.acumen.training.codes.test;

import java.util.List;

import org.acumen.training.codes.BookServiceClient;
import org.acumen.training.codes.model.data.Book;

public class TestBookServiceClient {

	public static void main(String[] args) {
		BookServiceClient bookServiceClient = new BookServiceClient();

		// Test: Get all books
		System.out.println("Testing getAllBooks:");
		List<Book> books = bookServiceClient.getAllBooks();
		if (books != null) {
			books.forEach(System.out::println);
		} else {
			System.out.println("Failed to retrieve books.");
		}

		// Test: Get a book by ISBN
		String testIsbn = "1";
		System.out.println("\nTesting getBookByIsbn with ISBN " + testIsbn + ":");
		Book book = bookServiceClient.getBookByIsbn(testIsbn);
		if (book != null) {
			System.out.println(book);
		} else {
			System.out.println("Book with ISBN " + testIsbn + " not found.");
		}

		// Test: Get a books by Title
		String testTitle = "Clean Code";
		System.out.println("\nTesting getBooksByTitle with Title " + testTitle + ":");
		System.out.println(bookServiceClient.getBookTitleList(testTitle));

		// Test: Update an existing book by isbn
		Book updatedBook = new Book();
		updatedBook.setIsbn("1");
		updatedBook.setTitle("Clean Code");
		updatedBook.setAuthor("Uncle Bob");
		updatedBook.setPrice(1000.0);
		updatedBook.setQuantity(1);
		System.out.println("\nTesting updateBookByIsbn with ISBN " + updatedBook.getIsbn() + ":");
		boolean updateResult = bookServiceClient.updateBookByIsbn(updatedBook.getIsbn(), updatedBook);
		System.out.println("Update book result: " + updateResult);
		// Test: Delete a book by Title
		System.out.println("\nTesting deleteBookByTitle with Title " + updatedBook.getTitle() + ":");
		boolean deleteResult = bookServiceClient.deleteBookByTilte("uPDATSEDASDFFDDDD CLEAN CODE");
		System.out.println("Delete book result: " + deleteResult);
		
		// Test: Add a new book
		Book newBook = new Book();
		newBook.setIsbn("1");
		newBook.setTitle("Clean CodeRS");
		newBook.setAuthor("Uncle Bob");
		newBook.setPrice(1000.0);
		newBook.setQuantity(1);
		System.out.println("\nTesting addBook with new book:");
		boolean addResult = bookServiceClient.addBook(newBook);
		System.out.println("Add book result: " + addResult);
		
		// Test: Partial update of a book by ISBN
		Book partialUpdateBook = new Book();
		Book updateThisBook = new Book();
		updateThisBook.setIsbn("11");
		updateThisBook.setTitle("Clean Code");
		updateThisBook.setAuthor("Uncle Bob");
		updateThisBook.setPrice(1000.0);
		updateThisBook.setQuantity(1);
		bookServiceClient.addBook(updateThisBook);
		partialUpdateBook.setTitle("Partially Updated Clean Code");
		System.out.println("\nTesting partialEditBook with ISBN " + updateThisBook.getIsbn() + ":");
		boolean partialEditResult = bookServiceClient.partialEditBook(partialUpdateBook, updateThisBook.getIsbn());
		System.out.println("Partial edit book result: " + partialEditResult);
	}
}
