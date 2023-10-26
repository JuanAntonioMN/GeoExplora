package com.example.geoexplora;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0:
                return new InicioFragment(); //Fragemento de Inicio
            
            case 1:
                return new perfilFragment();//Fragmento de Perfil
            case 2:
                return new ContenidosFragment(); // Fragmento de contenidos
            case 3:

            return new JuegosFragment(); // Fragemento de Juegos
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        // Devuelve la cantidad de vistas que tienes.
        return 4;
    }
}
