package com.carlesarnal.themoviedb.app.toprated;

import com.carlesarnal.themoviedb.app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carles on 17/01/18.
 */

@Module
public class TopRatedModule {
    private final TopRatedContract.View topRatedVIew;

    TopRatedModule(TopRatedContract.View topRatedVIew) {
        this.topRatedVIew = topRatedVIew;
    }

    @Provides
    @ActivityScope
    TopRatedContract.View provideTopRatedView() {
        return topRatedVIew;
    }

}
