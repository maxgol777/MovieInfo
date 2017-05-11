package work.maxgol.movieviewer.jv.data.rest.model;

import java.util.List;

import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;

/**
 * Created by maxgol on 19.04.2017.
 */

public class MovieDetailedRetrofit implements MovieDetailed {

    StringBuilder sb = new StringBuilder();
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

    List<Content> genres;
    List<Content> production_companies;
    List<Content> production_countries;
    List<Content> spoken_languages;

    public class Content {
        int id;
        String name;
    }

    @Override
    public String getBackdrop_path() {
        return backdrop_path;
    }

    @Override
    public int getBudget() {
        return budget;
    }

    @Override
    public String getHomepage() {
        return homepage;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getImdb_id() {
        return imdb_id;
    }

    @Override
    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public String getOriginal_title() {
        return original_title;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public String getPoster_path() {
        return poster_path;
    }

    @Override
    public String getRelease_date() {
        return release_date;
    }

    @Override
    public int getRevenue() {
        return revenue;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getTagline() {
        return tagline;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isVideo() {
        return video;
    }

    @Override
    public int getVote_count() {
        return vote_count;
    }

    @Override
    public double getPopularity() {
        return Math.round(popularity);
    }

    @Override
    public double getVote_average() {
        return vote_average;
    }

    @Override
    public String getGenres() {
        return listToString(genres);
    }

    @Override
    public String getProductionCompanies() {
        return listToString(production_companies);
    }

    @Override
    public String getProductionCountries() {
        return listToString(production_countries);
    }

    @Override
    public String getSpokenLanguages() {
        return listToString(spoken_languages);
    }


    String listToString(List<Content> list) {
        sb.setLength(0);
        for (Content c : list) {
            sb.append(c.name).append(",");
        }
        if (sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
