package com.mukhtarinc.dredge.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mukhtarinc.dredge.data.local.MoviesDatabase;
import com.mukhtarinc.dredge.data.local.TopRatedMovieDao;
import com.mukhtarinc.dredge.model.TopRatedMovie;

import java.util.List;

public class TopRatedMovieRepository {

    private static final String TAG = "MovieRepository";
    private TopRatedMovieDao topRatedMovieDao;

    private LiveData<List<TopRatedMovie>> movies;



    public TopRatedMovieRepository(Application application) {

        MoviesDatabase db = MoviesDatabase.getDatabase(application);
        topRatedMovieDao = db.topRatedMovieDao();

        movies = topRatedMovieDao.getListMovies();

    }

    public LiveData<List<TopRatedMovie>> getMovies() {
        return movies;
    }

    public void insert (TopRatedMovie topRatedMovie){

        new InsertAsyncTask(topRatedMovieDao).execute(topRatedMovie);
    }


    static class InsertAsyncTask extends AsyncTask<TopRatedMovie,Void,Void>{
        final TopRatedMovieDao asyncDao;

        public InsertAsyncTask(TopRatedMovieDao asyncDao) {
            this.asyncDao = asyncDao;
        }

        @Override
        protected Void doInBackground(TopRatedMovie... movies) {

            asyncDao.insert(movies[0]);

            return null;
        }
    }

}
