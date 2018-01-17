package com.carlesarnal.themoviedb.app.toprated;


import com.carlesarnal.themoviedb.app.ActivityScope;
import com.carlesarnal.themoviedb.app.AppComponent;

import dagger.Component;

/**
 * Created by carles on 17/01/18.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = TopRatedModule.class
)
interface TopRatedComponent {

    void inject (TopRatedActivity topRatedActivity);

}
