package work.maxgol.movieviewer.jv.data.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;

/**
 * Created by maxgol on 29.03.2017.
 */

public class MovieDetailedRealm extends RealmObject implements Cloneable, MovieDetailed {

    @PrimaryKey
    private int id;

    private String backdrop_path;
    private int budget;
    private String homepage;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private int revenue;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

    private String genres;
    private String productionCompanies;
    private String productionCountries;
    private String spokenLanguages;

    public MovieDetailedRealm() {
    }


    public MovieDetailedRealm(MovieDetailed model) {
        setPopularity(model.getPopularity());
        setRelease_date(model.getRelease_date());
        setRevenue(model.getRevenue());
        setStatus(model.getStatus());
        setTagline(model.getTagline());
        setVideo(model.isVideo());
        setVote_average(model.getVote_average());
        setVote_count(model.getVote_count());
        setId(model.getId());
        setImdb_id(model.getImdb_id());
        setOriginal_language(model.getOriginal_language());
        setOriginal_title(model.getOriginal_title());
        setBudget(model.getBudget());
        setHomepage(model.getHomepage());
        setBackdrop_path(model.getBackdrop_path());
        setPoster_path(model.getPoster_path());
        setOverview(model.getOverview());
        setTitle(model.getTitle());
        setGenres(model.getGenres());
        setProductionCompanies(model.getProductionCompanies());
        setProductionCountries(model.getProductionCountries());
        setSpokenLanguages(model.getSpokenLanguages());
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview == null ? "" : overview.equals("null") ? "" : overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
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

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline == null ? "" : tagline.equals("null") ? "" : tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
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

    public String getGenres() {
        return genres;
    }

    public String getProductionCompanies() {
        return productionCompanies;
    }

    public String getProductionCountries() {
        return productionCountries;
    }

    public String getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setProductionCompanies(String productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public void setProductionCountries(String productionCountries) {
        this.productionCountries = productionCountries;
    }

    public void setSpokenLanguages(String spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    @Override
    public String toString() {

        return
                "id = " + id + "\n" +
                        "backdrop_path  = " + backdrop_path + "\n" +
                        "budget = " + budget + "\n" +
                        "homepage = " + homepage + "\n" +
                        "id = " + id + "\n" +
                        "imdb_id  = " + imdb_id + "\n" +
                        "original_language = " + original_language + "\n" +
                        "original_title = " + original_title + "\n" +
                        "overview = " + overview + "\n" +
                        "popularity = " + popularity + "\n" +
                        "poster_path = " + poster_path + "\n" +
                        "release_date = " + release_date + "\n" +
                        "revenue = " + revenue + "\n" +
                        "status = " + status + "\n" +
                        "tagline = " + tagline + "\n" +
                        "title = " + title + "\n" +
                        "video = " + video + "\n" +
                        "vote_average = " + vote_average + "\n" +
                        "vote_count = " + vote_count + "\n" +
                        "genres = " + genres + "\n" +
                        "productionCompanies = " + productionCompanies + "\n" +
                        "productionCountries = " + productionCountries + "\n" +
                        "spokenLanguages = " + spokenLanguages;
    }

    @Override
    public MovieDetailedRealm clone() {
        return new MovieDetailedRealm(this);
    }

    public static MovieDetailedRealm createFromModel(MovieDetailed model) {
        if (model instanceof MovieDetailedRealm)
            return (MovieDetailedRealm) model;
        return new MovieDetailedRealm(model);
    }
}
