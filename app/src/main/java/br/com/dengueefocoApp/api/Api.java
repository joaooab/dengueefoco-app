package br.com.dengueefocoApp.api;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET ("users/list")
    public JsonElement getAntivetorials();

}
