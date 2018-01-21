package com.carlesarnal.themoviedb.app.moviedetail;

import com.carlesarnal.themoviedb.app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carles on 21/01/18.
 */


@Module
public class MovieDetailModule {
    private final MovieDetailContract.View detailView;

    MovieDetailModule(MovieDetailContract.View detailView) {
        this.detailView = detailView;
    }

    @Provides
    @ActivityScope
    MovieDetailContract.View provideDetailView() {
        return detailView;
    }

}
