package work.maxgol.movieviewer.jv.common.di.components;

import javax.inject.Singleton;

import dagger.Component;
import work.maxgol.movieviewer.jv.common.di.modules.InteractorModule;
import work.maxgol.movieviewer.jv.common.di.modules.MvpModelModule;
import work.maxgol.movieviewer.jv.common.di.modules.RepositoryModule;
import work.maxgol.movieviewer.jv.common.di.modules.SelectedLanguageModule;
import work.maxgol.movieviewer.jv.data.Repository;
import work.maxgol.movieviewer.jv.domain.Interactor;
import work.maxgol.movieviewer.jv.ui.mvp.model.ViewModel;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.MainActivityPresenter;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.MovieDetailedActivityPresenter;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.MovieDetailedFragmentPresenter;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.SettingsActivityPresenter;

/**
 * Created by Maxgol on 19.03.2017.
 */

@Singleton
@Component(modules = {
        InteractorModule.class,
        RepositoryModule.class,

        MvpModelModule.class,
        SelectedLanguageModule.class
})
public interface ApplicationComponent {

    void inject(MainActivityPresenter presenter);

    void inject(MovieDetailedFragmentPresenter presenter);

    void inject(MovieDetailedActivityPresenter presenter);

    void inject(SettingsActivityPresenter presenter);

    void inject(Repository dao);

    void inject(Interactor model);

    void inject(ViewModel model);
}
