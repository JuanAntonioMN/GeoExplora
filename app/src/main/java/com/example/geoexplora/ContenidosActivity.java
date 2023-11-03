package com.example.geoexplora;

import android.app.Activity;
import android.os.Bundle;

public class ContenidosActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temas);

        if(getIntent().getExtras()!=null){
            Contenidos contenidos=(Contenidos)getIntent().getSerializableExtra("tema");

        }

    }
}
