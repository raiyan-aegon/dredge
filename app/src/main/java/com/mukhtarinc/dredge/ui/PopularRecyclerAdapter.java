package com.mukhtarinc.dredge.ui;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mukhtarinc.dredge.R;
import com.mukhtarinc.dredge.model.Movie;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PopularRecyclerAdapter extends RecyclerView.Adapter<PopularRecyclerAdapter.MovieViewHolder> {

    private static final String TAG = "ViewpagerAdapter";

     private List<Movie>movies;

     public PopularImageClickListener imageClickListener;
     private Context context;


     public PopularRecyclerAdapter(PopularImageClickListener imageClickListener){

         this.imageClickListener =imageClickListener;
     }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.popular_card_item,parent,false);

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

                    Picasso.get().load(String.valueOf(url)).noFade().into(holder.roundedImageView);

        } catch (MalformedURLException e) {
            System.err.println(e.toString()+"");
        }


        ViewCompat.setTransitionName(holder.roundedImageView,movies.get(position).getMovie_title());

        holder.roundedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageClickListener.onPopularImageClick(holder.getAdapterPosition(),movies.get(position),holder.roundedImageView);
            }
        });




    }


    @Override
    public int getItemCount() {

        return movies.size();
    }

    public void setData(List<Movie> movies){

        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView roundedImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);

        }
    }
}
