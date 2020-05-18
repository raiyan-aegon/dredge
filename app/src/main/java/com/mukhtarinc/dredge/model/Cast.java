package com.mukhtarinc.dredge.model;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String cast_image;


    public String getName() {
        return name;
    }

    public String getCast_image() {
        return cast_image;
    }
}
