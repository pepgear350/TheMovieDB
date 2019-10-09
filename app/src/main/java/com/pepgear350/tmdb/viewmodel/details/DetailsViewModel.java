package com.pepgear350.tmdb.viewmodel.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pepgear350.tmdb.db.entity.CreditsEntity;
import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.db.entity.VideoEntity;
import com.pepgear350.tmdb.repository.DetailsRepository;

import java.util.List;

import javax.inject.Inject;

public class DetailsViewModel extends ViewModel {


    private DetailsRepository detailsRepository;
    private LiveData<MoviesEntity> liveDataMovie;
    private LiveData<List<CreditsEntity>> liveDataCredits;
    private LiveData<List<VideoEntity>> liveDataVideo;

    @Inject
    public DetailsViewModel(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public LiveData<MoviesEntity> getLiveDataMovie(int id_movie) {
        if (liveDataMovie == null) {
            liveDataMovie = detailsRepository.getMovie(id_movie);
        }
        return liveDataMovie;
    }

    public LiveData<List<CreditsEntity>> getLiveDataCredits(int id_movie) {
        if (liveDataCredits == null) {
            liveDataCredits = detailsRepository.loadCredist(id_movie);
        }
        return liveDataCredits;
    }

    public LiveData<List<VideoEntity>> getLiveDataVideo(int id_movie) {
        if (liveDataVideo == null) {
            liveDataVideo = detailsRepository.loadVideo(id_movie);
        }
        return liveDataVideo;
    }

    public MutableLiveData<String> getLiveDataError() {
        return detailsRepository.getLiveDataError();
    }
}
