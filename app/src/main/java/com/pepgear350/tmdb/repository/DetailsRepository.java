package com.pepgear350.tmdb.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pepgear350.tmdb.db.DataBaseTMDB;
import com.pepgear350.tmdb.db.entity.CreditsEntity;
import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.db.entity.VideoEntity;
import com.pepgear350.tmdb.network.NetworkDetails;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DetailsRepository {

    private DataBaseTMDB dataBaseTMDB;
    private NetworkDetails networkDetails;

    @Inject
    public DetailsRepository(DataBaseTMDB dataBaseTMDB, NetworkDetails networkDetails) {
        this.dataBaseTMDB = dataBaseTMDB;
        this.networkDetails = networkDetails;
    }


    public LiveData<MoviesEntity> getMovie(int id_movie) {
        networkDetails.fetchMovie(id_movie);
        return dataBaseTMDB.moviesDAO().getMovie(id_movie);
    }

    public LiveData<List<CreditsEntity>> loadCredist(int id_movie) {
        networkDetails.fetchCredits(id_movie);
        return dataBaseTMDB.creditsDAO().loadCredist(id_movie);
    }

    public LiveData<List<VideoEntity>> loadVideo(int id_movie) {
        networkDetails.fetchVideo(id_movie);
        return dataBaseTMDB.videoDAO().loadVideo(id_movie);
    }

    public MutableLiveData<String> getLiveDataError() {
        return networkDetails.getLiveDataError();
    }
}
