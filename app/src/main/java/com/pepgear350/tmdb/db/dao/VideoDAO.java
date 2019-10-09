package com.pepgear350.tmdb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pepgear350.tmdb.db.entity.VideoEntity;

import java.util.List;

@Dao
public interface VideoDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveVideo(List<VideoEntity> videoEntities);


    @Query("SELECT * FROM video WHERE id_movie = :value ")
    LiveData<List<VideoEntity>> loadVideo(int value);

}
