package andersonarmani.aaafoursquare.di.module;

import javax.inject.Singleton;

import andersonarmani.aaafoursquare.repository.FoursquareRepository;
import andersonarmani.aaafoursquare.repository.FoursquareRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    FoursquareRepository provideFoursquareRepository() {
        return new FoursquareRepositoryImpl();
    }
}
