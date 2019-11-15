package com.example.ale.misactivos.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.ale.misactivos.R;
import com.example.ale.misactivos.Vistas.EditaActivosActivity;
import com.example.ale.misactivos.entidades.Activos;

import java.util.ArrayList;
import java.util.List;


public class ReciclerViewAdaptador extends RecyclerView.Adapter<ReciclerViewAdaptador.ViewHolderActivos>{
                                   // implements View.OnClickListener {
    //ArrayList<Activos> mlista;
    Context mContex;
    public List<Activos> listaActivos;

    //private View.OnClickListener listener;

    public static class ViewHolderActivos extends RecyclerView.ViewHolder{
        private TextView codActivo, DescActivo, fecIng;
        ImageView fotoActivo;
        CardView cardViewActivo;

        public ViewHolderActivos(View itemView){
            super(itemView);
            codActivo = itemView.findViewById(R.id.tvCodigoItem);
            DescActivo = itemView.findViewById(R.id.tvDescripcionItem);
            fecIng =itemView.findViewById(R.id.tvFechaingItem);
            fotoActivo = itemView.findViewById(R.id.imgActivo);
            cardViewActivo =  itemView.findViewById(R.id.idCardviewActivos);
        }

    }


    public ReciclerViewAdaptador(Context context,List<Activos> listaActivos){
        this.mContex=context;
        this.listaActivos = listaActivos;
    }

    @NonNull
    @Override
    public ViewHolderActivos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activos, viewGroup,false );
       // view.setOnClickListener(this);
        ViewHolderActivos viewHolder = new ViewHolderActivos(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReciclerViewAdaptador.ViewHolderActivos holder, final int i) {
        holder.codActivo.setText(listaActivos.get(i).getCodigo());
        holder.DescActivo.setText(listaActivos.get(i).getDESCRIPCION());
        holder.fecIng.setText(listaActivos.get(i).getFECHA_INGRESO());
        holder.fotoActivo.setImageResource(listaActivos.get(i).getIMAGEN());

        holder.cardViewActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentActivos = new Intent(mContex, EditaActivosActivity.class);
                intentActivos.putExtra("datosActivo",listaActivos.get(i));
                //intentActivos.putExtra("imagen",listaActivos.get(i).getIMAGEN());
                //intentActivos.putExtra("desc",listaActivos.get(i).getDESCRIPCION());
                mContex.startActivity(intentActivos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaActivos.size();
    }

   /* public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }*/



}
