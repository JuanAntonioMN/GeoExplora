package com.example.geoexplora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

public class ContenidosFragment extends Fragment {
    ListView listView;
    List<Contenidos> temasGeografia;
    List<Imagenes> imagenes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contenidoslistview, container, false);
        OperacionesContenidos contenidos=new OperacionesContenidos(getContext());
        OperacionesImagenes imagen=new OperacionesImagenes(getContext());
        contenidos.abrir();
        imagen.abrir();
        listView=view.findViewById(R.id.listview);
        temasGeografia=contenidos.obtenerTodosLosContenidos();
        imagenes=imagen.obtenerTodasLasImagenes();
        ContenidosAdapter contenidosAdapter= new ContenidosAdapter(this.getActivity(),temasGeografia);
        listView.setAdapter(contenidosAdapter);
        listView.setOnItemClickListener(evento);
        contenidos.cerrar();
        imagen.cerrar();
        return view;

    }

    private AdapterView.OnItemClickListener evento=(adapterview,view,i,l)->{


        Contenidos contenidos=(Contenidos) adapterview.getItemAtPosition(i);
        if(temasGeografia.get(i).getBandera()==1){
            Intent intent=new Intent(getActivity(),ContenidosActivity.class);
            intent.putExtra("tema", temasGeografia.get(i));
            intent.putExtra("imagenes",imagenes.get(i));
            startActivity(intent);
        }
        else{
            // Crear un Toast personalizado
            Toast customToast = Toast.makeText(this.getActivity(), "", Toast.LENGTH_SHORT);

        // Obtener el View del Toast
            View toastView = customToast.getView();

        // Inflar el diseño personalizado del Toast
            LayoutInflater inflater = getLayoutInflater();
            View customToastView = inflater.inflate(R.layout.toast_personalizado, null);

        // Obtener la TextView (contenido) y la ImageView (imagen)
            TextView textView = customToastView.findViewById(R.id.textToast);
            ImageView imageView = customToastView.findViewById(R.id.imagenToast);

        // Personalizar el texto y la imagen aquí si es necesario
            textView.setText("Aún te falta concluir el tema: " + temasGeografia.get(i-1).getTema());
            imageView.setImageResource(temasGeografia.get(i-1).getImagen());
            textView.setTextColor(Color.WHITE);

         // Aplicar el fondo personalizado al contenido del Toast
            customToastView.setBackgroundResource(R.drawable.gradiente_card); // El nombre del archivo XML

        // Establecer la vista personalizada en el Toast
            customToast.setView(customToastView);
            customToast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 50);

        // Mostrar el Toast personalizado
            customToast.show();
        }

    };
}


