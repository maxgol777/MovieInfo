package work.maxgol.movieviewer.jv.common.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.maxgol.movieviewer.jv.domain.Interactor;

/**
 * Created by maxgol on 11.04.2017.
 */

@Module
public class InteractorModule {

    @Provides
    @Singleton
    Interactor model() {
       return  new Interactor();
    }
}
