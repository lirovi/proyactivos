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

import java.util.List;


public class ReciclerViewAdaptador extends RecyclerView.Adapter<ReciclerViewAdaptador.ViewHolderActivos>{

    Context mContex;
    public List<Activos> listaActivos;
    String edif;
    String func;


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

    public ReciclerViewAdaptador(Context context,List<Activos> listaActivos, String edif, String func){
        this.mContex=context;
        this.listaActivos = listaActivos;
        this.edif=edif;
        this.func=func;
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
       // holder.fotoActivo.setImageResource();

        holder.cardViewActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentActivos = new Intent(mContex, EditaActivosActivity.class);
                intentActivos.putExtra("datosActivo",listaActivos.get(i));
                intentActivos.putExtra("vedificio",edif);
                intentActivos.putExtra("vfuncionario",func);
                mContex.startActivity(intentActivos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaActivos.size();
    }

}
