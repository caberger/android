package at.htl.leonding.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.leonding.model.Model
import at.htl.leonding.model.Store
import at.htl.leonding.model.ToDoService

@Composable
fun HomeScreen(model: Model, toDoService: ToDoService?, store: Store?) {
    val todos = model.toDos;
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)) {
            Text(
                text = "Welcome Home!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text("${todos.size} Todos have been loaded")
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Button(modifier = Modifier.padding(16.dp),
                onClick = { toDoService?.getAll() }) {
                Text("load Todos now")
            }
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = { store?.setTodos(arrayOf()) }) {
                Text("clean Todos")
            }
        }
    }
}