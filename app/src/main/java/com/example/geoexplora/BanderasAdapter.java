package com.example.geoexplora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BanderasAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{
    private List<Banderas> banderasList;

    public BanderasAdapter(List<Banderas> banderasList) {
        this.banderasList = banderasList;
    }

    @NonNull
    @Override
    public CardAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardbanderas, parent, false);
        return new CardAdapter.CardViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return banderasList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView capital;
        TextView poblacion;
        TextView idioma;
        TextView textDescription;
        ImageView imagen;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.nombre);
            textDescription = itemView.findViewById(R.id.informacion);
            imagen = itemView.findViewById(R.id.imagen);
            capital=itemView.findViewById(R.id.capital);
            poblacion=itemView.findViewById(R.id.poblacion);
            idioma=itemView.findViewById(R.id.idioma);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull CardAdapter.CardViewHolder holder, int position) {
        Banderas bandera = banderasList.get(position);
        holder.textTitle.setText(bandera.getNombre());
        holder.textDescription.setText(bandera.getInformacion());

        String imageUrl = "https://api-geo-explora.vercel.app"+bandera.getImagen();

        // Cargar la imagen desde la URL utilizando Glide
        Glide.with(holder.itemView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imagen);

        // Aplicar la animación
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    // Dentro de la clase CardAdapter
    private int lastPosition = -1;

}
