package com.example.ale.misactivos.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ale.misactivos.Model.Picture;
import com.example.ale.misactivos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.pictureViewHolder>{

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public pictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new pictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pictureViewHolder holder, int position) {
        Picture picture= pictures.get(position);
        holder.usernameCard.setText(picture.getUsername());
        holder.likeNumberCard.setText(picture.getLikeNumber());
        holder.timeCard.setText(picture.getTime());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

    }

    @Override
    public int getItemCount() {

        return pictures.size();
    }

    public class pictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;

        public pictureViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureCard =(ImageView) itemView.findViewById(R.id.imagenCardView);
            usernameCard =(TextView) itemView.findViewById(R.id.userNameCardView);
            timeCard =(TextView) itemView.findViewById(R.id.timeCardView);
            likeNumberCard =(TextView) itemView.findViewById(R.id.likeNumberCardView);

        }
    }
}
