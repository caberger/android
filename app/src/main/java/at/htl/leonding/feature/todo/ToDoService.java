package at.htl.leonding.feature.todo;

import android.util.Log;

import org.eclipse.microprofile.config.Config;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.model.Store;
import at.htl.leonding.model.ToDo;
import at.htl.leonding.util.resteasy.RestApiClientBuilder;

@Singleton
public class ToDoService {
    public static final String JSON_PLACEHOLDER_BASE_URL_SETTING = "json.placeholder.baseurl";
    private static final String TAG = ToDoService.class.getSimpleName();

    public final ToDoClient toDoClient;
    public final Store store;

    @Inject
    ToDoService(Config config, RestApiClientBuilder builder, Store store) {
        var baseUrl = config.getValue(JSON_PLACEHOLDER_BASE_URL_SETTING, String.class);
        toDoClient = builder.build(ToDoClient.class, baseUrl);
        this.store = store;
    }

    /** read the first 20 todos from the service.
     * TODO: add currentPage und pageSize to UIState
     */
    public void getAll() {
        Consumer<ToDo[]> setToDos = todos -> {
            store.apply(model -> model.toDos = todos);
        };
        CompletableFuture
                .supplyAsync(() -> toDoClient.all(0, 40))
                .thenAccept(setToDos)
                .exceptionally(e -> {
                    Log.e(TAG, "failed to load todos", e);
                    return null;
                });
    }
}
