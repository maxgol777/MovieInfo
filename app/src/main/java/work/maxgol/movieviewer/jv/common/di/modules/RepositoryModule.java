package work.maxgol.movieviewer.jv.common.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.maxgol.movieviewer.jv.data.Repository;

/**
 * Created by Maxgol on 16.03.2017.
 */

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    Repository provideRepository() {
        return new Repository();
    }
}
