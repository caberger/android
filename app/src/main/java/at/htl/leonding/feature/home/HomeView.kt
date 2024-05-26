package at.htl.leonding.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.leonding.LocalIsPreviewMode
import at.htl.leonding.model.Store
import at.htl.leonding.model.UIState
import at.htl.leonding.ui.theme.ToDoTheme
import javax.inject.Inject

/**
 * Example of an editing composable using <a href="https://medium.com/androiddevelopers/under-the-hood-of-jetpack-compose-part-2-of-2-37b2c20c6cdd">remember</a>.
 */
class HomeView @Inject constructor() {
    @Inject
    lateinit var homeScreenViewModel: HomeViewModel

    @Composable
    fun HomeScreen() {
        val model = homeScreenViewModel.subject.subscribeAsState(homeScreenViewModel.current());
        val text = remember { mutableStateOf(model.value.greetingText) }
        //val orientation = remember { mutableStateOf(model.value.orientation) }
        val orientation = model.value.orientation

        /** we update the model whenever the text is changed by the user */
        SideEffect {
            homeScreenViewModel.setGreetingText(text.value);
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(
                    text = text.value,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                TextField(model.value.greetingText, { text.value = it })
            }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Text("${model.value.numberOfToDos} Todos have been loaded")
            }
            if (orientation == UIState.Orientation.landscape) {
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Buttons(Modifier.align(Alignment.CenterVertically))
                }
            } else {
                Buttons(Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
    @Composable
    fun LoadAllToDosButton(modifier: Modifier) {
        Row(modifier) {
            Button(modifier = Modifier.padding(16.dp),
                onClick = { homeScreenViewModel.loadAllTodos() }) {
                Text("load Todos now")
            }
        }
    }
    @Composable
    fun ClearButton(modifier: Modifier) {
        Row(modifier) {
            Button(
                onClick = { homeScreenViewModel.cleanToDos()}) {
                Text("clean Todos")
            }
        }
    }
    @Composable
    fun Buttons(modifier: Modifier) {
        LoadAllToDosButton(modifier)
        ClearButton(modifier)
    }
    @Preview(showBackground = true)
    @Composable
    fun HomeViewPreview() {
        CompositionLocalProvider(LocalIsPreviewMode provides true) {
            homeScreenViewModel = HomeViewModel(Store(), null)
            ToDoTheme {
                HomeScreen()
            }
        }
    }
}