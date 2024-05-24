package at.htl.leonding.feature.tabscreen

import android.util.Log
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
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import at.htl.leonding.model.Store
import at.htl.leonding.ui.theme.ToDoTheme
import javax.inject.Inject
import androidx.compose.ui.tooling.preview.Preview
import at.htl.leonding.feature.home.HomeView
import at.htl.leonding.feature.settings.SettingsScreen
import at.htl.leonding.feature.todo.ToDoView

/** use dummy data for preview mode if set to true */
public val isPreviewMode = compositionLocalOf { false }

class TabScreenView @Inject constructor() {
    private final val TAG = TabScreenView::class.simpleName

    @Inject
    lateinit var tabScreenViewModel: TabScreenViewModel
    @Inject
    lateinit var homeScreenView: HomeView
    @Inject
    lateinit var toDoView: ToDoView

    @Composable
    fun TabScreen() {
        var model = tabScreenViewModel.pipe.subscribeAsState(initial = TabScreenViewModel.TabScreenModel())
        val selectedTab = remember { mutableIntStateOf(model.value.selectedTab) }
        val numberOfTodos = model.value.numberOfToDos
        val tabs = listOf("Home", "ToDos", "Settings")
        Log.i(TAG, "number of totos: ${numberOfTodos}")
        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = selectedTab.value) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = selectedTab.value == index,
                        onClick = {
                            selectedTab.value = index
                            tabScreenViewModel.selectTab(index)
                        },
                        icon = {
                            when (index) {
                                0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                1 -> BadgedBox(badge = { Badge { Text("$numberOfTodos") }}) {
                                    Icon(Icons.Filled.Favorite, contentDescription = "ToDos")
                                }
                                2 -> Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                            }
                        }
                    )
                }
            }
            if (!isPreviewMode.current) {
                when (selectedTab.value) {
                    0 -> homeScreenView?.HomeScreen()
                    1 -> toDoView?.ToDos()
                    2 -> SettingsScreen()
                }
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun TabScreenViewPreview() {
        CompositionLocalProvider(isPreviewMode provides true) {
            tabScreenViewModel = TabScreenViewModel(Store())
            ToDoTheme {
                TabScreen()
            }
        }
    }
}




