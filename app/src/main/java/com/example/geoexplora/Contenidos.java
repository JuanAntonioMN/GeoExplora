package com.example.geoexplora;

public class Contenidos {
    private String Tema;
    private int Imagen;

    public Contenidos(String Tema,int Imagen){
        this.Tema=Tema;
        this.Imagen=Imagen;
    }

    public String getTema() {
        return Tema;
    }

    public void setTema(String tema) {
        Tema = tema;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}
