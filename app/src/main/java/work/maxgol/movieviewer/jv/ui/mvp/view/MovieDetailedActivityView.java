package work.maxgol.movieviewer.jv.ui.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import work.maxgol.movieviewer.jv.domain.model.Video;

/**
 * Created by maxgol on 30.03.2017.
 */

public interface MovieDetailedActivityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showTitle(String title);

    @StateStrategyType(SkipStrategy.class)
    void setFloatButtonState(List<Video> list);
}
