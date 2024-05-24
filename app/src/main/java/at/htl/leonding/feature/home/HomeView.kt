package at.htl.leonding.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import javax.inject.Inject

/**
 * Example of an editing composable using <a href="https://medium.com/androiddevelopers/under-the-hood-of-jetpack-compose-part-2-of-2-37b2c20c6cdd">remember</a>.
 */
class HomeView {
    @Inject
    lateinit var homeScreenViewModel: HomeViewModel

    @Inject
    constructor() {
    }
    @Composable
    fun HomeScreen() {
        val initialState = homeScreenViewModel.get()
        val size = homeScreenViewModel.pipe.map { model -> model.todos.size }.subscribeAsState(0)
        val text = remember { mutableStateOf(initialState.greetingText) }

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
                TextField(text.value, { text.value = it })
            }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Text("${size.value} Todos have been loaded")
            }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Button(modifier = Modifier.padding(16.dp),
                    onClick = { homeScreenViewModel.loadAllTodos() }) {
                    Text("load Todos now")
                }
            }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = { homeScreenViewModel.cleanToDos()}) {
                    Text("clean Todos")
                }
            }
        }
    }
}