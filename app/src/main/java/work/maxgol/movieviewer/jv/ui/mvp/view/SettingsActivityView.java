package work.maxgol.movieviewer.jv.ui.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by maxgol on 10.04.2017.
 */

public interface SettingsActivityView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String mes);

    @StateStrategyType(SkipStrategy.class)
    void reloadApp();
}
