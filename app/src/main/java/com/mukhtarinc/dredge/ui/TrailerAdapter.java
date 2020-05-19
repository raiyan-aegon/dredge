package com.mukhtarinc.dredge.ui;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mukhtarinc.dredge.R;
import com.mukhtarinc.dredge.model.Cast;
import com.mukhtarinc.dredge.model.Trailer;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.CastViewHolder> {

    private List<Trailer> trailers;
    private TrailerClickListener mListener;

    public TrailerAdapter(TrailerClickListener mListener){

        this.mListener =mListener;
    }


    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item,parent,false);


        return new CastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {


        holder.name.setText(trailers.get(position).getName());

    }

    public interface TrailerClickListener{

        void clickTrailer(Trailer trailer);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    void setTrailers(List<Trailer> data){

        trailers = data;
        notifyDataSetChanged();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       private ImageView play;
       private TextView name;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            mListener.clickTrailer(trailers.get(getAdapterPosition()));

        }
    }
}
