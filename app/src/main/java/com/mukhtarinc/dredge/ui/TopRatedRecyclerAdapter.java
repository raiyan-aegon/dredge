package com.mukhtarinc.dredge.ui;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukhtarinc.movieapp.R;
import com.mukhtarinc.dredge.model.TopRatedMovie;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class TopRatedRecyclerAdapter extends RecyclerView.Adapter<TopRatedRecyclerAdapter.MovieViewHolder> {

    private static final String TAG = "ViewpagerAdapter";

     private List<TopRatedMovie>movies;


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.top_rated_card_item,parent,false);

        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String poster_url =movies.get(position).getPoster_path();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w780")
                .appendEncodedPath(poster_url);
        try {

            URL url = new URL(builder.toString());
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Picasso.get().load(String.valueOf(url)).into(holder.roundedImageView);
                }
            });

        } catch (MalformedURLException e) {
            System.err.println(e.toString()+"");
        }


    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    public void setData(List<TopRatedMovie> movies){

        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView roundedImageView;
        private TextView title,year;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.top_rated_roundedImageView);
          //  title = itemView.findViewById(R.id.movie_title);
          //  year = itemView.findViewById(R.id.year);


        }
    }
}
