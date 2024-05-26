package at.htl.leonding.feature.tabscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import at.htl.leonding.feature.home.HomeView
import at.htl.leonding.feature.settings.SettingsScreen
import at.htl.leonding.feature.todo.ToDoView
import at.htl.leonding.LocalIsPreviewMode
import at.htl.leonding.model.Store
import at.htl.leonding.ui.theme.ToDoTheme
import javax.inject.Inject

class TabView @Inject constructor() {
    @Inject
    lateinit var tabScreenViewModel: TabViewModel
    @Inject
    lateinit var homeScreenView: HomeView
    @Inject
    lateinit var toDoView: ToDoView

    @Composable
    fun TabViewLayout() {
        val model = tabScreenViewModel.subject.subscribeAsState(tabScreenViewModel.getValue())
        val tab = model.value.selectedTab
        val tabIndex = tab.index()
        val selectedTab = remember { mutableIntStateOf(tabIndex) }
        val numberOfTodos = model.value.numberOfToDos
        val tabs = listOf("Home", "ToDos", "Settings")
        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = selectedTab.value) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = selectedTab.value == index,
                        onClick = {
                            selectedTab.value = index
                            tabScreenViewModel.selectTabByIndex(index)
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
            ContentArea(selectedTab.value)
        }
    }
    @Composable
    fun ContentArea(selectedTab: Int) {
        if (!LocalIsPreviewMode.current) {
            when (selectedTab) {
                0 -> homeScreenView.HomeScreen()
                1 -> toDoView.ToDos()
                2 -> SettingsScreen()
            }
        } else {
            PreviewContentArea()
        }
    }
    @Composable
    fun PreviewContentArea() {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "This is the content of the selected tab",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun TabViewPreview() {
        CompositionLocalProvider(LocalIsPreviewMode provides true) {
            tabScreenViewModel = TabViewModel(Store())
            
            ToDoTheme {
                TabViewLayout()
            }
        }
    }
}




