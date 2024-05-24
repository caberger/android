package at.htl.leonding.feature.todo;

import at.htl.leonding.model.ToDo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/todos")
public interface ToDoClient {
    @GET
    ToDo[] all();
}
