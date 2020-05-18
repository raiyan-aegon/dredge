package com.mukhtarinc.dredge.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mukhtarinc.dredge.model.Movie;
import com.mukhtarinc.dredge.model.TopRatedMovie;

@Database(entities = {Movie.class, TopRatedMovie.class}, version = 2,exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract TopRatedMovieDao topRatedMovieDao();

    private static MoviesDatabase instance;


    public static  MoviesDatabase getDatabase(final Context context){

        if(instance == null){

            synchronized (MoviesDatabase.class){
                if(instance==null){

                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class,"movies.db")
                            .fallbackToDestructiveMigration()
                            .build();


                }
            }
        }

        return instance;
    }
}
