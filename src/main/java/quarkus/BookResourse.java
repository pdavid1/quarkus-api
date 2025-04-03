package quarkus;

import java.util.List;
import java.util.NoSuchElementException;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/books")
@Transactional
public class BookResourse {

    @Inject
    private BookRepository booksRepository;

    @GET
    public List<Book> index() {
        return booksRepository.listAll();
    }

    @POST
    public Book insert(Book insertedBook) {
        booksRepository.persist(insertedBook);
        return insertedBook;

    }

    @GET
    @Path("{id}")
    public Book retrieve(@PathParam("id") Long id) {
        var book = booksRepository.findById(id);
        if (book != null) {
            return book;
        }
        throw new NoSuchElementException("No hay libro con el ID " + id + ".");
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        booksRepository.deleteById(id);
    }

    @PUT
    @Path("{id}")
    public Book update(@PathParam("id") Long id, Book book) {
        var updateBook = booksRepository.findById(id);
        if (updateBook != null) {
            updateBook.setTitle(book.getTitle());
            updateBook.setPubDate(book.getPubDate());
            updateBook.setNumPages(book.getNumPages());
            updateBook.setDescripcion(book.getDescripcion());
            booksRepository.persist(updateBook);
            return updateBook;
        }
        throw new NoSuchElementException("No hay libro con el ID " + id +".");
    }

}
