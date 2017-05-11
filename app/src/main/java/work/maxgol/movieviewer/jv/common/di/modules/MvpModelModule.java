package work.maxgol.movieviewer.jv.common.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.maxgol.movieviewer.jv.ui.mvp.model.ViewModel;

/**
 * Created by maxgol on 18.04.2017.
 */

@Module
public class MvpModelModule {

    @Provides
    @Singleton
    ViewModel provideMvpModel() {
        return new ViewModel();
    }
}
