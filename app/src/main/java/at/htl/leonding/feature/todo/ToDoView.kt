package at.htl.leonding.feature.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.leonding.isPreviewMode
import at.htl.leonding.model.Store
import at.htl.leonding.model.ToDo
import at.htl.leonding.ui.theme.ToDoTheme
import javax.inject.Inject

class ToDoView @Inject constructor() {
    @Inject
    lateinit var toDoViewModel: ToDoViewModel

    @Composable
    fun ToDos() {
        val model = toDoViewModel.pipe.subscribeAsState(ToDoViewModel.ToDoModel()).value
        val todos = todos(model)
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            items(todos.size) { ToDoRow(todos[it]) }
        }
    }
    @Composable
    fun ToDoRow(toDo: ToDo) {
        Row(modifier = Modifier.padding(4.dp)) {
            Text(toDo.id.toString(), modifier = Modifier.padding(horizontal = 6.dp))
            Text(text = toDo.title, overflow = TextOverflow.Ellipsis, maxLines = 1)
        }
    }
    @Composable
    fun todos(model: ToDoViewModel.ToDoModel): List<ToDo> {
        val todos: List<ToDo>
        if (!isPreviewMode.current) {
            todos = model.toDos
        } else {
            fun createToDo(id: Long, title: String): ToDo {
                val toDo = ToDo()
                toDo.id = id
                toDo.title = title
                return toDo
            }
            todos = listOf(createToDo(1, "test"), createToDo(2, "commit"))
        }
        return todos
    }
    @Preview(showBackground = true)
    @Composable
    fun TodoViewPreview() {
        CompositionLocalProvider(isPreviewMode provides true) {
            toDoViewModel = ToDoViewModel(Store())
            ToDoTheme {
                ToDos()
            }
        }
    }
}
