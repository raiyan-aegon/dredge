package com.mukhtarinc.dredge.ui;

import android.widget.ImageView;

import com.mukhtarinc.dredge.model.Movie;

public interface ImageClickListener {



        void onImageClick(int position, Movie movie, ImageView sharedImageView);


}
