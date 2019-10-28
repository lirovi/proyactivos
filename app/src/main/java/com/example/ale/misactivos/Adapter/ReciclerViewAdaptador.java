package com.example.ale.misactivos.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Activos;

import java.util.ArrayList;
import java.util.List;

public class ReciclerViewAdaptador extends RecyclerView.Adapter<ReciclerViewAdaptador.ViewHolder> {
    ArrayList<Activos> mlista;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView codActivo, DescActivo, fecIng;
        ImageView fotoActivo;

        public ViewHolder(View itemView){
            super(itemView);
            codActivo = itemView.findViewById(R.id.tvCodigoItem);
            DescActivo = itemView.findViewById(R.id.tvDescripcionItem);
            fecIng =itemView.findViewById(R.id.tvFechaingItem);
            fotoActivo = itemView.findViewById(R.id.imgActivo);
        }

    }
    public List<Activos> listaActivos;

    public ReciclerViewAdaptador(List<Activos> listaActivos){
        this.listaActivos = listaActivos;
    }

    @NonNull
    @Override
    public ReciclerViewAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activos, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReciclerViewAdaptador.ViewHolder holder, int i) {
        holder.codActivo.setText(listaActivos.get(i).getCodigo());
        holder.DescActivo.setText(listaActivos.get(i).getDESCRIPCION());
        holder.fecIng.setText(listaActivos.get(i).getFECHA_INGRESO());
        holder.fotoActivo.setImageResource(listaActivos.get(i).getIMAGEN());
    }

    @Override
    public int getItemCount() {
        return listaActivos.size();
    }



}
