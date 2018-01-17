package com.carlesarnal.themoviedb.app;

import android.app.Application;

import com.carlesarnal.themoviedb.api.ApiModule;
import com.carlesarnal.themoviedb.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by carles on 17/01/18.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class
        }
)
public interface AppComponent {

    Application application();

    ApiService apiService();

    void inject(App app);

}
