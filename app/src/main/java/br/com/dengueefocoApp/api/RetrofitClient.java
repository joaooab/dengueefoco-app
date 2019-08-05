package br.com.dengueefocoApp.api;

import retrofit2.Retrofit;

public class RetrofitClient {

    private static String URL = "https://api.github.com/";
    private Retrofit INSTANCE;

    Retrofit getInstance() {
        if (INSTANCE == null) {
            return createInstance();
        } else {
            return INSTANCE;
        }
    }

    private Retrofit createInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .build();
    }

    private Api getApi() {
        return INSTANCE.create(Api.class);
    }

}
