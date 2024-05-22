package at.htl.leonding.model;

/** Our read only <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> model */
public class Model {
    public static class UIState {
        public int selectedTab = 0;
    }
    public static class HomeScreenModel {
        public String greetingText = "Hello, world!";
    }
    public ToDo[] toDos = new ToDo[0];
    public UIState uiState = new UIState();
    public HomeScreenModel homeScreenModel = new HomeScreenModel();
}
