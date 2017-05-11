package work.maxgol.movieviewer.jv.ui.mvp.view;

import com.arellomobile.mvp.MvpView;

import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;

/**
 * Created by Maxgol on 13.03.2017.
 */

public interface MovieDetailedFragmentView extends MvpView {

    void setDetailedMovieInfo(MovieDetailed info);

    void setMovieInfo(Movie mi);

    void loadImageWithoutDouble(String path);

}
