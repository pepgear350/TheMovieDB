package com.pepgear350.tmdb.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pepgear350.tmdb.db.dao.CreditsDAO;
import com.pepgear350.tmdb.db.dao.MoviesDAO;
import com.pepgear350.tmdb.db.dao.VideoDAO;
import com.pepgear350.tmdb.db.entity.CreditsEntity;
import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.db.entity.VideoEntity;


@Database(entities = {MoviesEntity.class, CreditsEntity.class, VideoEntity.class}, version = 1 , exportSchema = false)
public abstract class DataBaseTMDB extends RoomDatabase {

    public abstract MoviesDAO moviesDAO();

    public abstract CreditsDAO creditsDAO();

    public abstract VideoDAO videoDAO();

}
