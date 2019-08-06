package br.com.dengueefocoApp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String URL = "https://api.github.com/";
    private static String GOOGLE_URL = "https://maps.googleapis.com/maps/api/";
    private Retrofit INSTANCE;
    private Retrofit GOOGLE_INSTANSE;

    Retrofit getInstance() {
        if (INSTANCE == null) {
            return createInstance();
        } else {
            return INSTANCE;
        }
    }

    Retrofit getGoogleInstance() {
        if (GOOGLE_INSTANSE == null) {
            return createGoogleInstance();
        } else {
            return GOOGLE_INSTANSE;
        }
    }

    private Retrofit createInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .build();
    }

    private Retrofit createGoogleInstance() {
        return new Retrofit.Builder()
                .baseUrl(GOOGLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Api getApi() {
        return INSTANCE.create(Api.class);
    }

    public GoogleApi getGoogleApi() { return getGoogleInstance().create(GoogleApi.class); }
}
