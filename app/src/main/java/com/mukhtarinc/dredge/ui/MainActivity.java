package com.mukhtarinc.dredge.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mukhtarinc.dredge.model.Movie;
import com.mukhtarinc.dredge.model.TopRatedMovie;
import com.mukhtarinc.dredge.R;

public class MainActivity extends AppCompatActivity implements PopularImageClickListener,TopRatedImageClickListener {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    public static final String EXTRA_MOVIE_ITEM = "movie_image_url";
    public static final String EXTRA_TOP_MOVIE_ITEM = "top_movie_image_url";
    public static final String EXTRA_MOVIE_IMAGE_TRANSITION_NAME = "movie_image_transition_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);

        tabLayout = findViewById(R.id.tabs);


        init();
    }


    void init(){

        viewPager.setAdapter(new ViewPagerTabAdapter(this ,this,this));

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){

                    case 0:

                        tab.setText("Popular");
                        break;
                    case 1:

                        tab.setText("Top Rated");
                        break;

                }
            }
        });

        mediator.attach();


    }

    @Override
    public void onPopularImageClick(int position, Movie movie, ImageView sharedImageView) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE_ITEM, movie);
        intent.putExtra(EXTRA_MOVIE_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options.toBundle());
        }
    }

    @Override
    public void onTopRatedImageClick(int position, TopRatedMovie movie, ImageView sharedImageView) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_TOP_MOVIE_ITEM, movie);
        intent.putExtra(EXTRA_MOVIE_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options.toBundle());
        }
    }
}
