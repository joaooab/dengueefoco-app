package br.com.dengueefocoApp.api;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ViaCepApi {

	@GET("{cep}/json")
	Call<JsonElement> getEndereco(@Path("cep") String cep);

}