package work.maxgol.movieviewer.jv.ui.mvp.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action0;
import work.maxgol.movieviewer.jv.common.app.MyApplication;
import work.maxgol.movieviewer.jv.domain.Interactor;
import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;
import work.maxgol.movieviewer.jv.domain.model.Video;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.MovieDetailedActivityPresenter;

/**
 * Created by maxgol on 18.04.2017.
 */

public class ViewModel {

    @Inject
    Interactor interactor;

    private MovieDetailedActivityPresenter movieDetailedActivityPresenter;
    public List<Movie> movies = new ArrayList<>();
    private Movie selectedMovie;
    private int restorePosition;
    private String searchStr = "";

    public ViewModel() {
        MyApplication.component().inject(this);
    }

    public synchronized Movie getSelectedMovie() {
        return selectedMovie;
    }

    public synchronized void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;

        // --- cache MovieRealm after click ---
        interactor.cacheMovieInfo(selectedMovie);
    }

    public int getRestorePosition() {
        return restorePosition;
    }

    public void setRestorePosition(int restorePosition) {
        this.restorePosition = restorePosition;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public void setMovieDetailedActivityPresenter(MovieDetailedActivityPresenter movieDetailedActivityPresenter) {
        this.movieDetailedActivityPresenter = movieDetailedActivityPresenter;
    }

    public boolean clear() {
        if (interactor.needToClearList()) {
            movies.clear();
            return true;
        }

        return false;
    }

    public Observable<List<Movie>> getListOfMovies(String query, int page) {
        return interactor.getListOfMovies(query, page);
    }

    public Observable<MovieDetailed> getDetailedMovieInfo(int id) {
        return interactor.getDetailedMovieInfo(id);
    }

    public Observable<List<Video>> getVideos(int id) {

        if (movieDetailedActivityPresenter != null)
            movieDetailedActivityPresenter.loadVideo(new ArrayList<>());

        return interactor.getVideos(id)
                // --- notify presenter that video has loaded ---
                .doOnNext(lst -> {
                    if (movieDetailedActivityPresenter != null)
                        movieDetailedActivityPresenter.loadVideo(lst);
                });
    }
}
