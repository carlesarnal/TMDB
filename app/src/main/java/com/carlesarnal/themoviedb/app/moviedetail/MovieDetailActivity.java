package com.carlesarnal.themoviedb.app.moviedetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carlesarnal.themoviedb.R;
import com.carlesarnal.themoviedb.api.model.Movie;
import com.carlesarnal.themoviedb.app.App;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by carles on 21/01/18.
 */

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View, OnSwipeTouchListener.OnSwipeListener {

    @Inject
    MovieDetailPresenter detailPresenter;

    @BindView(R.id.container)
    View contentView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.overviewHeader)
    View overviewHeader;
    @BindView(R.id.overviewTextView)
    TextView overviewTextView;
    @BindView(R.id.textView)
    View errorView;
    @BindView(R.id.progressBar)
    View loadingView;

    private Movie movie = null;
    private final static String movieId = "movie";
    private OnSwipeTouchListener onSwipeTouchListener;
    private List<Movie> movies;
    private int currentIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        setupContentView();
        DaggerMovieDetailComponent.builder()
                .appComponent(App.getAppComponent(getApplication()))
                .movieDetailModule(new MovieDetailModule(this))
                .build()
                .inject(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movie = extras.getParcelable(movieId);
            setTitle(movie.title);
        }
    }

    private void setupContentView() {
        onSwipeTouchListener = new OnSwipeTouchListener(this, this);
        contentView.setOnTouchListener(onSwipeTouchListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
        detailPresenter.start(movie);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        showContent(false);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showContent(Movie movie, List<Movie> movies) {
        this.movies = movies;
        setCurrentMovie(movie);
    }

    private void setCurrentMovie(Movie movie) {
        String fullImageUrl = getFullImageUrl(movie);

        if (!fullImageUrl.isEmpty()) {
            Glide.with(this)
                    .load(fullImageUrl)
                    .centerCrop()
                    .crossFade()
                    .into(imageView);
        }

        overviewTextView.setText(getOverview(movie.overview));

        loadingView.setVisibility(View.GONE);
        showContent(true);
        errorView.setVisibility(View.GONE);
    }


    private String getOverview(String overview) {
        return TextUtils.isEmpty(overview) ? "-" : overview;
    }

    @NonNull
    private String getFullImageUrl(Movie movie) {
        String imagePath = "";

        if (movie.posterPath != null && !movie.posterPath.isEmpty()) {
            imagePath = getString(R.string.images_base_url) + movie.posterPath;
        } else {
            imagePath = getString(R.string.images_base_url) + movie.backdropPath;
        }

        return imagePath;
    }

    @Override
    public void showError() {
        loadingView.setVisibility(View.GONE);
        showContent(false);
        errorView.setVisibility(View.VISIBLE);
    }


    private void showContent(boolean show) {
        int visibility = show ? View.VISIBLE : View.INVISIBLE;

        contentView.setVisibility(visibility);
        overviewHeader.setVisibility(visibility);
        overviewTextView.setVisibility(visibility);
    }

    @Override
    public void onSwipeLeft() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = movies.size() -1;
        }
        movie = movies.get(currentIndex);
        setCurrentMovie(movies.get(currentIndex));
        setTitle(movie.title);
    }

    @Override
    public void onSwipeRight() {
        currentIndex++;
        if (currentIndex > movies.size() - 1) {
            currentIndex = 0;
        }
        movie = movies.get(currentIndex);
        setCurrentMovie(movie);
        setTitle(movie.title);
    }
}
