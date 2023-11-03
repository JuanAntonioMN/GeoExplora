package com.example.geoexplora;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContenidosAdapter extends ArrayAdapter<Contenidos> {
    Activity context;
    Contenidos []datos;
    ContenidosAdapter(Activity context,Contenidos []datos){
        super(context,R.layout.contenidos,datos);
        this.context=context;
        this.datos=datos;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.contenidos, parent, false);
            holder = new ViewHolder();
            holder.imagen=convertView.findViewById(R.id.imagen);
            holder.tema = convertView.findViewById(R.id.tema);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contenidos contenido = datos[position];
        holder.tema.setText(contenido.getTema());
        holder.imagen.setImageResource(contenido.getImagen());


        return convertView;
    }

    static class ViewHolder {
        TextView tema;
        ImageView imagen;
    }

}
