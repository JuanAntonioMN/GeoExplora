package com.example.geoexplora;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.geoexplora.CardAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class InicioFragment extends Fragment {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Verificar si hay conexión a Internet
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            // Si hay conexión, inflar el diseño de la vista con RecyclerView
            View view = inflater.inflate(R.layout.inicio, container, false);
            obtenerDatosDeApi();
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



            return view;
        } else {
            // Si no hay conexión, inflar el diseño de la vista sin RecyclerView
            return inflater.inflate(R.layout.principal, container, false);
        }
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
