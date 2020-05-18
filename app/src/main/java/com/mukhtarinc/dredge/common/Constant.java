package com.mukhtarinc.dredge.common;

public class Constant {
    private static final String BASE_URL ="https://api.themoviedb.org/3/movie/";
    private static final String API_KEY ="fc350b0d854b5757a33142a3678ba50b";
    private static final String BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342";
    private static final String BASR_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780";
    private static final String YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=%1$s";

    public static String getApiKey() {
        return API_KEY;
    }


    public static String getBackdropPath(String backdropPath) {
        return BASR_BACKDROP_PATH + backdropPath;

    }
    public static String getPosterPath(String posterPath) {
        return BASE_POSTER_PATH + posterPath;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }


}
