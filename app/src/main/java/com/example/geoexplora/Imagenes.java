package com.example.geoexplora;

import java.io.Serializable;

public class Imagenes implements Serializable {
    int imagenprincipal;
    int imagensecundaria;
    int imagenData1;
    int imagenData2;
    int imagenData3;

    Imagenes(int img1,int img2,int img3,int imag4,int img5){
        this.imagenprincipal=img1;
        this.imagensecundaria=img2;
        this.imagenData1=img3;
        this.imagenData2=imag4;
        this.imagenData3=img5;
    }

    public int getImagenprincipal() {
        return imagenprincipal;
    }

    public void setImagenprincipal(int imagenprincipal) {
        this.imagenprincipal = imagenprincipal;
    }

    public int getImagensecundaria() {
        return imagensecundaria;
    }

    public void setImagensecundaria(int imagensecundaria) {
        this.imagensecundaria = imagensecundaria;
    }

    public int getImagenData1() {
        return imagenData1;
    }

    public void setImagenData1(int imagenData1) {
        this.imagenData1 = imagenData1;
    }

    public int getImagenData2() {
        return imagenData2;
    }

    public void setImagenData2(int imagenData2) {
        this.imagenData2 = imagenData2;
    }

    public int getImagenData3() {
        return imagenData3;
    }

    public void setImagenData3(int imagenData3) {
        this.imagenData3 = imagenData3;
    }
}
