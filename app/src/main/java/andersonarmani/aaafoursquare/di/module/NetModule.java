package andersonarmani.aaafoursquare.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import andersonarmani.aaafoursquare.api.FoursquareService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

@Module
public class NetModule {
    private String mBaseUrl;

    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    FoursquareService provideFoursquareService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(FoursquareService.class);
    }
}
