package work.maxgol.movieviewer.jv.data.realm.model;

import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import work.maxgol.movieviewer.jv.domain.model.Movie;

/**
 * Created by Maxgol on 19.01.2017.
 */

public class MovieRealm extends RealmObject implements Cloneable, Movie {

    @PrimaryKey
    private int movieId;

    private String release_date;
    private String title;
    private String original_title;
    // --------------------------
    @Nullable
    private String poster_path;
    @Nullable
    private String backdrop_path;
    //---------------------------
    private String overview;
    private double vote_average;
    private int vote_count;
    private double popularity;

    /**
     * values for searching in DB
     * because Realm currently doesn't support case insensitive searches for non-latin characters.
     **/
    private String title_search;
    private String original_title_search;


    public MovieRealm() {
    }

    public MovieRealm(Movie movie) {
        setMovieId(movie.getId());
        setRelease_date(movie.getRelease_date());
        setTitle(movie.getTitle());
        setOriginal_title(movie.getOriginal_title());
        setPoster_path(movie.getPoster_path());
        setBackdrop_path(movie.getBackdrop_path());
        setOverview(movie.getOverview());
        setVote_average(movie.getVote_average());
        setVote_count(movie.getVote_count());
        setPopularity(movie.getPopularity());

        setOriginal_title_search(movie.getOriginal_title_search());
        setTitle_search(movie.getTitle_search());
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_search() {
        return title_search;
    }

    public void setTitle_search(String title_search) {
        this.title_search = title_search;
    }

    public String getOriginal_title_search() {
        return original_title_search;
    }

    public void setOriginal_title_search(String original_title_search) {
        this.original_title_search = original_title_search;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return
                "\nmovie id :  " + movieId + "\n" +
                        "release_date: " + release_date + "\n" +
                        "title: " + title + "\n" +
                        "original_title:  " + original_title + "\n" +
                        "poster_path: " + poster_path + "\n" +
                        "title_search: " + title_search + "\n" +
                        "original_title_search: " + original_title_search + "\n" +
                        "backdrop_path: " + backdrop_path + "\n" +
                        "overview: " + overview + "\n" +
                        "vote_average: " + vote_average + "\n" +
                        "vote_count: " + vote_count + "\n" +
                        "popularity: " + popularity;
    }


    @Override
    public MovieRealm clone() {
        return new MovieRealm(this);
    }

    // Movie and MovieRealm may have different methods
    public static MovieRealm createFromModel(Movie movie) {
        if (movie instanceof MovieRealm)
            return (MovieRealm) movie;

        return new MovieRealm(movie);
    }
}
