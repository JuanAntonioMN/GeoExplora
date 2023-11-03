package com.example.geoexplora;

public class Informacion {
    private int Id;
    private String informacion;
    private String imagen;

    Informacion(int id,String informacion,String imagen){
        this.Id=id;
        this.informacion=informacion;
        this.imagen=imagen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
