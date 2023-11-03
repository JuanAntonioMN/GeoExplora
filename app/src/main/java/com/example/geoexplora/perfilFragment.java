package com.example.geoexplora;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;


public class perfilFragment extends Fragment{
    private ImageView fondoImageView;
    private int[] fondos = {R.drawable.fondo1, R.drawable.fondo2, R.drawable.fondo3, R.drawable.fondo4, R.drawable.fondo5};
    private int fondoIndex = 0;
    private int NUM_MAX = 4;
    private static final String PREFS_KEY = "FondoPreferido";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil, container, false);

        fondoImageView = view.findViewById(R.id.fondoPantalla);

        //Guarda la imagen que  se alla elgido con el boton de cambiar fondo
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        fondoIndex = preferences.getInt("background_index", 0);
        fondoImageView.setImageResource(fondos[fondoIndex]);

        Button btnCambioFondo = view.findViewById(R.id.btnFondo);
        btnCambioFondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fondoIndex = (fondoIndex + 1) % fondos.length;
                if(fondoIndex > NUM_MAX)
                    fondoIndex = 0;
                // Guardar el índice del fondo seleccionado
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("background_index", fondoIndex);
                editor.apply();
                // Cambia el fondo del ImageView al siguiente en el array
                fondoImageView.setImageResource(fondos[fondoIndex]);
            }
        });

        return view;
    }

}





