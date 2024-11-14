package org.acumen.training.codes.services;

import java.util.List;

import org.acumen.training.codes.dao.BookDao;
import org.acumen.training.codes.model.data.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/book")
public class BookService {

	private final BookDao bookDao = new BookDao("bookstore_pu");
	private static final Logger LOGGER = LogManager.getLogger(BookService.class);

	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertBook(Book book) {
		LOGGER.info("Attempting to insert book: {}", book);
		boolean isInserted = bookDao.insert(book);
		if (isInserted) {
			LOGGER.info("Successfully inserted book with title: {}", book.getTitle());
			return Response.status(Response.Status.CREATED).entity(book).build();
		} else {
			LOGGER.error("Failed to insert book with title: {}", book.getTitle());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllBooks() {
		LOGGER.info("Fetching all books.");
		List<Book> books = bookDao.selectAll();
		LOGGER.info("Fetched {} books.", books.size());
		return Response.ok(books).build();
	}

	@DELETE
	@Path("/delete/title/{title}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteBookByTitle(@PathParam("title") String title) {
		LOGGER.info("Attempting to delete book with title: {}", title);
		boolean isDeleted = bookDao.deleteByTitle(title);
		if (isDeleted) {
			LOGGER.info("Successfully deleted book with title: {}", title);
			return Response.noContent().build();
		} else {
			LOGGER.warn("Book with title: {} not found for deletion.", title);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/isbn/{isbn}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getBookByIsbn(@PathParam("isbn") String isbn) {
		LOGGER.info("Searching for book with ISBN: {}", isbn);
		Book book = bookDao.findByIsbn(isbn);
		if (book != null) {
			LOGGER.info("Found book with ISBN: {}", isbn);
			return Response.ok(book).build();
		} else {
			LOGGER.warn("Book with ISBN: {} not found.", isbn);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/title/list/{title}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getBooksByTitle(@PathParam("title") String title) {
		LOGGER.info("Searching for books with title: {}", title);
		List<Book> books = bookDao.findByTitle(title);
		GenericEntity<List<Book>> data = new GenericEntity<List<Book>>(books) {
		};
		LOGGER.info("Found {} books with title: {}", books.size(), title);
		return Response.ok(data).build();
	}

	@PUT
	@Path("/update/isbn/{isbn}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateBookByIsbn(@PathParam("isbn") String isbn, Book updatedBook) {
		LOGGER.info("Attempting to update book with ISBN: {}", isbn);
		boolean isUpdated = bookDao.updateByIsbn(isbn, updatedBook);
		if (isUpdated) {
			LOGGER.info("Successfully updated book with ISBN: {}", isbn);
			return Response.ok(updatedBook).build();
		} else {
			LOGGER.warn("Book with ISBN: {} not found for update.", isbn);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PATCH
	@Path("/update/partial/isbn/{isbn}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response partialUpdateBookByIsbn(@PathParam("isbn") String isbn, Book partialBook) {
		LOGGER.info("Attempting partial update for book with ISBN: {}", isbn);
		boolean isUpdated = bookDao.partialUpdateByIsbn(isbn, partialBook);
		if (isUpdated) {
			LOGGER.info("Successfully partially updated book with ISBN: {}", isbn);
			return Response.ok(partialBook).build();
		} else {
			LOGGER.warn("Book with ISBN: {} not found for partial update.", isbn);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
