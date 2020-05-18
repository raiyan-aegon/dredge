package com.mukhtarinc.dredge.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mukhtarinc.dredge.data.local.MovieDao;
import com.mukhtarinc.dredge.data.local.MoviesDatabase;
import com.mukhtarinc.dredge.model.Movie;

import java.util.List;

public class MovieRepository {

    private static final String TAG = "MovieRepository";
    private MovieDao movieDao;

    private LiveData<List<Movie>> movies;



    public MovieRepository(Application application) {

        MoviesDatabase db = MoviesDatabase.getDatabase(application);
        movieDao = db.movieDao();

        movies = movieDao.getListMovies();

    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void insert (Movie movie){

        new InsertAsyncTask(movieDao).execute(movie);
    }


    static class InsertAsyncTask extends AsyncTask<Movie,Void,Void>{
        final MovieDao asyncDao;

        public InsertAsyncTask(MovieDao asyncDao) {
            this.asyncDao = asyncDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            asyncDao.insert(movies[0]);

            return null;
        }
    }

}
