package com.pepgear350.tmdb.viewmodel.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.repository.MainRepository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private LiveData<List<MoviesEntity>> liveDataMovies;

    private MainRepository mainRepository;

    @Inject
    public MainViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }


    public LiveData<List<MoviesEntity>> getLiveDataMovies() {
        if (liveDataMovies == null) {
            liveDataMovies = new MutableLiveData<>();
        }
        return liveDataMovies;
    }


    public LiveData<List<MoviesEntity>> getAllMovies(int category) {
        liveDataMovies = mainRepository.getAllMovies(category);
        return liveDataMovies;
    }


    public LiveData<List<MoviesEntity>> getSearchMovies(String query, int category) {
        liveDataMovies = mainRepository.getSearchMovies(query, category);
        return liveDataMovies;
    }

    public LiveData<List<MoviesEntity>> getSearchMoviesOnline(String query) {
        liveDataMovies = mainRepository.getSearchMoviesOnline(query);
        return liveDataMovies;
    }


    public MutableLiveData<String> getLiveDataError() {
        return mainRepository.getLiveDataError();
    }
}
