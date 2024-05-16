package at.htl.leonding.util.immer;

import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.util.mapper.Mapper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/** Immer simplifies handling immutable data structures.
 * @author Christian Aberger (http://www.aberger.at)
 * @see <a>https://immerjs.github.io/immer/</a>
 * @see <a>https://redux.js.org/understanding/thinking-in-redux/motivation</a>
 *
 * @param <T> The type of the baseState
 */
@Singleton
public class Immer<T> {
    final Mapper<T> mapper;

    @Inject
    public Immer(Class<? extends T> type) {
        mapper = new Mapper<T>(type);
    }
    /** Create a deep clone of the existing model.
     * To avoid multithreading issues we change things only running on the one and only Main thread of the app.
     * @param readonlyState the previous readonly single source or truth
     * @param recipe the callback function that modifies the cloned state
     * @param resultConsumer the callback function that uses the cloned & modified model
     */
    public void produce(final T readonlyState, Consumer<T> recipe, Consumer<T> resultConsumer) {
        var nextState = mapper.clone(readonlyState);
        var threadSafe = BehaviorSubject.createDefault(nextState);
        threadSafe.observeOn(AndroidSchedulers.mainThread()).subscribe(model -> {
            recipe.accept(model);
            resultConsumer.accept(model);
        });
    }
}
