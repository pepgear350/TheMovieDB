package com.pepgear350.tmdb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pepgear350.tmdb.db.entity.CreditsEntity;

import java.util.List;

@Dao
public interface CreditsDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCredits(List<CreditsEntity> creditsEntities);


    @Query("SELECT * FROM credits WHERE id_movie = :value ")
    LiveData<List<CreditsEntity>> loadCredist(int value);

}
