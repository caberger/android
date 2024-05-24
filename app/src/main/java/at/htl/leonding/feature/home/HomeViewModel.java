package at.htl.leonding.feature.home;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.model.Model;
import at.htl.leonding.model.Store;
import at.htl.leonding.model.ToDo;
import at.htl.leonding.feature.todo.ToDoService;
import at.htl.leonding.util.store.StoreBase;
import at.htl.leonding.util.store.ViewModelBase;
import io.reactivex.rxjava3.disposables.Disposable;

/** The HomeViewModel translates between our global application state and
 * the model how our small HomeView sees the world.
 */
@Singleton
public class HomeViewModel extends ViewModelBase<HomeViewModel.HomeModel> {

    /** the model for our HomeView, which only knows about a list of todos and a greeting text */
    public static record HomeModel(int numberOfToDos, String greetingext) {
        HomeModel() { this(0, "Hello, world!"); }
    }
    final ToDoService toDoService;

    @Inject
    HomeViewModel(Store store, ToDoService toDoService) {
        super(HomeModel.class, store, new HomeModel());
        this.toDoService = toDoService;
    }
    @Override
    protected HomeModel toSubModel(Model model) {
        return new HomeModel(model.toDos.length, model.greetingModel.greetingText);
    }
    public void setGreetingText(String text) {
        store.apply(model -> model.greetingModel.greetingText = text);
    }
    public void cleanToDos() {
        store.apply(model -> model.toDos = new ToDo[0]);
    }
    public void loadAllTodos() {
        toDoService.getAll();
    }
}
