package com.example.geoexplora;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ContenidosActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temas);

        if(getIntent().getExtras()!=null){
            Contenidos contenidos=(Contenidos)getIntent().getSerializableExtra("tema");
            if(contenidos!=null){
                TextView tema=findViewById(R.id.tema);
                tema.setText(contenidos.getTema());
            }

        }

    }
}
