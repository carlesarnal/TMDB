package com.carlesarnal.themoviedb.app.moviedetail;

import com.carlesarnal.themoviedb.api.model.Movie;

import java.util.List;

/**
 * Created by carles on 21/01/18.
 */

public interface MovieDetailContract {

    interface View {

        void showLoading();

        void showContent(Movie movie, List<Movie> movies);

        void showError();

    }

    interface Presenter {

        void start(Movie movie);

        void finish();

    }

}
