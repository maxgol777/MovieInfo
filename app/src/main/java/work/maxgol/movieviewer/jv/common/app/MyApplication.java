package work.maxgol.movieviewer.jv.common.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.common.di.components.ApplicationComponent;

import work.maxgol.movieviewer.jv.common.di.components.DaggerApplicationComponent;
import work.maxgol.movieviewer.jv.common.di.modules.InteractorModule;
import work.maxgol.movieviewer.jv.common.di.modules.MvpModelModule;
import work.maxgol.movieviewer.jv.common.di.modules.RepositoryModule;
import work.maxgol.movieviewer.jv.common.di.modules.SelectedLanguageModule;


/**
 * Created by Maxgol on 10.03.2017.
 */

public class MyApplication extends Application implements LangI {

    /**
     * в Android Studio,
     * как и в IDEA есть bookmarks - закладки.
     * Вы можете ставить их находясь на строчке клавишей F11
     * и открывать окно bookmark сочетанием shift+F11.
     * //========== технологии ===========================================
     * Clean Architecture
     * Mvp(Moxy)
     * JavaRx
     * Retrofit2
     * Dagger2
     * Realm orm
     * // -------
     * Kotlin
     * Anko
     * //============= Функционал ========================================
     * 0  Создать приложение для просмотра списка фильмов используя следующее API https://developers.themoviedb.org/3/
     * 1 Организовать отображение списка фильмов
     * 2 просмотр детальной информации об одном конкретном фильме, поиск фильма
     * 3 Реализация offline-mode, кэширование данных в локальную базу данных
     * 4 Просмотр трейлера к фильму
     * 5 Оптимизация под различные форматы(телефон, планшет)
     * //-------- особенности ------------------------------
     * 1 реализация автоподгрузки RecyclerView методами JavaRx
     * 2 доступ к кэшированным данным происходит без проверки доступности сети,
     *   а с помощью оператора merge javaRx
     */


    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // ----- AppComponent ---------------------------
        component = DaggerApplicationComponent
                .builder()
                .interactorModule(new InteractorModule())
                .mvpModelModule(new MvpModelModule())
                .repositoryModule(new RepositoryModule())
                .selectedLanguageModule(new SelectedLanguageModule(this))
                .build();

        // ----- realm configuration ---------------
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        // --- set application language ---
        setLocale(getSelectedLanguage());
    }

    public static ApplicationComponent component() {
        return component;
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    @Override
    public String getSelectedLanguage() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = sp.getString(getString(R.string.languagePref), getString(R.string.def_value));

        if (lang.equals(getString(R.string.def_value))) {
            // ------ get system language ------
            lang = Locale.getDefault().getISO3Language().substring(0, 2);
        }
        return lang;
    }

}
