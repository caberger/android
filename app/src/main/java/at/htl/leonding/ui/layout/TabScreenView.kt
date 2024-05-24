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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import at.htl.leonding.feature.home.HomeViewModel
import at.htl.leonding.model.Store
import at.htl.leonding.ui.theme.ToDoTheme
import javax.inject.Inject
import androidx.compose.ui.tooling.preview.Preview
import at.htl.leonding.feature.home.HomeView
import at.htl.leonding.feature.settings.SettingsScreen
import at.htl.leonding.feature.todo.ToDoView


private val localPreviewMode: ProvidableCompositionLocal<Boolean> = compositionLocalOf { false }

class TabScreenView {
    @Inject
    lateinit var store: Store
    @Inject
    lateinit var homeScreenView: HomeView
    @Inject
    lateinit var toDoView: ToDoView

    @Inject constructor() {}

    @Composable
    fun TabScreenLayout() {
        val initialState = store.get()
        var model = store.pipe.subscribeAsState(initialState).value
        val selectedTab = remember { mutableIntStateOf(initialState.uiState.selectedTab) }
        val tabs = listOf("Home", "ToDos", "Settings")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = selectedTab.value) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = selectedTab.value == index,
                        onClick = {
                            selectedTab.value = index
                            store.selectTab(index)
                        },
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
            if (!localPreviewMode.current) {
                when (selectedTab.value) {
                    0 -> homeScreenView.HomeScreen()
                    1 -> toDoView.ToDos()
                    2 -> SettingsScreen()
                }
            }
        }
    }
    @Composable
    fun TabScreen() {
        if (localPreviewMode.current) {
            TabScreenLayout()
        } else {
            TabScreenLayout()
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun TabScreenPreview() {
        store = Store()
        CompositionLocalProvider(localPreviewMode provides true) {
            ToDoTheme {
                TabScreen()
            }
        }
    }
}




