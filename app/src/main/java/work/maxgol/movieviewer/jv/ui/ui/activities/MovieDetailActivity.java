package work.maxgol.movieviewer.jv.ui.ui.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.domain.model.Video;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.MovieDetailedActivityPresenter;
import work.maxgol.movieviewer.jv.ui.mvp.view.MovieDetailedActivityView;
import work.maxgol.movieviewer.jv.ui.ui.fragments.MovieDetailFragment;
import work.maxgol.movieviewer.jv.ui.ui.util.MyFloatMenuDirector;

public class MovieDetailActivity extends MvpAppCompatActivity implements MovieDetailedActivityView {

    @InjectPresenter
    MovieDetailedActivityPresenter presenter;

    MyFloatMenuDirector floatMenuDirector;
    com.github.clans.fab.FloatingActionMenu fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        AppBarLayout appBarLayout = ((AppBarLayout) findViewById(R.id.app_bar));
        setSupportActionBar(toolbar);
        appBarLayout.setExpanded(false, false);

        fabMenu = (com.github.clans.fab.FloatingActionMenu) findViewById(R.id.fabMenu);
        floatMenuDirector = new MyFloatMenuDirector(this, fabMenu);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            MovieDetailFragment fragment = new MovieDetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }

        presenter.onCreate();
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
    public void showTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setFloatButtonState(List<Video> list) {
        floatMenuDirector.setFloatButtonState(list);
    }

}
