package work.maxgol.movieviewer.jv.ui.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.*;


import java.util.List;

import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.Video;

/**
 * Created by Maxgol on 10.03.2017.
 */

public interface MainActivityView extends MvpView {

    void setRecyclerViewAdapter(List<Movie> movies);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateView();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void restoreViewsState(String query, int pos);

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String mes);

    @StateStrategyType(SkipStrategy.class)
    void showDetailsInFragment();

    @StateStrategyType(SkipStrategy.class)
    void setFloatButtonState(List<Video> list);

    @StateStrategyType(SkipStrategy.class)
    void startMovieDetailedActivity();

    @StateStrategyType(SkipStrategy.class)
    void startSettingsActivity();

}
