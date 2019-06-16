package com.example.ale.misactivos.Vistas.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ale.misactivos.Adapter.PictureAdapterRecyclerView;
import com.example.ale.misactivos.Model.Picture;
import com.example.ale.misactivos.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home),false,view);
        RecyclerView picturesRecycler= view.findViewById(R.id.pictureRecycler);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView= new PictureAdapterRecyclerView(buildPicture(),R.layout.cardview_picture,getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public ArrayList<Picture> buildPicture(){
        ArrayList<Picture> pictures=new ArrayList<>();
        pictures.add(new Picture("http://www.thewebfoto.com/old/images/paisajes04.jpg","Limber Rojas","5 Dias", "8 Me Gusta"));
        pictures.add(new Picture("http://www.thewebfoto.com/old/images/paisajes05.jpg","Gadiel Rojas","3 Dias", "9 Me Gusta"));
        pictures.add(new Picture("https://www.dzoom.org.es/wp-content/uploads/2017/07/seebensee-2384369-810x540.jpg1","Matias Rojsa","7 Dias", "10 Me Gusta"));
        return pictures;
        //http://www.thewebfoto.com/old/images/paisajes04.jpg
        //http://www.thewebfoto.com/old/images/paisajes05.jpg
        //https://www.dzoom.org.es/wp-content/uploads/2019/02/sobreprocesar-fotografias-paisaje-11-300x200.jpg
        //https://www.dzoom.org.es/wp-content/uploads/2017/07/seebensee-2384369-810x540.jpg
    };
    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar= (Toolbar ) view.findViewById(R.id.toolbar);
        ((AppCompatActivity ) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity ) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity ) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
