package andersonarmani.aaafoursquare;

import android.app.Application;

import andersonarmani.aaafoursquare.di.AppComponent;
import andersonarmani.aaafoursquare.di.DaggerAppComponent;
import andersonarmani.aaafoursquare.di.module.NetModule;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public class FoursquareApplication extends Application {
    private static final String BASE_URL = "https://api.foursquare.com/v2/";
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static AppComponent getsAppComponent() {
        return sAppComponent;
    }
}
