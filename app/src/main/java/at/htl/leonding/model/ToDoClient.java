package at.htl.leonding.model;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/todos")
public interface ToDoClient {
    @GET
    ToDo[] all();
}
