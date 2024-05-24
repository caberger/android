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
    public static class ToDoModel {
        public List<ToDo> todos = List.of();
    }
    @Inject
    public ToDoViewModel(Store store) {
        super(ToDoModel.class, store, new ToDoModel());
    }
    @Override
    protected ToDoModel toSubModel(Model model) {
        var tdModel = new ToDoModel();
        tdModel.todos = List.of(model.toDos);
        return tdModel;
    }
}
