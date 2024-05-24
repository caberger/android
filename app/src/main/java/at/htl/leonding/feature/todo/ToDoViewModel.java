package at.htl.leonding.feature.todo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.model.Model;
import at.htl.leonding.model.Store;
import at.htl.leonding.model.ToDo;
import at.htl.leonding.util.store.ViewModelBase;

@Singleton
public class ToDoViewModel extends ViewModelBase<ToDoViewModel.ToDoModel> {
    public static record ToDoModel(List<ToDo> toDos) {
        public ToDoModel() { this(List.of()); }
    }
    @Inject
    public ToDoViewModel(Store store) {
        super(ToDoModel.class, store, new ToDoModel());
    }
    @Override
    protected ToDoModel toSubModel(Model model) {
        return new ToDoModel(List.of(model.toDos));
    }
}
