package com.carlesarnal.themoviedb.app.moviedetail;

import com.carlesarnal.themoviedb.api.ApiService;
import com.carlesarnal.themoviedb.api.model.Movie;
import com.carlesarnal.themoviedb.api.model.Movies;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by carles on 21/01/18.
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter {
        private MovieDetailContract.View view;

    private ApiService apiService;


    @Inject
        MovieDetailPresenter(MovieDetailContract.View view, ApiService apiService) {
            this.view = view;
            this.apiService = apiService;
        }

        @Override
        public void start(Movie movie) {
            view.showLoading();
            getMovies(movie);
        }

    private void getMovies(final Movie movie) {
        Call<Movies> call = apiService.getRelatedMovies(Integer.parseInt(movie.id));
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    view.showContent(movie, response.body().movies);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                view.showError();
            }
        });
    }


        @Override
        public void finish() {

        }

    }
