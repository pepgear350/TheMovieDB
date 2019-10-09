package com.pepgear350.tmdb.network;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.pepgear350.tmdb.api.MoviesApi;
import com.pepgear350.tmdb.db.DataBaseTMDB;
import com.pepgear350.tmdb.db.entity.CreditsEntity;
import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.db.entity.VideoEntity;
import com.pepgear350.tmdb.model.credits.Credits;
import com.pepgear350.tmdb.model.movies.ResultsMovies;
import com.pepgear350.tmdb.model.video.Video;
import com.pepgear350.tmdb.util.Constants;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class NetworkDetails {

    private DataBaseTMDB dataBaseTMDB;
    private MoviesApi moviesApi;
    private MutableLiveData<String> liveDataError;

    @Inject
    public NetworkDetails(DataBaseTMDB dataBaseTMDB, MoviesApi moviesApi) {
        this.dataBaseTMDB = dataBaseTMDB;
        this.moviesApi = moviesApi;
    }

    @SuppressLint("CheckResult")
    public void fetchMovie(final int id_movie) {

        Observable<ResultsMovies> observable;

        observable = moviesApi.getMovieDetails(Constants.TOKEN, id_movie);


        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
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
                })
                .subscribe(this::handleResultsMovie, this::handleError);


    }

    @SuppressLint("CheckResult")
    public void fetchCredits(final int id_movie) {

        Observable<Credits> observable;
        observable = moviesApi.searchCredits(Constants.TOKEN, id_movie);

        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .flatMapIterable(credits -> Arrays.asList(credits.getCast()))
                .map(item -> {
                    CreditsEntity creditsEntity = new CreditsEntity();
                    creditsEntity.setId(item.getId());
                    creditsEntity.setId_movie(id_movie);
                    creditsEntity.setCharacter(item.getCharacter());
                    creditsEntity.setName(item.getName());
                    creditsEntity.setProfile_path(item.getProfile_path());
                    return creditsEntity;
                }).toList()
                .subscribe(this::handleResultsCredits, this::handleError);

    }


    @SuppressLint("CheckResult")
    public void fetchVideo(final int id_movie) {

        Observable<Video> observable;
        observable = moviesApi.searchVideos(Constants.TOKEN, id_movie);

        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .flatMapIterable(video -> Arrays.asList(video.getResults()))
                .map(item -> {
                    VideoEntity videoEntity = new VideoEntity();
                    videoEntity.setId(item.getId());
                    videoEntity.setId_movie(id_movie);
                    videoEntity.setKey(item.getKey());
                    return videoEntity;
                }).toList()
                .subscribe(this::handleResultsVideo, this::handleError);

    }

    private void handleResultsVideo(List<VideoEntity> videoEntities) {
        dataBaseTMDB.videoDAO().saveVideo(videoEntities);
    }

    private void handleResultsCredits(List<CreditsEntity> creditsEntities) {
        dataBaseTMDB.creditsDAO().saveCredits(creditsEntities);
    }


    private void handleResultsMovie(MoviesEntity movie) {
        dataBaseTMDB.moviesDAO().saveMovie(movie);
    }


    private void handleError(Throwable t) {
        liveDataError.postValue(t.getMessage());
    }


    public MutableLiveData<String> getLiveDataError() {
        if (liveDataError == null) {
            liveDataError = new MutableLiveData<>();
        }
        return liveDataError;
    }
}
