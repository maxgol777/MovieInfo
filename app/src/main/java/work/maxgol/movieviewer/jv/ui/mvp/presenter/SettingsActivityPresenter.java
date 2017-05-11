package work.maxgol.movieviewer.jv.ui.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import work.maxgol.movieviewer.jv.common.app.MyApplication;
import work.maxgol.movieviewer.jv.domain.Interactor;
import work.maxgol.movieviewer.jv.ui.mvp.view.SettingsActivityView;

/**
 * Created by maxgol on 10.04.2017.
 */

@InjectViewState
public class SettingsActivityPresenter extends MvpPresenter<SettingsActivityView> {

    @Inject
    Interactor model;

    public void onCreate() {
        MyApplication.component().inject(this);
    }

    public void clearCache(String mes) {
        model.clearCache();
        getViewState().showMessage(mes);
    }

    public void changeLanguage() {
        getViewState().reloadApp();
    }
}
