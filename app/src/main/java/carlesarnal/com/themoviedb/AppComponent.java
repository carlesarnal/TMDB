package carlesarnal.com.themoviedb;

import android.app.Application;

import javax.inject.Singleton;

import carlesarnal.com.themoviedb.api.ApiModule;
import carlesarnal.com.themoviedb.api.ApiService;
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
