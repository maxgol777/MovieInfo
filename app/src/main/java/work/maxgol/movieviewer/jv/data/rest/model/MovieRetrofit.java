package work.maxgol.movieviewer.jv.data.rest.model;

import work.maxgol.movieviewer.jv.domain.model.Movie;

/**
 * Created by maxgol on 19.04.2017.
 */

public class MovieRetrofit implements Movie {

    private int id;
    private String release_date;
    private String title;
    private String original_title;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private double vote_average;
    private int vote_count;
    private double popularity;

    public int getId() {
        return id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle_search() {
        return title.toLowerCase();
    }

    public String getOriginal_title_search() {
        return original_title.toLowerCase();
    }


}
