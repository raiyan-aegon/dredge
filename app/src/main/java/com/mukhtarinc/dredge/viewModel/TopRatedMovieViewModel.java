package com.mukhtarinc.dredge.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mukhtarinc.dredge.model.TopRatedMovie;
import com.mukhtarinc.dredge.repository.TopRatedMovieRepository;

import java.util.List;

public class TopRatedMovieViewModel extends AndroidViewModel {
    private TopRatedMovieRepository repository;


    private final LiveData<List<TopRatedMovie>> allmovies;
    private static final String TAG = "MovieViewModel";


    public TopRatedMovieViewModel(Application application){

        super(application);

        repository = new TopRatedMovieRepository(application);

        allmovies = repository.getMovies();

    }

    public LiveData<List<TopRatedMovie>> getAllmovies() {

        return allmovies;
    }

    public void insert(TopRatedMovie topRatedMovie){
        repository.insert(topRatedMovie);
    }

}
