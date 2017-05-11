package work.maxgol.movieviewer.jv.ui.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import work.maxgol.movieviewer.jv.common.app.MyApplication;
import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.Video;
import work.maxgol.movieviewer.jv.ui.mvp.model.ViewModel;
import work.maxgol.movieviewer.jv.ui.mvp.view.MainActivityView;

/**
 * Created by Maxgol on 10.03.2017.
 */

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    @Inject
    ViewModel model;

    private CompositeSubscription cs;

    private boolean mTwoPane;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        // --- set adapter with list of movies ---
        getViewState().setRecyclerViewAdapter(model.movies);
        getViewState().updateView();

    }

    public void onCreate() {
        MyApplication.component().inject(this);

        getViewState().restoreViewsState(model.getSearchStr(), model.getRestorePosition());

        if (mTwoPane) {
            getViewState().showDetailsInFragment();
        }
    }

    public void saveState(GridLayoutManager manager) {
        int cvp = manager.findFirstCompletelyVisibleItemPosition();
        int restPos = cvp == -1 ? manager.findFirstVisibleItemPosition() : cvp;
        model.setRestorePosition(restPos);
    }

    public void onPause() {
        cs.unsubscribe();
    }

    public void setPaneState(boolean dualPane) {
        this.mTwoPane = dualPane;
    }

    public void onResume() {
        if (model.clear())
            getViewState().updateView();
    }

    public void setObservables(Observable<String> search, Observable<Integer> scroll) {
        cs = new CompositeSubscription();
        search(search);
        scroll(scroll);
    }

    private void search(Observable<String> search) {
        cs.add(search
                .debounce(200, TimeUnit.MILLISECONDS)// limits frequency
                .filter(e -> e != null)
                .doOnNext(s -> model.setSearchStr(s))
                .switchMap(s -> getListOfMovies(s, 1))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(list -> {
                    model.movies.clear();
                    model.movies.addAll(list);
                })
                .doOnNext(items -> getViewState().updateView())
                .subscribe());
    }

    private void scroll(Observable<Integer> observable) {
        cs.add(
                observable
                        .map(offset -> offset / 20 + 1)
                        .distinctUntilChanged()
                        .filter(page -> page != 1)
                        .switchMap(page -> getListOfMovies(model.getSearchStr(), page))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Movie>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.v("TAG", "ERROR : " + e.getMessage());
                            }

                            @Override
                            public void onNext(List<Movie> items) {
                                model.movies.addAll(items);
                                getViewState().updateView();
                            }
                        })
        );
    }

    public void recyclerClickPerform(int selPos) {

        Movie mi = model.movies.get(selPos);

        // --- set MovieRealm for sharing between activities ---
        model.setSelectedMovie(mi);


        if (mTwoPane) {
            getViewState().showDetailsInFragment();

            // --- load videos ---
            model.getVideos(mi.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Video>>() {
                        @Override
                        public void onCompleted() {
                            //Log.v("TAG", "COmplete");
                            //getViewState().setFloatButtonState(new ArrayList<>());
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(List<Video> videos) {
                            getViewState().setFloatButtonState(videos);
                        }
                    });


        } else {
            getViewState().startMovieDetailedActivity();
        }
    }

    public void startSettingsActivity() {
        getViewState().startSettingsActivity();
    }

    @NonNull
    private Observable<List<Movie>> getListOfMovies(String request, int page) {
        return model.getListOfMovies(request, page);
    }
}
