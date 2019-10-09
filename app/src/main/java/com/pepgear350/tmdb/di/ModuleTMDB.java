package com.pepgear350.tmdb.di;

import android.app.Application;

import androidx.room.Room;

import com.pepgear350.tmdb.api.MoviesApi;
import com.pepgear350.tmdb.db.DataBaseTMDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.pepgear350.tmdb.util.Constants.BASE_URL;

@Module(includes = ViewModelModule.class)
class ModuleTMDB {

    @Provides
    @Singleton
    DataBaseTMDB provideDataBaseTMDB(Application application) {
        return Room.databaseBuilder(application, DataBaseTMDB.class, "databaseTMDB")
                .build();
    }

    @Singleton
    @Provides
    MoviesApi provideMoviesApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(MoviesApi.class);
    }


}
