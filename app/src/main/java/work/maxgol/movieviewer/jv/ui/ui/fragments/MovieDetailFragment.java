package work.maxgol.movieviewer.jv.ui.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.common.constants.ApiConstants;
import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.domain.model.MovieDetailed;
import work.maxgol.movieviewer.jv.ui.mvp.view.MovieDetailedFragmentView;
import work.maxgol.movieviewer.jv.ui.mvp.presenter.MovieDetailedFragmentPresenter;

/**
 * Created by Maxgol on 13.03.2017.
 */

public class MovieDetailFragment extends MvpAppCompatFragment implements MovieDetailedFragmentView {


    TextView movieDetailText;
    TextView releaseDate;
    TextView language;
    TextView voteCount;
    TextView voteAverage;
    ImageView movieDetailImage;
    ImageView movieDetailImage2;
    ImageView movieDetailImage3;

    CollapsingToolbarLayout appBarLayout;
    TextView budget;
    TextView original_title;
    TextView popularity;
    TextView revenue;
    TextView status;
    TextView tagline;
    TextView genres;
    TextView productionCompanies;
    TextView productionCountries;
    TextView spokenLanguages;
    TextView descTitle;

    TableRow lHideView;
    TableRow cHideView;
    TableRow pHideView;
    TableRow gHideView;
    TableRow tgHideView;


    @InjectPresenter
    MovieDetailedFragmentPresenter presenter;

    // =========== fragment lifeCycle ========================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        presenter.onViewCreated();
    }

    // =======================================================

    void setupViews(View view) {
        movieDetailText = (TextView) view.findViewById(R.id.movie_detail_text);
        releaseDate = (TextView) view.findViewById(R.id.release_date);
        language = (TextView) view.findViewById(R.id.language);
        voteCount = (TextView) view.findViewById(R.id.vote_count);
        voteAverage = (TextView) view.findViewById(R.id.vote_average);
        movieDetailImage = ((ImageView) view.findViewById(R.id.movie_detail_image));
        movieDetailImage2 = (ImageView) view.findViewById(R.id.movie_detail_image2);
        movieDetailImage3 = ((ImageView) view.findViewById(R.id.movie_detail_image3));
        appBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        budget = (TextView) view.findViewById(R.id.budget);
        original_title = (TextView) view.findViewById(R.id.original_title);
        popularity = (TextView) view.findViewById(R.id.popularity);
        revenue = (TextView) view.findViewById(R.id.revenue);
        status = (TextView) view.findViewById(R.id.status);
        tagline = (TextView) view.findViewById(R.id.tagline);
        genres = (TextView) view.findViewById(R.id.genres);
        productionCompanies = (TextView) view.findViewById(R.id.productionCompanies);
        productionCountries = (TextView) view.findViewById(R.id.productionCountries);
        spokenLanguages = (TextView) view.findViewById(R.id.spokenLanguages);
        descTitle = (TextView) view.findViewById(R.id.descTitle);
        lHideView = (TableRow) view.findViewById(R.id.lHideView);
        cHideView = (TableRow) view.findViewById(R.id.cHideView);
        pHideView = (TableRow) view.findViewById(R.id.pHideView);
        gHideView = (TableRow) view.findViewById(R.id.gHideView);
        tgHideView = (TableRow) view.findViewById(R.id.tgHideView);
    }

    void hideIfEmptyList(String str, TextView displayView, View hideView) {
        if (str == null || str.length() == 0) {
            hideView.setVisibility(View.GONE);
            return;
        }

        hideView.setVisibility(View.VISIBLE);


        displayView.setText(str);
    }

    void hideViewIfEmptyText(String data, TextView displayView, View hideView) {

        if (displayView.getText().toString().trim().length() > 0)
            return;

        if (data == null || data.equals("")) {
            hideView.setVisibility(View.GONE);
            return;
        }

        hideView.setVisibility(View.VISIBLE);
        displayView.setText(data);
    }

    void loadImage(String path, ImageView iv) {
        Glide.with(getContext())
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(iv);
    }

    // ============== view interfaces =======================

    @Override
    public void setDetailedMovieInfo(MovieDetailed dInfo) {
        // ------------- set description ----------------
        releaseDate.setText(dInfo.getRelease_date());
        language.setText(dInfo.getOriginal_language());
        voteCount.setText(String.valueOf(dInfo.getVote_count()));
        voteAverage.setText(String.valueOf(dInfo.getVote_average()));
        budget.setText(String.valueOf(dInfo.getBudget()));
        original_title.setText(dInfo.getOriginal_title());
        popularity.setText(String.valueOf(dInfo.getPopularity()));
        revenue.setText(String.valueOf(dInfo.getRevenue()));
        status.setText(dInfo.getStatus());


        hideViewIfEmptyText(dInfo.getOverview(), movieDetailText, descTitle);
        hideViewIfEmptyText(dInfo.getTagline(), tagline, tgHideView);

        hideIfEmptyList(dInfo.getGenres(), genres, gHideView);
        hideIfEmptyList(dInfo.getProductionCompanies(), productionCompanies, pHideView);
        hideIfEmptyList(dInfo.getProductionCountries(), productionCountries, cHideView);
        hideIfEmptyList(dInfo.getSpokenLanguages(), spokenLanguages, lHideView);


        if (appBarLayout != null) {
            appBarLayout.setTitle(dInfo.getTitle());
        }
    }

    @Override
    public void setMovieInfo(Movie mi) {
        hideViewIfEmptyText(mi.getOverview(), movieDetailText, descTitle);
        loadImage(ApiConstants.Glide.imagePath342 + mi.getBackdrop_path(), movieDetailImage);
        loadImage(ApiConstants.Glide.imagePath342 + mi.getPoster_path(), movieDetailImage2);
    }

    @Override
    public void loadImageWithoutDouble(String path) {
        if (path == null)
            return;
        loadImage(ApiConstants.Glide.imagePath342 + path, movieDetailImage3);
    }
}
