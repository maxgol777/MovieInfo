package work.maxgol.movieviewer.jv.ui.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import work.maxgol.movieviewer.jv.common.app.MyApplication;
import work.maxgol.movieviewer.jv.domain.model.Video;
import work.maxgol.movieviewer.jv.ui.mvp.model.ViewModel;
import work.maxgol.movieviewer.jv.ui.mvp.view.MovieDetailedActivityView;

/**
 * Created by maxgol on 30.03.2017.
 */

@InjectViewState
public class MovieDetailedActivityPresenter extends MvpPresenter<MovieDetailedActivityView> {

    @Inject
    ViewModel model;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model.setMovieDetailedActivityPresenter(this);
    }

    public void onCreate() {
        MyApplication.component().inject(this);
        String title = model.getSelectedMovie().getTitle();
        getViewState().showTitle(title);
    }

    public void loadVideo(List<Video> videos) {
        getViewState().setFloatButtonState(videos);
    }
}
