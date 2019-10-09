package com.pepgear350.tmdb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pepgear350.tmdb.db.entity.MoviesEntity;

import java.util.List;

@Dao
public interface MoviesDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MoviesEntity> moviesEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovie(MoviesEntity moviesEntities);


    @Query("SELECT * FROM movies WHERE category = :value ")
    LiveData<List<MoviesEntity>> loadAllMovies(int value);

    @Query("SELECT * FROM movies WHERE category = :category AND title LIKE  '%' ||:query|| '%'")
    LiveData<List<MoviesEntity>> findMovies(String query, int category);

    @Query("SELECT * FROM movies WHERE id = :value ")
    LiveData<MoviesEntity> getMovie(int value);
}
