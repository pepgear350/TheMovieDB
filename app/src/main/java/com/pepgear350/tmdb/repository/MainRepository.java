package com.pepgear350.tmdb.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pepgear350.tmdb.db.DataBaseTMDB;
import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.network.NetworkMain;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainRepository {


    private NetworkMain networkMain;
    private DataBaseTMDB dataBaseTMDB;

    @Inject
    public MainRepository(NetworkMain networkMain, DataBaseTMDB dataBaseTMDB) {
        this.networkMain = networkMain;
        this.dataBaseTMDB = dataBaseTMDB;
    }

    public LiveData<List<MoviesEntity>> getAllMovies(int category) {
        networkMain.fetchMovies(category);
        return dataBaseTMDB.moviesDAO().loadAllMovies(category);
    }

    public LiveData<List<MoviesEntity>> getSearchMovies(String query, int category) {
        return dataBaseTMDB.moviesDAO().findMovies(query, category);
    }


    public LiveData<List<MoviesEntity>> getSearchMoviesOnline(String query) {
        MutableLiveData<List<MoviesEntity>> liveData = networkMain.getLiveDataSearchOnline();
        networkMain.fetchMoviesOnline(query);
        return liveData;
    }

    public MutableLiveData<String> getLiveDataError() {
        return networkMain.getLiveDataError();
    }
}
