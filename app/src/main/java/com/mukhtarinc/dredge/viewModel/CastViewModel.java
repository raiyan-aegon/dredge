package com.mukhtarinc.dredge.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.mukhtarinc.dredge.model.Cast;

import java.util.List;

public class CastViewModel  extends AndroidViewModel {

    private List<Cast> getAllCast;
    private static final String TAG = "CastViewModel";

    private String movie_id;

    public CastViewModel(Application application) {
        super(application);


    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public List<Cast> getGetAllCast() {
        return getAllCast;
    }
}
