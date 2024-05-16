package at.htl.leonding.ui.layout

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import at.htl.leonding.model.Store
import at.htl.leonding.model.Model
import at.htl.leonding.model.ToDoService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/** Our View implemented in kotlin.
 * Application Logic is separated from this view.
 * Our State is delivered exclusively from our {@link Store}, which we subscribe here.
 */
@Singleton
class MainViewBuilder {
    @Inject
    lateinit var store: Store

    @Inject
    lateinit var toDoService: ToDoService

    @Inject
    constructor() {
    }
    fun setContentOfActivity(activity: ComponentActivity) {
        val view = ComposeView(activity)
        view.setContent {
            val viewModel = store.pipe.observeOn(AndroidSchedulers.mainThread()).subscribeAsState(initial = Model()).value
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                TabScreen(viewModel, store, toDoService)
            }   
        }
        activity.setContentView(view)
    }
}


