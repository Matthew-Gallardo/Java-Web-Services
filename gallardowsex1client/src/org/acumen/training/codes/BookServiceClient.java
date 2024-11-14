package org.acumen.training.codes;

import java.util.List;

import org.acumen.training.codes.model.data.Book;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class BookServiceClient {
	
    // POST: add a new book
    public boolean addBook(Book book) {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target("http://localhost:8080/gallardoweb/rs/book/add")
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.json(book));
            System.out.println(response.readEntity(Book.class));
            return response.getStatus() == 201;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // GET: select all books
    public List<Book> getAllBooks() {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/gallardoweb/rs/book/all")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        int sc = response.getStatus();
        System.out.println("Status Code: " + sc);
        List<Book> result = response.readEntity(new GenericType<List<Book>>() {});
        return result;
    }
    
    // PUT: update book by ISBN
    public boolean updateBookByIsbn(String isbn, Book book) {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target("http://localhost:8080/gallardoweb/rs/book/update/isbn/" + isbn)
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .put(Entity.json(book));
            System.out.println(response.readEntity(Book.class));
            return response.getStatus() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // DELETE: delete book by title
    public boolean deleteBookByTilte(String title) {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target("http://localhost:8080/gallardoweb/rs/book/delete/title/" + title)
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .delete();
            System.out.println("Status Code: " + response.getStatus());
            return response.getStatus() == 204;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // GET: select a book by ISBN
    public Book getBookByIsbn(String isbn) {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/gallardoweb/rs/book/isbn/" + isbn)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        int sc = response.getStatus();
        System.out.println("Status Code: " + sc);
        return response.readEntity(Book.class);
    }
    // GET: select a book by Title
    public List<Book> getBookTitleList(String title) {
    	Client client = ClientBuilder.newClient();
    	Response response = client.target("http://localhost:8080/gallardoweb/rs/book/title/list/" + title)
    			.request()
    			.accept(MediaType.APPLICATION_JSON)
    			.get();
    	int sc = response.getStatus();
		System.out.println("SC: %d".formatted(sc));
		List<Book> res = response.readEntity(new GenericType<List<Book>>() {});
		return res;
    }


    // Partial update (PATCH) of book details by ISBN
    public boolean partialEditBook(Book book, String isbn) {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target("http://localhost:8080/gallardoweb/rs/book/update/partial/isbn/" + isbn)
                    .request(MediaType.APPLICATION_JSON)
                    .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
                    .accept(MediaType.APPLICATION_JSON)
                    .method("PATCH", Entity.json(book));
            System.out.println(response.readEntity(Book.class));
            return response.getStatus() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
