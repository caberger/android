package at.htl.leonding.feature.tabscreen;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.model.Model;
import at.htl.leonding.model.Store;
import at.htl.leonding.util.store.ViewModelBase;

@Singleton
public class TabScreenViewModel extends ViewModelBase<TabScreenViewModel.TabScreenModel> {
    public static record TabScreenModel(int getNumberOfToDos, int selectedTab) {
        public TabScreenModel() {
            this(0, 0);
        }
    }
    @Inject
    TabScreenViewModel(Store store) {
        super(TabScreenModel.class, store, new TabScreenModel());
    }

    @Override
    protected TabScreenModel toSubModel(Model model) {
        return new TabScreenModel(model.toDos.length, model.uiState.selectedTab);
    }
    public void selectTab(int tab) {
        store.apply(model -> model.uiState.selectedTab = tab);
    }
}
