package com.mukhtarinc.dredge.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerTabAdapter extends FragmentStateAdapter {


    private PopularImageClickListener popularImageClickListener;
    private TopRatedImageClickListener topRatedImageClickListener;

        public ViewPagerTabAdapter(@NonNull FragmentActivity fragmentActivity, PopularImageClickListener popularImageClickListener,TopRatedImageClickListener topRatedImageClickListener) {
            super(fragmentActivity);
            this.popularImageClickListener = popularImageClickListener;
            this.topRatedImageClickListener = topRatedImageClickListener;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new PopularFragment(popularImageClickListener);
                case 1:
                   return new TopRatedFragment(topRatedImageClickListener);

            }
            return new PopularFragment(popularImageClickListener);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
