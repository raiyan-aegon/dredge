package com.mukhtarinc.dredge.ui;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mukhtarinc.movieapp.R;
import com.mukhtarinc.dredge.model.Cast;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private List<Cast> casts;

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item,parent,false);


        return new CastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {

        String poster_url = casts.get(position).getCast_image();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w780")
                .appendEncodedPath(poster_url);
        try {

            URL url = new URL(builder.toString());
            Picasso.get().load(String.valueOf(url)).noFade().into(holder.castImg);

        } catch (MalformedURLException e) {
            System.err.println(e.toString() + "");
        }
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    void setCasts(List<Cast> data){

        casts = data;
        notifyDataSetChanged();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView castImg;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);

            castImg = itemView.findViewById(R.id.castImg);


        }
    }
}
