package com.example.geoexplora;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiAnimales {
    @GET("/")
    Call<List<Animales>> getAnimales();
}
