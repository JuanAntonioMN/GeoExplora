package com.example.geoexplora;

import android.os.Parcel;
import android.os.Parcelable;

public class Banderas implements Parcelable {


    int id;
    String nombre;
    String imagen;
    String capital;
    String idioma;
    String informacion;
    String poblacion;

    protected Banderas(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        imagen = in.readString();
        capital=in.readString();
        idioma=in.readString();
        poblacion=in.readString();
        informacion = in.readString();
    }

    public static final Creator<Banderas> CREATOR = new Creator<Banderas>() {
        @Override
        public Banderas createFromParcel(Parcel in) {
            return new Banderas(in);
        }

        @Override
        public Banderas[] newArray(int size) {
            return new Banderas[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(imagen);
        dest.writeString(informacion);
        dest.writeString(idioma);
        dest.writeString(poblacion);
        dest.writeString(capital);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public void setPoblacion(String poblacion){
        this.poblacion=poblacion;
    }
    public String getPoblacion(){
        return poblacion;
    }
}
