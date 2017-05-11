package work.maxgol.movieviewer.jv.domain;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import work.maxgol.movieviewer.jv.common.app.LangI;
import work.maxgol.movieviewer.jv.common.app.MyApplication;
import work.maxgol.movieviewer.jv.data.Repository;
import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;
import work.maxgol.movieviewer.jv.domain.model.Video;


/**
 * Created by maxgol on 11.04.2017.
 */

public class Interactor {

    @Inject
    LangI lang;

    @Inject
    Repository repository;

    private boolean loadFromDb;
    private boolean cacheCleared;

    public Interactor() {
        MyApplication.component().inject(this);
    }

    public boolean needToClearList() {
        return loadFromDb && cacheCleared;
    }

    public void cacheMovieInfo(Movie mi) {
        repository.cacheMovieInfo(mi);
        cacheCleared = false;
    }

    public Observable<List<Movie>> getListOfMovies(String query, int page) {

        Observable<List<Movie>> network = repository.moviesFromNetwork(query, page, lang.getSelectedLanguage())

                // --- last request was from server ---
                .doOnNext(l -> loadFromDb = false);

        Observable<List<Movie>> bd = repository.moviesFromBD(query, page)

                // --- last request was from DataBase ---
                .doOnNext(l -> loadFromDb = true);

        return Observable.merge(network, bd);
    }

    public Observable<List<Video>> getVideos(int id) {
        return repository.video(id, lang.getSelectedLanguage());
    }

    public Observable<MovieDetailed> getDetailedMovieInfo(int id) {
        return repository.detailedMovieInfoRequest(id, lang.getSelectedLanguage());
    }

    public void clearCache() {
        repository.clearCache();
        cacheCleared = true;
    }

}
