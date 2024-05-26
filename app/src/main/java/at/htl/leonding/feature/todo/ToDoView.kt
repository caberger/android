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
import at.htl.leonding.LocalIsPreviewMode
import at.htl.leonding.model.Store
import at.htl.leonding.model.ToDo
import at.htl.leonding.ui.theme.ToDoTheme
import javax.inject.Inject

class ToDoView @Inject constructor() {
    @Inject
    lateinit var toDoViewModel: ToDoViewModel

    @Composable
    fun ToDos() {
        val model = toDoViewModel.subject.subscribeAsState(toDoViewModel.getValue()).value
        val todos = model.toDos
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
        if (!LocalIsPreviewMode.current) {
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

    @Composable
    fun preview() {
        CompositionLocalProvider(LocalIsPreviewMode provides true) {
            fun toDo(id: Long, title: String): ToDo {
                val toDo = ToDo()
                toDo.id = id
                toDo.title = title
                return toDo
            }
            val todos = arrayOf(
                toDo(1, "short title"),
                toDo(2, "title generated by ChatGPT: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Viverra ipsum nunc aliquet bibendum enim facilisis gravida neque convallis. Vulputate enim nulla aliquet porttitor lacus luctus accumsan tortor. Neque egestas congue quisque egestas diam in arcu. Est lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque. Ut venenatis tellus in metus. Consectetur adipiscing elit pellentesque habitant morbi tristique. Ut tellus elementum sagittis vitae et leo duis ut. Vulputate ut pharetra sit amet aliquam id diam. Arcu cursus vitae congue mauris rhoncus aenean vel. Consequat interdum varius sit amet mattis. Faucibus purus in massa tempor nec feugiat nisl pretium fusce. Facilisi nullam vehicula ipsum a arcu cursus. Enim ut tellus elementum sagittis vitae et leo. Neque sodales ut etiam sit amet nisl purus. Vitae tempus quam pellentesque nec. Diam quam nulla porttitor massa id neque aliquam vestibulum morbi. Sed sed risus pretium quam vulputate dignissim suspendisse in est. Nibh mauris cursus mattis molestie a."),
                toDo(3, "another tile that is too long for portrait mode, but ok for landscape")
            )

            val store = Store()
            store.pipe.value!!.toDos = todos
            toDoViewModel = ToDoViewModel(store)
            ToDoTheme {
                ToDos()
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun ToDoViewPreviewPortrait() {
        preview()
    }
    @Preview(showBackground = true, device = "spec:parent=Nexus 5,orientation=landscape")
    @Composable
    fun ToDoViewPreviewLandscape() {
        preview()
    }
}
