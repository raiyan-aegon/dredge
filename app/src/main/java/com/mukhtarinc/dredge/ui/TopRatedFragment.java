package com.mukhtarinc.dredge.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mukhtarinc.dredge.R;
import com.mukhtarinc.dredge.common.Constant;
import com.mukhtarinc.dredge.data.remote.ApiService;
import com.mukhtarinc.dredge.data.remote.RetrofitClient;
import com.mukhtarinc.dredge.data.remote.TopRatedMoviesResponse;
import com.mukhtarinc.dredge.model.TopRatedMovie;
import com.mukhtarinc.dredge.viewModel.TopRatedMovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment {

    private TopRatedMovieViewModel movieViewModel;
    private TopRatedRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

  public TopRatedFragment(){


  }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);

        recyclerView = view.findViewById(R.id.recycler_top_rated);


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(layoutManager);


        recyclerAdapter  = new TopRatedRecyclerAdapter();


        return view;
    }






    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postponeEnterTransition();




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startPostponedEnterTransition();
        movieViewModel = new ViewModelProvider(this).get(TopRatedMovieViewModel.class);


        movieViewModel.getAllmovies().observe(getViewLifecycleOwner(), new Observer<List<TopRatedMovie>>() {
            @Override
            public void onChanged(List<TopRatedMovie> movies) {

                recyclerAdapter.setData(movies);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        fetchTopMovies();

    }


    private void fetchTopMovies(){

        RetrofitClient retrofitClient = new RetrofitClient();

        ApiService service = retrofitClient.createService(ApiService.class);
        Call<TopRatedMoviesResponse> call = service.topratedMovies(Constant.getApiKey());
        call.enqueue(new Callback<TopRatedMoviesResponse>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {
                if(response.isSuccessful()) {


                    List<TopRatedMovie> movies = null;
                    if (response.body() != null) {
                        movies = response.body().getData();

                        for (int i = 0; i < movies.size(); i++) {

                            TopRatedMovie movie = movies.get(i);

                            movieViewModel.insert(movie);

                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 300);
                    }
                }
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {

            }
        });


    }
}
