package com.pepgear350.tmdb.di;

import com.pepgear350.tmdb.ui.details.DetailsActivity;
import com.pepgear350.tmdb.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract DetailsActivity contributeDetailsActivity();

}
