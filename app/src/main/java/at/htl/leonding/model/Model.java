package at.htl.leonding.model;

/** Our read only <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> model */
public class Model {
    public static class UIState {
        /** the type of Tab - Bars in our main view */
        public enum Tab {
            home(0),
            todo(1),
            settings(2);

            public int index() {
                return index;
            }
            private int index;
            Tab(int index) {
                this.index = index;
            }
        }
        public Tab selectedTab = Tab.home;
    }
    public static class GreetingModel {
        public String greetingText = "Hello, world!";
    }
    public ToDo[] toDos = new ToDo[0];
    public UIState uiState = new UIState();
    public GreetingModel greetingModel = new GreetingModel();
}
