package at.htl.leonding;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.activity.ComponentActivity;
import at.htl.leonding.ui.layout.MainViewBuilder;
import dagger.hilt.android.AndroidEntryPoint;

/** Our main activity implemented in java.
 * We separate the application logic from the view. This is our controller.
 * The View is implemented in Jetpack compose in a separate file (separation of concerns).
 * For the views we use Kotlin in order to keep the nice design preview.
 */
@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    public static final String TAG = MainActivity.class.getName();
    @Inject
    MainViewBuilder mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView.setContentOfActivity(this);
    }
}
