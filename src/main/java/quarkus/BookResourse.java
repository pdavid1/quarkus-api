package quarkus;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/books")
@Transactional
public class BookResourse {

    @GET
    public List<Book> index() {
        return Book.listAll();
    } 

    @POST
    public Book insert(Book insertedBook) {
        insertedBook.persist();
        return insertedBook;

    }

}
