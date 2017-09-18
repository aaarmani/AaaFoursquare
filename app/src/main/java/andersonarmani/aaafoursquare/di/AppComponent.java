package andersonarmani.aaafoursquare.di;

import javax.inject.Singleton;

import andersonarmani.aaafoursquare.di.module.NetModule;
import andersonarmani.aaafoursquare.di.module.RepositoryModule;
import andersonarmani.aaafoursquare.repository.FoursquareRepositoryImpl;
import andersonarmani.aaafoursquare.ui.activity.MapPresenterImpl;
import andersonarmani.aaafoursquare.ui.activity.itemDetail.ItemDetailActivity;
import dagger.Component;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */
@Singleton
@Component(modules = {NetModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(FoursquareRepositoryImpl foursquareRepository);
    void inject(MapPresenterImpl mapPresenter);
    void inject(ItemDetailActivity itemDetailActivity);
}