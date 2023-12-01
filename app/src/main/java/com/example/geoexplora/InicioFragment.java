package com.example.geoexplora;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.geoexplora.CardAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class InicioFragment extends Fragment {


    CardView card1;
    CardView card2;
    CardView card3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Verificar si hay conexión a Internet
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            // Si hay conexión, inflar el diseño de la vista con RecyclerView

            View view = inflater.inflate(R.layout.api, container, false);
             card1 = view.findViewById(R.id.animales);
             card2 = view.findViewById(R.id.banderas); // Asegúrate de tener el ID correcto
             card3 = view.findViewById(R.id.vegetacion); // Asegúrate de tener el ID correcto

            card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAnimales = new Intent(getActivity(), ActividadAnimales.class);
                    startActivity(intentAnimales);

                }
            });
            card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentPaises = new Intent(getActivity(), ActividadBanderas.class);
                    startActivity(intentPaises);


                }
            });

            card3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción para la tercera CardView
                    // Puedes llamar a un método o iniciar una actividad aquí

                }
            });
            /*obtenerDatosDeApi();
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));*/



            return view;
        } else {
            // Si no hay conexión, inflar el diseño de la vista sin RecyclerView
            return inflater.inflate(R.layout.principal, container, false);
        }
    }




}
