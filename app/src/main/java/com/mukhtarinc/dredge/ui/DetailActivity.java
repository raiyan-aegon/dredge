package com.mukhtarinc.dredge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.mukhtarinc.dredge.R;
import com.mukhtarinc.dredge.common.Constant;
import com.mukhtarinc.dredge.data.remote.ApiService;
import com.mukhtarinc.dredge.data.remote.CastResponse;
import com.mukhtarinc.dredge.data.remote.RetrofitClient;
import com.mukhtarinc.dredge.data.remote.TrailersResponse;
import com.mukhtarinc.dredge.model.Movie;
import com.mukhtarinc.dredge.model.Trailer;
import com.mukhtarinc.dredge.viewModel.CastViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements TrailerAdapter.TrailerClickListener{

    private CastViewModel castViewModel;
    private static final String TAG = "DetailActivity";
    private CastAdapter castAdapter;
    private TrailerAdapter trailerAdapter;
    private RecyclerView rv,rv_trailers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        supportPostponeEnterTransition();
        Toolbar toolbar = findViewById(R.id.toolbar);

        TextView plot = findViewById(R.id.movie_overview);

         rv = findViewById(R.id.rv_cast);
         rv_trailers = findViewById(R.id.rv_trailers);

        castAdapter = new CastAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        rv.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager_trailers = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        rv_trailers.setLayoutManager(layoutManager_trailers);

        trailerAdapter = new TrailerAdapter(this);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Movie movie = extras.getParcelable(MainActivity.EXTRA_MOVIE_ITEM);
            if(movie!=null) {

                fetchCasts(movie.getMovie_id());

                fetchTrailers(movie.getMovie_id());

                plot.setText(movie.getOverview());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //toolbar.setTitleTextColor(getResources().getColor(R.color.textColor));
                    toolbar.setNavigationIcon(R.drawable.ic_cancel_black_24dp);
                    toolbar.setNavigationOnClickListener(view -> {

                        finishAfterTransition();

                    });
                    toolbar.setTitle(movie.getMovie_title());
                }


                ImageView imageView = findViewById(R.id.imageDetail);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String imageTransitionName = extras.getString(MainActivity.EXTRA_MOVIE_IMAGE_TRANSITION_NAME);
                    imageView.setTransitionName(imageTransitionName);
                }

                String poster_url = movie.getPoster_path();
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("image.tmdb.org")
                        .appendPath("t")
                        .appendPath("p")
                        .appendPath("w780")
                        .appendEncodedPath(poster_url);
                try {

                    URL url = new URL(builder.toString());
                    Picasso.get().load(String.valueOf(url)).noFade().into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError(Exception e) {
                            supportStartPostponedEnterTransition();
                        }
                    });

                } catch (MalformedURLException e) {
                    System.err.println(e.toString() + "");
                }
            }
        }
    }


    void fetchCasts(String id){

        RetrofitClient retrofitClient = new RetrofitClient();
        ApiService service = retrofitClient.createService(ApiService.class);

        Call<CastResponse> call = service.movieCast(id, Constant.getApiKey());
        call.enqueue(new retrofit2.Callback<CastResponse>() {
            @Override
            public void onResponse(@NotNull Call<CastResponse> call, @NotNull Response<CastResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {

                        castAdapter.setCasts(response.body().getGetAllCast());
                        rv.setAdapter(castAdapter);
                    }else
                        Log.d(TAG, "onResponse: Error");
                }else{


                }


            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: No data"+t.getMessage());
            }
        });



    }
    void fetchTrailers(String id){

        RetrofitClient retrofitClient = new RetrofitClient();
        ApiService service = retrofitClient.createService(ApiService.class);

        Call<TrailersResponse> call = service.movietrailers(id,Constant.getApiKey());
        call.enqueue(new retrofit2.Callback<TrailersResponse>() {
            @Override
            public void onResponse(@NotNull Call<TrailersResponse> call, @NotNull Response<TrailersResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {

                        trailerAdapter.setTrailers(response.body().getGetTrailers());
                        rv_trailers.setAdapter(trailerAdapter);
                    }else
                        Log.d(TAG, "onResponse: Error");
                }else{


                }


            }

            @Override
            public void onFailure(Call<TrailersResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: No data"+t.getMessage());
            }
        });



    }

    @Override
    public void clickTrailer(Trailer trailer) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey()));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }


    }
}
