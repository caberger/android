package at.htl.leonding.model;

import org.eclipse.microprofile.config.Config;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.util.resteasy.RestApiClientBuilder;

@Singleton
public class ToDoService {
    public static final String JSON_PLACEHOLDER_BASE_URL_SETTING = "json.placeholder.baseurl";
    public final ToDoClient toDoClient;
    public final Store store;

    @Inject
    ToDoService(Config config, RestApiClientBuilder builder, Store store) {
        var baseUrl = config.getValue(JSON_PLACEHOLDER_BASE_URL_SETTING, String.class);
        toDoClient = builder.build(ToDoClient.class, baseUrl);
        this.store = store;
    }
    public void getAll() {
        CompletableFuture
                .supplyAsync(() -> toDoClient.all())
                .thenAccept(store::setTodos);
    }
}
