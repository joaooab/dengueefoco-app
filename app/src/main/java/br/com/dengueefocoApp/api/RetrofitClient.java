package br.com.dengueefocoApp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String URL = "http://192.168.0.13:8080/";
    private static Retrofit INSTANCE;

    static Retrofit getInstance() {
        if (INSTANCE == null) {
            return createInstance();
        } else {
            return INSTANCE;
        }
    }

    private static Retrofit createInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Api getApi() {
        return getInstance().create(Api.class);
    }

}
