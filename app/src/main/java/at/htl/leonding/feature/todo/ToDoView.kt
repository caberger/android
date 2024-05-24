package at.htl.leonding.feature.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import at.htl.leonding.model.Store
import at.htl.leonding.model.ToDo
import javax.inject.Inject


class ToDoView @Inject constructor() {
    @Inject
    lateinit var toDoViewModel: ToDoViewModel

    @Composable
    fun ToDos() {
        val model = toDoViewModel.pipe.subscribeAsState(ToDoViewModel.ToDoModel()).value
        val todos = model.todos
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
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
}
