package com.example.geoexplora;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class JuegosFragment extends Fragment implements View.OnClickListener {

    private static final String TAG_IMAGEN_AHORCADO = "imagenAhorcado";
    private static final String TAG_IMAGEN_MEMORAMA = "imagenMemorama";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.juegos, container, false);

        // Obtén referencias a tus ImageViews
        ImageView imagenAhorcado = view.findViewById(R.id.imagenAhorcado);
        ImageView imagenMemorama = view.findViewById(R.id.imagenMemorama);

        // Configura el OnClickListener para cada ImageView
        imagenAhorcado.setTag(TAG_IMAGEN_AHORCADO);
        imagenMemorama.setTag(TAG_IMAGEN_MEMORAMA);

        imagenAhorcado.setOnClickListener(this);
        imagenMemorama.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        // Maneja el evento de clic según la etiqueta (Tag) asignada a la vista (ImageView) seleccionada
        String tag = (String) v.getTag();
        if (tag != null) {
            switch (tag) {
                case TAG_IMAGEN_AHORCADO:
                    // Inicia la actividad AhorcadoActivity
                    startActivity(new Intent(getActivity(), AhorcadoActivity.class));
                    break;

                case TAG_IMAGEN_MEMORAMA:
                    // Inicia la actividad MemoramaActivity
                    startActivity(new Intent(getActivity(), MemoramaActivity.class));
                    break;
            }
        }
    }
}




