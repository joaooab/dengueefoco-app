package br.com.dengueefocoApp.api;

import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.Usuario;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface Api {

	@Headers("Content-Type: application/json;charset=utf-8")
	@POST("login")
	Call<JsonElement> login(@Body Usuario usuario);

	@Headers("Content-Type: application/json;charset=utf-8")
	@POST("antivetoriais")
	Call<JsonElement> salvarAntivetorial(@Body Antivetorial antivetorial);

	@Headers("Content-Type: application/json;charset=utf-8")
	@POST("antivetoriais/2")
	Call<JsonElement> salvarAntivetorial2(@Body Antivetorial antivetorial);

}