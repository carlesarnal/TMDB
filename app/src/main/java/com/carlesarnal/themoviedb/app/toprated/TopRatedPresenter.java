package com.carlesarnal.themoviedb.app.toprated;

import com.carlesarnal.themoviedb.api.ApiService;
import com.carlesarnal.themoviedb.api.model.Movies;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by carles on 17/01/18.
 */

public class TopRatedPresenter implements TopRatedContract.Presenter {
    private TopRatedContract.View view;
    private ApiService apiService;

    private int page = 1;

    @Inject
    TopRatedPresenter(TopRatedContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void start() {
        view.showLoading(false);
        getMovies(true);
    }

    @Override
    public void onPullToRefresh() {
        page = 1; // reset
        view.showLoading(true);
        getMovies(true);
    }

    @Override
    public void onScrollToBottom() {
        page++;
        view.showLoading(true);
        getMovies(false);
    }

    private void getMovies(final boolean isRefresh) {
        Call<Movies> call = apiService.getTopRatedMovies(page);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    view.showContent(response.body().movies, isRefresh);
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
