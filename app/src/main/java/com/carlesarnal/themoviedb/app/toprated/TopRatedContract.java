package com.carlesarnal.themoviedb.app.toprated;

import com.carlesarnal.themoviedb.api.model.Movie;

import java.util.List;

/**
 * Created by carles on 17/01/18.
 */


public interface TopRatedContract {

    interface View {

        void showLoading(boolean isRefresh);

        void showContent(List<Movie> movies, boolean isRefresh);

        void showError();


    }

    interface Presenter {

        void start();

        void onPullToRefresh();

        void onScrollToBottom();

        void finish();

    }

}
