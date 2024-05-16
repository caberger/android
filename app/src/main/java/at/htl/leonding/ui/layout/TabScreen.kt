package at.htl.leonding.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.htl.leonding.model.Model
import at.htl.leonding.model.Store
import at.htl.leonding.model.ToDo
import at.htl.leonding.model.ToDoService
import at.htl.leonding.ui.theme.ToDoTheme

@Composable
fun TabScreen(model: Model, store: Store?, toDoService: ToDoService?) {
    var uiState = model.uiState
    val tabIndex = uiState.selectedTab
    val tabs = listOf("Home", "ToDos", "Settings")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = uiState.selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { store?.selectTab(index)},
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> BadgedBox(badge = { Badge { Text("${model.toDos.size}") }}) {
                                Icon(Icons.Filled.Favorite, contentDescription = "ToDos")
                            }
                            2 -> Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> HomeScreen(model, toDoService, store)
            1 -> ToDos(model)
            2 -> SettingsScreen(model)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabScreenPreview() {
    fun todo(id: Long, title: String): ToDo {
        val todo = ToDo()
        todo.id = id
        todo.title = title
        return todo
    }

    val model = Model()
    model.uiState.selectedTab = 0
    model.toDos = arrayOf(todo(1, "homework"), todo(2, "this is a todo with a very long text which should be truncated"))

    ToDoTheme {
        TabScreen(model = model, store = null, toDoService = null)
    }
}

