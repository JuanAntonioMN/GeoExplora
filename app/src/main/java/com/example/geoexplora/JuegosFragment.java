package com.example.geoexplora;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class JuegosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.juegos, container, false);

        // Encuentra las imágenes
        ImageView imagenAhorcado = view.findViewById(R.id.imagenAhorcado);
        ImageView imagenMemorama = view.findViewById(R.id.imagenMemorama);

        // Asigna clics a las imágenes
        imagenAhorcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad del juego del Ahorcado
                Intent intent = new Intent(getActivity(), AhorcadoActivity.class);
                startActivity(intent);
            }
        });

        imagenMemorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad del juego del Memorama
                Intent intent = new Intent(getActivity(), MemoramaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}


