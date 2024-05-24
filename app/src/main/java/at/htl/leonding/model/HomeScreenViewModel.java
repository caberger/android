package at.htl.leonding.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.util.store.StoreBase;
import io.reactivex.rxjava3.disposables.Disposable;

@Singleton
public class HomeScreenViewModel extends StoreBase<HomeScreenViewModel.Model> {
    public static class Model {
        public List<ToDo> todos = List.of();
        public String greetingText = "";
    }
    final ToDoService toDoService;
    final Store store;
    Disposable subscription;

    @Inject
    public HomeScreenViewModel(Store store, ToDoService toDoService) {
        super(Model.class, new Model());
        this.store = store;
        this.toDoService = toDoService;
        subscription = store.pipe.map(model -> {
            var next = new Model();
            next.greetingText = model.greetingModel.greetingText;
            next.todos = List.of(model.toDos);
            return next;
        })
        .distinctUntilChanged()
        .subscribe(model -> pipe.onNext(model));
    }
    public void setGreetingText(String text) {
        store.apply(model -> model.greetingModel.greetingText = text);
    }
    public void loadAllTodos() {
        toDoService.getAll();
    }
}
