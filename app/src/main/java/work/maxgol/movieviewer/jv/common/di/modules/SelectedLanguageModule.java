package work.maxgol.movieviewer.jv.common.di.modules;

import dagger.Module;
import dagger.Provides;
import work.maxgol.movieviewer.jv.common.app.LangI;

/**
 * Created by maxgol on 10.04.2017.
 */
@Module
public class SelectedLanguageModule {

    private final LangI lang;

    public SelectedLanguageModule(LangI lang) {
        this.lang = lang;
    }

    @Provides
    public LangI language() {
        return lang;
    }
}
