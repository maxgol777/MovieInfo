package work.maxgol.movieviewer.jv.domain.model;

/**
 * Created by maxgol on 18.04.2017.
 */

public interface MovieDetailed {

    String getBackdrop_path();

    int getBudget();

    String getHomepage();

    int getId();

    String getImdb_id();

    String getOriginal_language();

    String getOriginal_title();

    String getOverview();

    double getPopularity();

    String getPoster_path();

    String getRelease_date();

    int getRevenue();

    String getStatus();

    String getTagline();

    String getTitle();

    boolean isVideo();

    double getVote_average();

    int getVote_count();

    String getGenres();

    String getProductionCompanies();

    String getProductionCountries();

    String getSpokenLanguages();
}
