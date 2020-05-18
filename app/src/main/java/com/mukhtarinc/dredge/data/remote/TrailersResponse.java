package com.mukhtarinc.dredge.data.remote;

import com.google.gson.annotations.SerializedName;
import com.mukhtarinc.dredge.model.Trailer;

import java.util.List;

public class TrailersResponse {

    @SerializedName("results")
    private List<Trailer> getTrailers;

    public List<Trailer> getGetTrailers() {
        return getTrailers;
    }
}
