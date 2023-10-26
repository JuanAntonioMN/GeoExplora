package com.example.geoexplora;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ContenidosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño XML de la vista de contenidos
        View view = inflater.inflate(R.layout.contenidoslistview, container, false);

        ListView listView=view.findViewById(R.id.contenidosview);
        Contenidos[] temasGeografia =new Contenidos[] {
                new Contenidos("Introducción a la geografía",R.drawable.introduccion),
                new Contenidos("El universo",R.drawable.universo),
                new Contenidos("El sistema solar",R.drawable.sistemasolar),
                new Contenidos( "El planeta tierra",R.drawable.tierra),
                new Contenidos("Estructura de la atmósfera",R.drawable.atmosfera),
                new Contenidos("Elementos del clima",R.drawable.elementos),
                new Contenidos("Los vientos",R.drawable.viento),
                new Contenidos( "Las coordenadas",R.drawable.coordenadas),
                new Contenidos("Estaciones del año",R.drawable.estaciones),
                new Contenidos("El ciclo del agua",R.drawable.ciclo),
                new Contenidos("Los continentes",R.drawable.continentes),
                new Contenidos("Las eras geológicas de la tierra",R.drawable.eras),
                new Contenidos("Mapa geográfico",R.drawable.mapageo),
                new Contenidos("Oceanos y mares",R.drawable.oceanos),
                new Contenidos( "Las montañas y volcanes",R.drawable.montana),
                new Contenidos( "Los ecosistemas",R.drawable.ecosistemas),
                new Contenidos( "Geografía de México",R.drawable.mexico),
                new Contenidos("Los países y capitales",R.drawable.paises),
                new Contenidos("Población y culturas del mundo",R.drawable.poblacion),
                new Contenidos("Recursos naturales",R.drawable.recursosnaturales),
                new Contenidos("Conservación del medio ambiente",R.drawable.consrvacion),
                new Contenidos("Desastres naturales",R.drawable.desastre)
        };
       Context context = inflater.getContext();

        ContenidosAdapter contenidosAdapter= new ContenidosAdapter(this.getActivity(),temasGeografia);
        listView.setAdapter(contenidosAdapter);
        return view;
    }
}
