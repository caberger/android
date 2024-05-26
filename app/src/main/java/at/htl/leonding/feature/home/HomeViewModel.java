package at.htl.leonding.feature.home;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.model.Model;
import at.htl.leonding.model.Store;
import at.htl.leonding.model.ToDo;
import at.htl.leonding.feature.todo.ToDoService;
import at.htl.leonding.model.UIState;
import at.htl.leonding.util.store.ViewModelBase;

/** The HomeViewModel translates between our global application state and
 * the model how our small HomeView sees the world.
 */
@Singleton
public class HomeViewModel extends ViewModelBase<HomeViewModel.HomeModel> {

    /** the model for our HomeView, which only knows about a list of todos and a greeting text */
    public record HomeModel(int numberOfToDos, String greetingText, UIState.Orientation orientation) {}

    final ToDoService toDoService;

    @Inject
    HomeViewModel(Store store, ToDoService toDoService) {
        super(HomeModel.class, store);
        this.toDoService = toDoService;
    }
    @Override
    protected HomeModel toViewModel(Model model) {
        return new HomeModel(model.toDos.length, model.greetingModel.greetingText, model.uiState.orientation);
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
