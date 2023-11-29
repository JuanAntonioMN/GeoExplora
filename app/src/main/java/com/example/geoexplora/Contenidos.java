package com.example.geoexplora;

import java.io.Serializable;

public class Contenidos implements Serializable {

    private String Tema;
    private int Imagen;

    private String Contenidos;
    private int bandera;


    public Contenidos(String Tema,int Imagen,String contenidos,int bandera){

        this.Tema=Tema;
        this.Imagen=Imagen;
        this.Contenidos=contenidos;
        this.bandera=bandera;
    }

    public String getTema() {
        return Tema;
    }
    public int getBandera(){
        return bandera;
    }
    public void setBandera(int bandera){
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
    public void setContenidos(String contenidos){
        Contenidos=contenidos;
    }
    public String getContenidos(){
        return Contenidos;
    }

}
