package com.pepgear350.tmdb;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.pepgear350.tmdb.db.DataBaseTMDB;
import com.pepgear350.tmdb.db.dao.MoviesDAO;

import com.pepgear350.tmdb.db.entity.MoviesEntity;

import org.junit.After;
import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TestRule;
import org.junit.runner.RunWith;


import java.io.IOException;


import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class TestDataBase {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private MoviesDAO moviesDAO;
    private DataBaseTMDB dataBaseTMDB;
    private LiveData<MoviesEntity> movie;
    private Observer<MoviesEntity> observer;


    @Before
    public void createDb() {

        Context context = ApplicationProvider.getApplicationContext();
        dataBaseTMDB = Room.inMemoryDatabaseBuilder(context, DataBaseTMDB.class).build();
        moviesDAO = dataBaseTMDB.moviesDAO();
    }

    @After
    public void closedDb() throws IOException {
        dataBaseTMDB.close();
        movie.removeObserver(observer);
    }

    @Test
    public void testDB() {

        MoviesEntity moviesEntity = new MoviesEntity();
        moviesEntity.setId(1);
        moviesEntity.setTitle("test room");

        moviesDAO.saveMovie(moviesEntity);

       observer = moviesEntity1 ->
                assertEquals("test room", moviesEntity1.getTitle());


        movie = moviesDAO.getMovie(1);
        movie.observeForever(observer);


    }
}
