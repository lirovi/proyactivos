package com.example.ale.misactivos.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ale.misactivos.Model.MenuPicture;
import com.example.ale.misactivos.Model.Picture;
import com.example.ale.misactivos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuPictureAdapterRecyclerView extends RecyclerView.Adapter<MenuPictureAdapterRecyclerView.pictureViewHolder>{

    private ArrayList<MenuPicture> menupictures;
    private int resource;
    private Activity activity;

    public MenuPictureAdapterRecyclerView(ArrayList<MenuPicture> pictures, int resource, Activity activity) {
        this.menupictures = pictures;
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
        MenuPicture picture= menupictures.get(position);
        //holder.usernameCard.setText(picture.getUsername());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

    }

    @Override
    public int getItemCount() {

        return menupictures.size();
    }

    public class pictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
       // private TextView usernameCard;

        public pictureViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureCard =(ImageView) itemView.findViewById(R.id.imagenCardViewButton);
           // usernameCard =(TextView) itemView.findViewById(R.id.tvCardViewButton);

        }
    }
}
