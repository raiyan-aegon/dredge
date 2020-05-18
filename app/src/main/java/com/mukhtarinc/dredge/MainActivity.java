package com.mukhtarinc.dredge;

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
import com.mukhtarinc.dredge.ui.DetailActivity;
import com.mukhtarinc.dredge.ui.ImageClickListener;
import com.mukhtarinc.dredge.ui.ViewPagerTabAdapter;
import com.mukhtarinc.dredge.R;

public class MainActivity extends AppCompatActivity implements ImageClickListener {

    private ViewPager2 viewPager;
    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private TabLayoutMediator mediator;
    public static final String EXTRA_MOVIE_ITEM = "animal_image_url";
    public static final String EXTRA_MOVIE_IMAGE_TRANSITION_NAME = "animal_image_transition_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.view_pager);

        tabLayout = findViewById(R.id.tabs);


        init();
    }


    void init(){

        viewPager.setAdapter(new ViewPagerTabAdapter(this ,this));

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

                    case 2:

                        tab.setText("Favorites");
                        break;

                }
            }
        });

        mediator.attach();


    }

    @Override
    public void onImageClick(int position, Movie movie, ImageView sharedImageView) {
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
}