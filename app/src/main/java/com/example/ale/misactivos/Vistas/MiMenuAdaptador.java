package com.example.ale.misactivos.Vistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ale.misactivos.R;

import java.util.List;

class MiMenuAdaptador extends BaseAdapter {

    private Context context;

    private int layout;
    private List<Integer> iconosCrud;

    public MiMenuAdaptador(Context context, int layout, List<Integer> iconosCrud){
        this.context=context;
        this.layout=layout;
        this.iconosCrud=iconosCrud;

    }

    @Override
    public int getCount() {
        return this.iconosCrud.size();
    }

    @Override
    public Object getItem(int position) {
        return iconosCrud.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        //View Holder

        ViewHolder holder;
        if(convertview==null){

            //inflamos nuestro layout
            LayoutInflater layoutInflater= LayoutInflater.from(this.context);
            convertview = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            //asociamos y rellenamos el textview con los nombres
            holder.imagenCardViewButton = convertview.findViewById(R.id.imagenCardViewButton);
            convertview.setTag(holder);
        }else{
            holder = ( ViewHolder ) convertview.getTag();
        }

        //obtenemos la referencia del dato
        Integer nombre = iconosCrud.get(position);

        holder.imagenCardViewButton.setImageResource(nombre);

        //devolvemos la vista inflada con los datos
        return convertview;

    }
    public class ViewHolder{
        private ImageView imagenCardViewButton;

    }

}


