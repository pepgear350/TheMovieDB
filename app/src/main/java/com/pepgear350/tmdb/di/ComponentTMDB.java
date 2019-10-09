package com.pepgear350.tmdb.di;

import android.app.Application;

import com.pepgear350.tmdb.DaggerApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ModuleTMDB.class, ActivityModule.class})
public interface ComponentTMDB {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ComponentTMDB build();
    }

    void inject(DaggerApplication daggerApplication);

}
