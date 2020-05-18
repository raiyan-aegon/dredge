package com.mukhtarinc.dredge.data.remote;

import com.google.gson.annotations.SerializedName;
import com.mukhtarinc.dredge.model.Cast;

import java.util.List;

public class CastResponse {


    @SerializedName("cast")
    private List<Cast>getAllCast;


    public List<Cast>getGetAllCast() {
        return getAllCast;
    }
}
