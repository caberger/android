package at.htl.leonding.feature.tabscreen;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.model.Model;
import at.htl.leonding.model.Store;
import at.htl.leonding.util.store.ViewModelBase;

@Singleton
public class TabScreenViewModel extends ViewModelBase<TabScreenViewModel.TabScreenModel> {
    public static class TabScreenModel {
        public int numberOfTodos = 0;
        public int selectedTab = 0;
    }
    @Inject
    TabScreenViewModel(Store store) {
        super(TabScreenModel.class, store, new TabScreenModel());
    }

    @Override
    protected TabScreenModel toSubModel(Model model) {
        var tsModel = new TabScreenModel();
        tsModel.numberOfTodos = model.toDos.length;
        tsModel.selectedTab = model.uiState.selectedTab;
        return tsModel;
    }
    public void selectTab(int tab) {
        store.apply(model -> model.uiState.selectedTab = tab);
    }
}
