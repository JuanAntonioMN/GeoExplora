package com.example.geoexplora;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface api {
    @GET("observations/{observation_id}")
    Call<Informacion> obtenerObservacion(@Path("observation_id") int observationId);

}
