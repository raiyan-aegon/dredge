package com.mukhtarinc.dredge.data.remote;

import com.google.gson.annotations.SerializedName;
import com.mukhtarinc.dredge.model.Movie;

import java.util.List;

public class PopularMoviesResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int totalPages;


    @SerializedName("results")
    private List<Movie> data;


    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getData() {
        return data;
    }

}
