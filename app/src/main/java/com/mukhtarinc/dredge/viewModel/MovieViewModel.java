package com.mukhtarinc.dredge.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.mukhtarinc.dredge.model.Movie;
import com.mukhtarinc.dredge.repository.MovieRepository;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;


    private final LiveData<List<Movie>> allmovies;
    private static final String TAG = "MovieViewModel";


    public MovieViewModel(Application application){

        super(application);

        repository = new MovieRepository(application);

        allmovies = repository.getMovies();

    }

    public LiveData<List<Movie>> getAllmovies() {

        return allmovies;
    }

    public void insert(Movie movie){
        repository.insert(movie);
    }

}
