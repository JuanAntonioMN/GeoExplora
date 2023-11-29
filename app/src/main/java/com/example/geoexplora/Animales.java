package com.example.geoexplora;

import android.os.Parcel;
import android.os.Parcelable;

public class Animales implements Parcelable {

    int id;
    String nombre;
    String imagen;
    String descripcion;


    protected Animales(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        imagen = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<Animales> CREATOR = new Creator<Animales>() {
        @Override
        public Animales createFromParcel(Parcel in) {
            return new Animales(in);
        }

        @Override
        public Animales[] newArray(int size) {
            return new Animales[size];
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
        dest.writeString(descripcion);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_Animal() {
        return nombre;
    }

    public void setNombre_Animal(String nombre_Animal) {
        this.nombre = nombre_Animal;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
