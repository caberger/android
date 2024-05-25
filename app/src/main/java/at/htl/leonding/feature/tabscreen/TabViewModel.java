package at.htl.leonding.feature.tabscreen;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.model.Model;
import at.htl.leonding.model.Store;
import at.htl.leonding.util.store.ViewModelBase;

@Singleton
public class TabViewModel extends ViewModelBase<TabViewModel.TabScreenModel> {
    public static record TabScreenModel(int getNumberOfToDos, Model.UIState.Tab selectedTab) {
        public TabScreenModel() {
            this(0, Model.UIState.Tab.home);
        }
        public final static Model.UIState.Tab DEFAULT_TAB = Model.UIState.Tab.home;
        public final static Model.UIState.Tab PREVIEW_TAB = Model.UIState.Tab.settings;
    }
    @Inject
    TabViewModel(Store store) {
        super(TabScreenModel.class, store, new TabScreenModel());
    }

    @Override
    protected TabScreenModel toSubModel(Model model) {
        return new TabScreenModel(model.toDos.length, model.uiState.selectedTab);
    }
    public void selectTabByIndex(int index) {
        var tabs = Arrays.stream(Model.UIState.Tab.values());
        var tab = tabs.filter(t -> t.index() == index).findFirst().orElse(Model.UIState.Tab.home);
        store.apply(model -> model.uiState.selectedTab = tab);
    }
}
