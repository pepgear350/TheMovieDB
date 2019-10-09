package com.pepgear350.tmdb.network;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.pepgear350.tmdb.api.MoviesApi;
import com.pepgear350.tmdb.db.DataBaseTMDB;
import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.model.movies.Movies;
import com.pepgear350.tmdb.util.Constants;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class NetworkMain {

    private DataBaseTMDB dataBaseTMDB;
    private MoviesApi moviesApi;
    private MutableLiveData<List<MoviesEntity>> liveDataSearchOnline;
    private MutableLiveData<String> liveDataError;

    @Inject
    public NetworkMain(DataBaseTMDB dataBaseTMDB, MoviesApi moviesApi) {
        this.dataBaseTMDB = dataBaseTMDB;
        this.moviesApi = moviesApi;
    }

    @SuppressLint("CheckResult")
    public void fetchMovies(final int category) {

        Observable<Movies> observable;

        switch (category) {
            case 2:
                observable = moviesApi.getTopRated(Constants.TOKEN);
                break;

            case 3:
                observable = moviesApi.getUpcoming(Constants.TOKEN);
                break;

            default:
                observable = moviesApi.getPopular(Constants.TOKEN);
                break;
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMapIterable(movies -> Arrays.asList(movies.getResults()))
                .map(item -> {
                    MoviesEntity moviesEntity = new MoviesEntity();
                    moviesEntity.setId(item.getId());
                    moviesEntity.setTitle(item.getTitle());
                    moviesEntity.setBackdrop_path(item.getBackdrop_path());
                    moviesEntity.setOverview(item.getOverview());
                    moviesEntity.setRelease_date(item.getRelease_date());
                    moviesEntity.setVote_average(item.getVote_average());
                    moviesEntity.setCategory(category);
                    moviesEntity.setPoster_path(item.getPoster_path());
                    return moviesEntity;
                }).toList()
                .subscribe(this::handleResults, this::handleError);


    }

    @SuppressLint("CheckResult")
    public void fetchMoviesOnline(String query) {

        Observable<Movies> observable;
        observable = moviesApi.searchMovies(Constants.TOKEN, query);

        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMapIterable(movies -> Arrays.asList(movies.getResults()))
                .map(item -> {
                    MoviesEntity moviesEntity = new MoviesEntity();
                    moviesEntity.setId(item.getId());
                    moviesEntity.setTitle(item.getTitle());
                    moviesEntity.setBackdrop_path(item.getBackdrop_path());
                    moviesEntity.setOverview(item.getOverview());
                    moviesEntity.setRelease_date(item.getRelease_date());
                    moviesEntity.setVote_average(item.getVote_average());
                    moviesEntity.setPoster_path(item.getPoster_path());
                    return moviesEntity;
                }).toList()
                .subscribe(this::handleResultsOnline, this::handleError);

    }

    private void handleResultsOnline(List<MoviesEntity> listMovies) {
        liveDataSearchOnline.postValue(listMovies);
    }

    private void handleResults(List<MoviesEntity> listMovies) {
        dataBaseTMDB.moviesDAO().saveMovies(listMovies);
    }

    private void handleError(Throwable t) {
        liveDataError.postValue(t.getMessage());
    }

    public MutableLiveData<List<MoviesEntity>> getLiveDataSearchOnline() {
        if (liveDataSearchOnline == null) {
            liveDataSearchOnline = new MutableLiveData<>();
        }
        return liveDataSearchOnline;
    }


    public MutableLiveData<String> getLiveDataError() {
        if (liveDataError == null) {
            liveDataError = new MutableLiveData<>();
        }
        return liveDataError;
    }
}
