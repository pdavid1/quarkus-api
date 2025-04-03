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

    @Inject
    private BookRepository repo;

    @GET
    public List<Book> index() {
        return repo.listAll();
    } 

    @POST
    public Book insert(Book insertedBook) {
        
        assert insertedBook.getId() == null;
        repo.persist(insertedBook);
        return insertedBook;

    }

}
