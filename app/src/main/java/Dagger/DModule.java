package Dagger;

import android.app.Application;

import javax.inject.Singleton;

import Services.RestaurantDatabase;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author uka
 */
@Module
public class DModule {

    Application mApplication;

    RestaurantDatabase mRestaurantDatabase;

    public DModule(Application application){
        this.mApplication = application;
        mRestaurantDatabase = RestaurantDatabase.getDatabase(application);
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.doordash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    RestaurantDatabase provideDatabase(){
        return mRestaurantDatabase;
    }

}
