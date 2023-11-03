package com.example.geoexplora;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.bumptech.glide.Glide; // Agrega la importación de Glide


public class InicioFragment extends Fragment {
    private ImageView imageView;
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño XML de la vista de contenidos
        View view = inflater.inflate(R.layout.inicio, container, false);
        //imageView = view.findViewById(R.id.imagen); // Supongamos que tienes una ImageView con ID "imagen"

        // Busca el TextView en tu diseño XML
       // textView = view.findViewById(R.id.informacion);

        // Realiza la solicitud a la API y muestra los datos en el TextView
        //obtenerObservaciones();
        return view;
    }
    private void obtenerObservaciones() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.inaturalist.org/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api apiService = retrofit.create(api.class);

        Call<Informacion> call = apiService.obtenerObservacion(10000);

        call.enqueue(new Callback<Informacion>() {
            @Override
            public void onResponse(Call<Informacion> call, Response<Informacion> response) {
                if (response.isSuccessful()) {
                    Informacion observation = response.body();
                    // Procesa la observación y muestra los datos en el TextView
                    mostrarObservacionesEnTextView(observation);
                    // Carga y muestra la imagen
                    cargarImagen(observation);
                    Log.d("API Response", "Response Body: " + response.body());

                } else {
                    // Maneja errores
                    Log.e("API Error", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Informacion> call, Throwable t) {
                // Maneja errores de red
                Log.e("API Error", "Error de red: " + t.getMessage());
            }
        });



    }
    private void mostrarObservacionesEnTextView(Informacion observations) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID: ").append(observations.getId()).append("\n");
        stringBuilder.append("Nombre: ").append(observations.getInformacion()).append("\n");
        // Agrega más datos según la estructura de Observacion
        stringBuilder.append("\n");

        // Muestra los datos en el TextView
        textView.setText(stringBuilder.toString());
    }
    private void cargarImagen(Informacion observation) {
        if (observation != null) {
            // Obtén la URL de la imagen de la observación
            String imageUrl = observation.getImagen();

            // Carga y muestra la imagen utilizando Glide en la ImageView
            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);
        }
    }


}
