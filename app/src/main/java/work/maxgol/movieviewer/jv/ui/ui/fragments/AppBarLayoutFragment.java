package work.maxgol.movieviewer.jv.ui.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import work.maxgol.movieviewer.R;

/**
 * Created by maxgol on 27.03.2017.
 */

public class AppBarLayoutFragment extends MvpAppCompatFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_bar_layout_fragment, container, false);
    }
}
