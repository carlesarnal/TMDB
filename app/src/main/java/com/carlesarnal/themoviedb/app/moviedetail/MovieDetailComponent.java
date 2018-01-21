package com.carlesarnal.themoviedb.app.moviedetail;

import com.carlesarnal.themoviedb.app.ActivityScope;
import com.carlesarnal.themoviedb.app.AppComponent;

import dagger.Component;

/**
 * Created by carles on 21/01/18.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MovieDetailModule.class
)
interface MovieDetailComponent {

    void inject(MovieDetailActivity detailActivity);

}
