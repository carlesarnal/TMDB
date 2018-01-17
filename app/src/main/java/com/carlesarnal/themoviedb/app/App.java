package com.carlesarnal.themoviedb.app;

import android.app.Application;

import com.carlesarnal.themoviedb.api.ApiModule;
import com.carlesarnal.themoviedb.R;


/**
 * Created by carles on 17/01/18.
 */


public class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule(getString(R.string.base_url), getString(R.string.api_key)))
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Application application) {
        return ((App) application).getAppComponent();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
