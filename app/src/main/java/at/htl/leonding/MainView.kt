package at.htl.leonding

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import at.htl.leonding.feature.tabscreen.TabView
import javax.inject.Inject
import javax.inject.Singleton

/** Allow previews of our views using dummy data */
val isPreviewMode = staticCompositionLocalOf { false }

/** Our View implemented in kotlin.
 * Application Logic is separated from this view.
 * Our State is delivered exclusively from our {@link Store}, which we subscribe here.
 */
@Singleton
class MainView @Inject constructor() {
    @Inject
    lateinit var tabScreenView: TabView

    fun setContentOfActivity(activity: ComponentActivity) {
        val view = ComposeView(activity)
        view.setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                tabScreenView.TabViewLayout()
            }   
        }
        activity.setContentView(view)
    }
}


