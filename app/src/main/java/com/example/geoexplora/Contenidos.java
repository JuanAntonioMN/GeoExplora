package com.example.geoexplora;

import java.io.Serializable;

public class Contenidos implements Serializable {
    private String Tema;
    private int Imagen;
    private boolean bandera;
    public Contenidos(String Tema,int Imagen,boolean bandera){
        this.Tema=Tema;
        this.Imagen=Imagen;
        this.bandera=bandera;
    }

    public String getTema() {
        return Tema;
    }
    public boolean getBandera(){
        return bandera;
    }
    public void setBandera(boolean bandera){
        this.bandera=bandera;
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
