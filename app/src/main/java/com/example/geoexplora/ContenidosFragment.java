package com.example.geoexplora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ContenidosFragment extends Fragment {
    ListView listView;
    Contenidos[] temasGeografia;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño XML de la vista de contenidos
        View view = inflater.inflate(R.layout.contenidoslistview, container, false);

        listView=view.findViewById(R.id.listview);
        temasGeografia =new Contenidos[] {
                new Contenidos("Introducción a la geografía",R.drawable.introduccion,true),
                new Contenidos("El universo",R.drawable.universo,false),
                new Contenidos("El sistema solar",R.drawable.sistemasolar,false),
                new Contenidos( "El planeta tierra",R.drawable.tierra,false),
                new Contenidos("Estructura de la atmósfera",R.drawable.atmosfera,false),
                new Contenidos("Elementos del clima",R.drawable.elementos,false),
                new Contenidos("Los vientos",R.drawable.viento,false),
                new Contenidos( "Las coordenadas",R.drawable.coordenadas,false),
                new Contenidos("Estaciones del año",R.drawable.estaciones,false),
                new Contenidos("El ciclo del agua",R.drawable.ciclo,false),
                new Contenidos("Los continentes",R.drawable.continentes,false),
                new Contenidos("Las eras geológicas de la tierra",R.drawable.eras,false),
                new Contenidos("Mapa geográfico",R.drawable.mapageo,false),
                new Contenidos("Oceanos y mares",R.drawable.oceanos,false),
                new Contenidos( "Las montañas y volcanes",R.drawable.montana,false),
                new Contenidos( "Los ecosistemas",R.drawable.ecosistemas,false),
                new Contenidos( "Geografía de México",R.drawable.mexico,false),
                new Contenidos("Los países y capitales",R.drawable.paises,false),
                new Contenidos("Población y culturas del mundo",R.drawable.poblacion,false),
                new Contenidos("Recursos naturales",R.drawable.recursosnaturales,false),
                new Contenidos("Conservación del medio ambiente",R.drawable.consrvacion,false),
                new Contenidos("Desastres naturales",R.drawable.desastre,false)
        };
       Context context = inflater.getContext();

        ContenidosAdapter contenidosAdapter= new ContenidosAdapter(this.getActivity(),temasGeografia);
        listView.setAdapter(contenidosAdapter);
        listView.setOnItemClickListener(evento);
        return view;
    }

    private AdapterView.OnItemClickListener evento=(adapterview,view,i,l)->{


        Contenidos contenidos=(Contenidos) adapterview.getItemAtPosition(i);
        if(temasGeografia[i].getBandera()==true){
            Intent intent=new Intent(getActivity(),ContenidosActivity.class);
            startActivity(intent);
        }
        else{
            // Crear un Toast personalizado
            Toast customToast = Toast.makeText(this.getActivity(), "Aun te falta concluir el tema: " + temasGeografia[i - 1].getTema(), Toast.LENGTH_SHORT);

            // Establecer el fondo personalizado
            View toastView = customToast.getView();
            toastView.setBackgroundResource(R.drawable.custom_toast_background); // El nombre del archivo XML

        // Personalizar el texto (opcional)
            TextView toastText = toastView.findViewById(android.R.id.message);
            toastText.setTextSize(5);
            toastText.setTextColor(Color.WHITE); // Cambiar el color del texto si es necesario

        // Mostrar el Toast personalizado
            customToast.show();

        }

    };
}


