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

@Singleton
public class HomeViewModel extends ViewModelBase<HomeViewModel.HomeModel> {
    public static class HomeModel {
        public List<ToDo> todos = List.of();
        public String greetingText = "";
    }
    final ToDoService toDoService;

    @Inject
    public HomeViewModel(Store store, ToDoService toDoService) {
        super(HomeModel.class, store, new HomeModel());
        this.toDoService = toDoService;
    }
    /** map the "big" model to our HomeModel */
    @Override
    protected HomeModel toSubModel(Model model) {
        var next = new HomeModel();
        next.greetingText = model.greetingModel.greetingText;
        next.todos = List.of(model.toDos);
        return next;
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
