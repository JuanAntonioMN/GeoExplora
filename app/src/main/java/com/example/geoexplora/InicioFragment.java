package com.example.geoexplora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class InicioFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño XML de la vista de contenidos
        View view = inflater.inflate(R.layout.inicio, container, false);
        return view;
    }
}
