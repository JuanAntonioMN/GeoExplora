package com.example.geoexplora;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContenidosActivity extends Activity {
    TextView Titulo,parrafo1,parrafo2,parrafo3,parrafo4,parrafo5;
    ImageView Imagen1,Imagen2,Imagen3,Imagen4,Imagen5;
    Contenidos contenidos;
    Imagenes imagenes;
    String informacion;
    String []parrafos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temas);

        if(getIntent().getExtras()!=null){
             contenidos=(Contenidos)getIntent().getSerializableExtra("tema");
            imagenes=(Imagenes) getIntent().getSerializableExtra("imagenes");
            if(contenidos!=null ){
                informacion= contenidos.getContenidos();
                parrafos=informacion.split("\n");
                Titulo=findViewById(R.id.Titulo);
                parrafo1 = findViewById(R.id.parrafo1);
                parrafo2 = findViewById(R.id.parrafo2);
                parrafo3 = findViewById(R.id.parrafo3);
                parrafo4 = findViewById(R.id.parrafo4);
                parrafo5 = findViewById(R.id.parrafo5);

                Titulo.setText(contenidos.getTema());
                parrafo1.setText(parrafos[0]);
                parrafo2.setText(parrafos[1]);
                parrafo3.setText(parrafos[2]);
                parrafo4.setText(parrafos[3]);
                parrafo5.setText(parrafos[4]);

                Imagen1=findViewById(R.id.imagen1);
                Imagen1.setImageResource(imagenes.getImagenprincipal());

                Imagen2=findViewById(R.id.imagen2);
                Imagen2.setImageResource(imagenes.getImagensecundaria());

                Imagen3=findViewById(R.id.imagen3);
                Imagen3.setImageResource(imagenes.getImagenData1());

                Imagen4=findViewById(R.id.imagen4);
                Imagen4.setImageResource(imagenes.getImagenData2());

                Imagen5=findViewById(R.id.imagen5);
                Imagen5.setImageResource(imagenes.getImagenData3());




            }
            else{

            }

        }

    }
}
