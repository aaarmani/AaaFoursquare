package andersonarmani.aaafoursquare.di;

import javax.inject.Singleton;

import andersonarmani.aaafoursquare.di.module.NetModule;
import andersonarmani.aaafoursquare.ui.activity.MainActivity;
import dagger.Component;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */
@Singleton
@Component(modules = NetModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}