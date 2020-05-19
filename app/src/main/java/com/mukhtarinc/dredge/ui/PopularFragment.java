package com.mukhtarinc.dredge.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mukhtarinc.dredge.R;
import com.mukhtarinc.dredge.common.Constant;
import com.mukhtarinc.dredge.data.remote.PopularMoviesResponse;
import com.mukhtarinc.dredge.data.remote.ApiService;
import com.mukhtarinc.dredge.data.remote.RetrofitClient;
import com.mukhtarinc.dredge.model.Movie;
import com.mukhtarinc.dredge.viewModel.MovieViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private PopularRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    private PopularImageClickListener imageClickListener;
    private static final String TAG = PopularFragment.class.getSimpleName();

    public PopularFragment(PopularImageClickListener imageClickListener) {
        // Required empty public constructor
        this.imageClickListener = imageClickListener;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

       recyclerView = view.findViewById(R.id.recycler);


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(layoutManager);


       recyclerAdapter  = new PopularRecyclerAdapter(imageClickListener);


        return view;

}




    private void fetchMovies(){

        RetrofitClient retrofitClient = new RetrofitClient();

        ApiService service = retrofitClient.createService(ApiService.class);
        Call<PopularMoviesResponse> call = service.popularMovies(Constant.getApiKey());
        call.enqueue(new Callback<PopularMoviesResponse>() {
            @Override
            public void onResponse(@NotNull Call<PopularMoviesResponse> call, @NotNull Response<PopularMoviesResponse> response) {

                if(response.isSuccessful()) {


                    List<Movie> movies = null;
                    if (response.body() != null) {
                        movies = response.body().getData();


                        for (int i = 0; i < movies.size(); i++) {

                            Movie movie = movies.get(i);

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
            public void onFailure(Call<PopularMoviesResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: No data");
            }
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        movieViewModel.getAllmovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                recyclerAdapter.setData(movies);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });

        fetchMovies();


    }
}
