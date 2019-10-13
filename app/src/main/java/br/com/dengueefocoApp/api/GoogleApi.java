package br.com.dengueefocoApp.api;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApi {

    @GET ("geocode/json")
    Call<JsonElement> reverseGeocode(@Query ("latlng") String latlng, @Query ("key") String key);

}