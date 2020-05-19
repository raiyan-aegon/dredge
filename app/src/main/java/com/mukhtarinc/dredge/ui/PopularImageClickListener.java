package com.mukhtarinc.dredge.ui;

import android.widget.ImageView;

import com.mukhtarinc.dredge.model.Movie;

public interface PopularImageClickListener {



        void onPopularImageClick(int position, Movie movie, ImageView sharedImageView);


}
