package com.mukhtarinc.dredge.ui;

import android.widget.ImageView;

import com.mukhtarinc.dredge.model.Movie;
import com.mukhtarinc.dredge.model.TopRatedMovie;

public interface TopRatedImageClickListener {



        void onTopRatedImageClick(int position, TopRatedMovie movie, ImageView sharedImageView);


}
