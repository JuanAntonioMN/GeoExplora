package com.example.geoexplora;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemoramaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorama);

        TextView textView = findViewById(R.id.textViewMensaje);
        textView.setText("Hola Mundo - Memorama");
    }
}