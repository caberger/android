package at.htl.leonding.ui.layout

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import at.htl.leonding.feature.home.HomeViewModel
import at.htl.leonding.feature.tabscreen.TabScreenView
import at.htl.leonding.model.Store
import at.htl.leonding.model.Model
import at.htl.leonding.feature.todo.ToDoService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/** Our View implemented in kotlin.
 * Application Logic is separated from this view.
 * Our State is delivered exclusively from our {@link Store}, which we subscribe here.
 */
@Singleton
class MainView {
    @Inject
    lateinit var tabScreenView: TabScreenView

    @Inject
    constructor() {
    }
    fun setContentOfActivity(activity: ComponentActivity) {
        val view = ComposeView(activity)
        view.setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                tabScreenView.TabScreen()
            }   
        }
        activity.setContentView(view)
    }
}


