package work.maxgol.movieviewer.jv.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import work.maxgol.movieviewer.jv.common.app.MyApplication;
import work.maxgol.movieviewer.jv.data.realm.model.MovieDetailedRealm;
import work.maxgol.movieviewer.jv.data.realm.model.MovieRealm;
import work.maxgol.movieviewer.jv.data.rest.RestManager;
import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;
import work.maxgol.movieviewer.jv.domain.model.Video;

/**
 * Created by Maxgol on 10.03.2017.
 */

public class Repository {

    public Repository() {
        MyApplication.component().inject(this);
    }

    // =================== MovieRealm requests ===================================

    public Observable<List<Movie>> moviesFromBD(String query, int page) {

        Observable<List<Movie>> movies = Observable.create(subscriber -> {

            List<Movie> list = new ArrayList<>();

            Realm realm = Realm.getDefaultInstance();
            try {
                // --- if it's scrolling or empty ---
                if (page != 1 || query.length() == 0) {
                    subscriber.onNext(list);
                    return;
                }

                // ---- search by title and original_title in lower case ----
                RealmResults<MovieRealm> results = realm.where(MovieRealm.class)
                        .beginGroup()
                        .contains("title_search", query.toLowerCase(), Case.INSENSITIVE)
                        .or()
                        .contains("original_title_search", query.toLowerCase(), Case.INSENSITIVE)
                        .endGroup()
                        .findAll();

                for (MovieRealm mi : results)
                    list.add(mi.clone());

                subscriber.onNext(list);

                Log.v("TAG", "list  = " + list);
            } finally {
                realm.close();
            }

        });

        return movies;
    }

    public Observable<List<Movie>> moviesFromNetwork(String query, int page, String lang) {
        return RestManager.getInstance()
                .getMovies(query, page, lang)
                .onErrorResumeNext(Observable.empty())
                .map(resp -> new ArrayList<Movie>(resp.results))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(list -> list)
                .toList()
                .filter(list -> list.size() != 0);
    }

    // ==================== MovieDetailedRealm requests ============================

    public Observable<List<Video>> video(int movieId, String lang) {

        return RestManager.getInstance().getVideos(String.valueOf(movieId), lang)
                .map((resp) -> new ArrayList<Video>(resp.results));
    }

    public Observable<MovieDetailed> detailedMovieInfoRequest(int movieId, String lang) {

        Observable<MovieDetailed> db = detailedMovieInfoRequestDB(movieId).doOnError(er -> er.printStackTrace());

        Observable<MovieDetailed> network = detailedMovieInfoRequestServer(String.valueOf(movieId), lang).doOnError(er -> er.printStackTrace());

        return Observable.concat(db, network).first();

    }

    private Observable<MovieDetailed> detailedMovieInfoRequestServer(String movieId, String lang) {

        return RestManager.getInstance().getDetailedInfo(movieId, lang)
                .map(m -> ((MovieDetailed) m))
                .doOnError(err -> Log.v("TAG", "ERROR: " + err.getMessage().toString() + " : " + err.getStackTrace().toString() + " : " + err.toString()))
                .doOnNext(info -> cacheMovieInfoDetailed(info));

    }

    private Observable<MovieDetailed> detailedMovieInfoRequestDB(int movieId) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<MovieDetailedRealm> movies = realm.where(MovieDetailedRealm.class)
                    .equalTo("id", movieId)
                    .findAll();

            if (movies.size() > 0) {
                // --- clone needs for Realm Db ---
                subscriber.onNext(movies.get(0).clone());
            }

            subscriber.onCompleted();
            realm.close();
        });
    }

    private boolean findMovieDetailedById(int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<MovieDetailedRealm> movies =
                realm.where(MovieDetailedRealm.class)
                        .equalTo("id", id)
                        .findAll();

        boolean v = movies.size() > 0;
        realm.close();
        return v;
    }

    private boolean findMovieById(int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<MovieRealm> movies =
                realm.where(MovieRealm.class)
                        .equalTo("movieId", id)
                        .findAll();

        boolean v = movies.size() > 0;
        realm.close();
        return v;
    }

    // ============ caching data ============================================

    public void clearCache() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm1.delete(MovieRealm.class);
            realm1.delete(MovieDetailedRealm.class);
        });
        realm.close();
    }

    private void cacheMovieInfoDetailed(MovieDetailed info) {
        Realm realm = Realm.getDefaultInstance();

        // if movie is out in DataBase - add it
        if (!findMovieDetailedById(info.getId()))
            realm.executeTransaction(r -> r.insert(MovieDetailedRealm.createFromModel(info)));
        realm.close();
    }

    public void cacheMovieInfo(Movie movieInfo) {
        Realm realm = Realm.getDefaultInstance();

        // --- if id isn't exists ---
        if (!findMovieById(movieInfo.getId())) {
            realm.executeTransaction(r -> r.insert(MovieRealm.createFromModel(movieInfo)));
        }

        realm.close();
    }
}
