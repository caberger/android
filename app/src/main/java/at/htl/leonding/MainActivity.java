package at.htl.leonding;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import androidx.activity.ComponentActivity;
import at.htl.leonding.ui.layout.MainView;
import dagger.hilt.android.AndroidEntryPoint;
import at.htl.leonding.model.Store;

/** Our main activity implemented in java.
 * We separate the application logic from the view. This is our controller.
 * The View is implemented in Jetpack compose in a separate file (separation of concerns).
 * For the views we use Kotlin in order to keep the nice design preview.
 */
@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    public static final String TAG = MainActivity.class.getName();
    @Inject
    MainView mainView;
    @Inject
    Store store;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView.setContentOfActivity(this);
        Log.i(TAG, "subscribe");
        store.pipe.subscribe(model -> Log.i(TAG, String.format("model changed. Number of todos: %d ", model.toDos.length)));
    }
}
