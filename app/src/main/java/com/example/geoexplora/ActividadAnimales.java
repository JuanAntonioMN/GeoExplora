package com.example.geoexplora;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadAnimales extends Activity {
    private RecyclerView recyclerView;
    CardAdapter cardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        obtenerDatosDeApi();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void obtenerDatosDeApi() {
        Call<List<Animales>> call = RetrofitInstance.getApiService().getAnimales();

        call.enqueue(new Callback<List<Animales>>() {
            @Override
            public void onResponse(Call<List<Animales>> call, Response<List<Animales>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Animales> animales = response.body();
                    // Configurar el RecyclerView con los datos obtenidos
                    cardAdapter = new CardAdapter(animales);
                    recyclerView.setAdapter(cardAdapter);
                    for (Animales animal : animales) {
                        Log.d("Animales", "ID: " + animal.getId());
                        Log.d("Animales", "Nombre: " + animal.getNombre_Animal());
                        Log.d("Animales", "Imagen URL: " + animal.getImagen());
                        Log.d("Animales", "Descripción: " + animal.getDescripcion());
                    }
                } else {
                    // Manejar errores
                    Log.e("Animales", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Animales>> call, Throwable t) {
                // Manejar errores de conexión
                Log.e("Animales", "Error en la llamada: " + t.getMessage());
            }
        });

    }

}
