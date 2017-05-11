package work.maxgol.movieviewer.jv.ui.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import work.maxgol.movieviewer.jv.common.app.MyApplication;
import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;
import work.maxgol.movieviewer.jv.domain.model.Video;
import work.maxgol.movieviewer.jv.ui.mvp.model.ViewModel;
import work.maxgol.movieviewer.jv.ui.mvp.view.MovieDetailedFragmentView;

/**
 * Created by Maxgol on 13.03.2017.
 */

@InjectViewState
public class MovieDetailedFragmentPresenter extends MvpPresenter<MovieDetailedFragmentView> {

    @Inject
    ViewModel model;

    public void onCreate() {

        MyApplication.component().inject(this);

        loadData(model.getSelectedMovie().getId());
    }

    public void onViewCreated() {
        getViewState().setMovieInfo(model.getSelectedMovie());
    }

    public void loadData(int id) {

        // ---- load detailed info -----
        Observable.just(id)
                .switchMap(s -> getDetailedMovieInfo(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetailed>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.v("TAG", "ERROR :   message " + error.getMessage() + "err: " + error.toString());
                        error.printStackTrace();
                    }

                    @Override
                    public void onNext(MovieDetailed info) {
                        getViewState().setDetailedMovieInfo(info);

                        String p = checkDoubling(info.getPoster_path());
                        getViewState().loadImageWithoutDouble(p);
                    }
                });


        // ---- load video -----
        Observable.just(id)
                .switchMap(s -> getVideos(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Video>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Video> video) {
                        //Log.v("TAG", "video  : " + video);
                    }
                });
    }

    String checkDoubling(String path) {
        String p = model.getSelectedMovie().getPoster_path();
        return p == null ? null : p.equals(path) ? null : path;
    }

    private Observable<MovieDetailed> getDetailedMovieInfo(int id) {
        return model.getDetailedMovieInfo(id)
                .subscribeOn(Schedulers.io());
    }

    private Observable<List<Video>> getVideos(int id) {
        return model.getVideos(id).subscribeOn(Schedulers.io());
    }

}
