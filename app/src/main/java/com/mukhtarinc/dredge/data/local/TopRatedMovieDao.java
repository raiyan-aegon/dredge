package com.mukhtarinc.dredge.data.local;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mukhtarinc.dredge.model.TopRatedMovie;

import java.util.List;

@Dao
public interface TopRatedMovieDao {

    @Query("SELECT * FROM top_movies")
    LiveData<List<TopRatedMovie>> getListMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TopRatedMovie topRatedMovie);

    @Query("DELETE FROM top_movies")
    void deleteAll();

}
