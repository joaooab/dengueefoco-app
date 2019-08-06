package br.com.dengueefocoApp.api;

import retrofit2.Retrofit;

public class RetrofitClient {

    private static String URL = "https://api.github.com/";
    private static String GOOGLE_URL = "https://maps.googleapis.com/maps/api/";
    private Retrofit INSTANCE;
    private Retrofit GOOGLE_INSTANSE;

    static Retrofit getInstance() {
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

    private static Retrofit createInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Retrofit createGoogleInstance() {
        return new Retrofit.Builder()
                .baseUrl(GOOGLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Api getApi() {
        return getInstance().create(Api.class);
    }

}
