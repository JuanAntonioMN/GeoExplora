package com.example.geoexplora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ContenidosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño XML de la vista de contenidos
        View view = inflater.inflate(R.layout.contenidos, container, false);

        ListView listView=view.findViewById(R.id.contentListView);
        String[] temasGeografia = {
                "Introducción a la geografía",
                "Historia de la geografía",
                "El universo",
                "El sistema solar",
                "El planeta tierra",
                "Estructura de la atmósfera",
                "Elementos del clima",
                "Los vientos",
                "Las coordenadas",
                "Estaciones del año",
                "El ciclo del agua",
                "Los continentes",
                "Las eras geológicas de la tierra",
                "Mapa geográfico",
                "Oceanos y mares",
                "Las montañas y volcanes",
                "Los ecosistemas",
                "Geografía de México",
                "Los países y capitales",
                "Población y culturas del mundo",
                "Recursos naturales",
                "Conservación del medio ambiente",
                "Desastres naturales"
        };

        ArrayAdapter<String> temasAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,temasGeografia);
        listView.setAdapter(temasAdapter);
        return view;
    }
}
