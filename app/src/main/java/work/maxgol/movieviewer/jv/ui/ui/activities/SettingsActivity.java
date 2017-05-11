package work.maxgol.movieviewer.jv.ui.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.processphoenix.ProcessPhoenix;

import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.ui.mvp.view.SettingsActivityView;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.SettingsActivityPresenter;
import work.maxgol.movieviewer.jv.ui.ui.fragments.MyPrefFragment;
import work.maxgol.movieviewer.jv.ui.ui.interfaces.ClearCacheEvent;

import static work.maxgol.movieviewer.R.string.languagePref;

public class SettingsActivity extends MvpAppCompatActivity implements SettingsActivityView, ClearCacheEvent, SharedPreferences.OnSharedPreferenceChangeListener {

    @InjectPresenter
    SettingsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.onCreate();

        ActionBar actBar = getSupportActionBar();
        if (actBar != null) {
            actBar.setTitle(R.string.settings_title);
            actBar.setDisplayHomeAsUpEnabled(true);
        }

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPrefFragment()).commit();

        // --- detect change language ----
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clearCacheClick() {
        presenter.clearCache(getString(R.string.cache_cleared));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(languagePref)))
            presenter.changeLanguage();
    }

    // ======= view implementation ==============

    @Override
    public void reloadApp() {
        new Handler().postDelayed(() -> {
            ProcessPhoenix.triggerRebirth(this);
        }, 200);// 200 msec for writing preferences)

    }

    @Override
    public void showMessage(String mes) {
        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
    }
}
