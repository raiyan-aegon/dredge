package com.mukhtarinc.dredge.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService  {


    @GET("popular")
    Call<PopularMoviesResponse> popularMovies(@Query("api_key") String api_key);

    @GET("top_rated")
    Call<TopRatedMoviesResponse> topratedMovies(@Query("api_key") String api_key);

    @GET("{movie_id}/credits")
    Call<CastResponse> movieCast(@Path("movie_id") String movie_id,@Query("api_key") String api_key);

}
