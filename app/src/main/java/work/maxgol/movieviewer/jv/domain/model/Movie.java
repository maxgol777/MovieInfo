package work.maxgol.movieviewer.jv.domain.model;

/**
 * Created by maxgol on 18.04.2017.
 */

public interface Movie {

    String getPoster_path();

    String getRelease_date();

    int getId();

    String getOriginal_title();

    String getTitle();

    String getTitle_search();

    String getOriginal_title_search();

    String getOverview();

    String getBackdrop_path();

    double getVote_average();

    int getVote_count();

    double getPopularity();
}
