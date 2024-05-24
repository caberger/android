package at.htl.leonding.util.store;

import at.htl.leonding.model.Model;
import at.htl.leonding.model.Store;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * A base class for our ViewModels in the sense of the MVVM Pattern.
 * In a lot of texts the term "Model-View-ViewModel" is often explained incorrectly.
 * For a detailed explanation of MVVM
 * watch the first 17 minutes of <a href="https://www.youtube.com/watch?v=W1ymVx6dmvc">Lecture 3 | Stanford CS193p 2023</a>.
 * In that text replace "SwiftUI" -> Jetpack; Compose "Swift" -> Java; "struct" -> record
 * @param <M> the type of the submodel
 */
public abstract class ViewModelBase<M> extends StoreBase<M> {
    protected final Store store;
    Disposable subscription;

    protected ViewModelBase(Class<? extends M> type, Store store, M initialState) {
        super(type, initialState);
        this.store = store;
        subscription = store
                .pipe
                .map(model -> toSubModel(model))
                .distinctUntilChanged()
                .subscribe(model -> pipe.onNext(model));
    }
    protected abstract M toSubModel(Model model);
}
