package work.maxgol.movieviewer.jv.ui.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import rx.Observable;
import rx.subscriptions.Subscriptions;
import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.Video;
import work.maxgol.movieviewer.jv.ui.mvp.view.MainActivityView;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.MainActivityPresenter;
import work.maxgol.movieviewer.jv.ui.ui.adapters.MyRecyclerViewAdapter;
import work.maxgol.movieviewer.jv.ui.ui.fragments.MovieDetailFragment;
import work.maxgol.movieviewer.jv.ui.ui.interfaces.RecyclerItemClickListener;
import work.maxgol.movieviewer.jv.ui.ui.util.MyFloatMenuDirector;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView, RecyclerItemClickListener, MenuItem.OnMenuItemClickListener {

    GridLayoutManager manager;
    RecyclerView recyclerView;
    TextView tvNotesIsEmpty;
    SearchView searchView;
    Toolbar toolbar;
    MenuItem item;
    com.github.clans.fab.FloatingActionMenu fabMenu;

    @InjectPresenter
    MainActivityPresenter presenter;
    MyFloatMenuDirector floatMenuDirector;


    // activity lifecycle =================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupElements();
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
        presenter.setObservables(getSearchObservable(), getScrollObservable());
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //--- save last scrolled position  for append ---
        presenter.saveState(manager);
    }

    // ====================================

    void setupElements() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);

        tvNotesIsEmpty = (TextView) findViewById(R.id.tvNotesIsEmpty);

        // ---- isTwoPanePanel -----
        presenter.setPaneState(findViewById(R.id.movie_detail_container) != null);

        // ---- set  searchView ----
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        Menu menu = toolbar.getMenu();

        item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
        fabMenu = (com.github.clans.fab.FloatingActionMenu) findViewById(R.id.fabMenu);
        floatMenuDirector = new MyFloatMenuDirector(this, fabMenu);

        menu.findItem(R.id.action_settings).setOnMenuItemClickListener(this);
    }

    private Observable<String> getSearchObservable() {
        return Observable.create(subscriber -> {
            SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    subscriber.onNext(newText.trim());
                    return false;
                }
            };
            searchView.setOnQueryTextListener(listener);
        });
    }

    private Observable<Integer> getScrollObservable() {
        return Observable.create(subscriber -> {
            final RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {

                boolean shouldLoad(int count) {
                    int position = manager.findLastVisibleItemPosition();
                    if (position == count - 1)
                        return true;
                    return false;
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int itemsCount = recyclerView.getAdapter().getItemCount();
                    if (!subscriber.isUnsubscribed() && shouldLoad(itemsCount)) {
                        subscriber.onNext(itemsCount);
                    }
                }
            };

            recyclerView.addOnScrollListener(sl);
            subscriber.add(Subscriptions.create(() -> recyclerView.removeOnScrollListener(sl)));
        });
    }

    @Override
    public void itemClick(int selPos) {
        presenter.recyclerClickPerform(selPos);
    }

    @Override
    public boolean onMenuItemClick(MenuItem v) {

        switch (v.getItemId()) {
            case R.id.action_settings:
                presenter.startSettingsActivity();
                break;

        }
        return false;
    }

    // ======= view implementation ==============

    @Override
    public void updateView() {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            tvNotesIsEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvNotesIsEmpty.setVisibility(View.GONE);
        }
    }


    @Override
    public void startMovieDetailedActivity() {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void setRecyclerViewAdapter(List<Movie> movies) {
        if (recyclerView.getAdapter() == null)
            recyclerView.setAdapter(new MyRecyclerViewAdapter(this, movies));
    }

    @Override
    public void restoreViewsState(String query, int restorePos) {

        // --- scroll to saved position ---
        manager.scrollToPositionWithOffset(restorePos, 0);

        // --- set saved Search request ---
        searchView.setQuery(query, true);
    }

    @Override
    public void showMessage(String mes) {
        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDetailsInFragment() {
        MovieDetailFragment fragment = new MovieDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.movie_detail_container, fragment)
                .commit();
    }

    @Override
    public void setFloatButtonState(List<Video> list) {
        if (list.size() == 0) {
            fabMenu.setVisibility(View.INVISIBLE);
            return;
        }
        floatMenuDirector.setFloatButtonState(list);
    }
}
