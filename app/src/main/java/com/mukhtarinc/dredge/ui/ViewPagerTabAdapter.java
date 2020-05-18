package com.mukhtarinc.dredge.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerTabAdapter extends FragmentStateAdapter {


    private ImageClickListener imageClickListener;

        public ViewPagerTabAdapter(@NonNull FragmentActivity fragmentActivity, ImageClickListener imageClickListener) {
            super(fragmentActivity);
            this.imageClickListener = imageClickListener;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new PopularFragment(imageClickListener);
                case 1:
                   return new TopRatedFragment();

                case 2:
                    return new FavoriteFragment();

            }
            return new PopularFragment(imageClickListener);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
