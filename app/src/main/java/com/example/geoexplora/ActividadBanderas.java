package com.example.geoexplora;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadBanderas extends Activity {

    private RecyclerView recyclerView;
    BanderasAdapter banderasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banderas);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void obtenerDatosDeApi() {
        Call<List<Banderas>> call = RetrofitInstance.getApiService().getBanderas();

        call.enqueue(new Callback<List<Banderas>>() {
            @Override
            public void onResponse(Call<List<Banderas>> call, Response<List<Banderas>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Banderas> banderas = response.body();
                    // Configurar el RecyclerView con los datos obtenidos
                    banderasAdapter = new BanderasAdapter(banderas);

                    recyclerView.setAdapter(banderasAdapter);
                    for (Banderas bandera : banderas) {
                        Log.d("Banderas", "ID: " + bandera.getId());
                        Log.d("Banderas", "Nombre: " + bandera.getNombre());
                        Log.d("Banderas", "Imagen URL: " + bandera.getImagen());
                        Log.d("Banderas","Capital: "+bandera.getCapital());
                        Log.d("Banderas","Poblacion: "+bandera.getPoblacion());
                        Log.d("Banderas","Idioma: "+bandera.getIdioma());
                        Log.d("Banderas", "Descripción: " + bandera.getInformacion());
                    }
                } else {
                    // Manejar errores
                    Log.e("Banderas", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Banderas>> call, Throwable t) {
                // Manejar errores de conexión
                Log.e("Banderas", "Error en la llamada: " + t.getMessage());
            }
        });

    }
}
