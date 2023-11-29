package com.example.geoexplora;

import android.util.Log;
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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{
    private List<Animales> animalesList;

    public CardAdapter(List<Animales> animalesList) {
        this.animalesList = animalesList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return animalesList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;
        ImageView imagen;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }

    // Dentro de la clase CardAdapter
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Animales animal = animalesList.get(position);
        holder.textTitle.setText(animal.getNombre_Animal());
        holder.textDescription.setText(animal.getDescripcion());

        String imageUrl = "https://api-geo-explora.vercel.app"+animal.getImagen();

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
