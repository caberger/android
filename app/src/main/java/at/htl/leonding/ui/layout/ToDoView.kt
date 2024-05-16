package at.htl.leonding.ui.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import at.htl.leonding.model.Model
import at.htl.leonding.model.ToDo

@Composable
fun ToDos(model: Model) {
    val todos = model.toDos

    LazyColumn (modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(todos.size) { index ->
            ToDoRow(toDo = todos[index])
        }
    }
}
@Composable
fun ToDoRow(toDo: ToDo) {
    Row(modifier = Modifier.padding(4.dp)) {
        Text(toDo.id.toString(), modifier = Modifier.padding(horizontal = 6.dp))
        Text(text = toDo.title, overflow = TextOverflow.Ellipsis, maxLines = 1)
    }
}
