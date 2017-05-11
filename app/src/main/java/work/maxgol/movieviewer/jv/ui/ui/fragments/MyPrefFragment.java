package work.maxgol.movieviewer.jv.ui.ui.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.ui.ui.interfaces.ClearCacheEvent;

/**
 * Created by maxgol on 10.04.2017.
 */

public class MyPrefFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference clearCache = findPreference(getString(R.string.cc_pref));
        clearCache.setOnPreferenceClickListener(this);
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        ((ClearCacheEvent) getActivity()).clearCacheClick();
        return false;
    }
}
