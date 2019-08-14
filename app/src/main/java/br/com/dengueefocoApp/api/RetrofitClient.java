package br.com.dengueefocoApp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String URL = "http://192.168.0.13:8080/";
    private static String GOOGLE_URL = "https://maps.googleapis.com/maps/api/";
    private static String VIA_CEP_URL = "https://viacep.com.br/ws/";
    private static Retrofit INSTANCE;
    private static Retrofit GOOGLE_INSTANSE;
    private static Retrofit VIA_CEP_INSTANSE;

    static Retrofit getInstance() {
        if (INSTANCE == null) {
            return createInstance();
        } else {
            return INSTANCE;
        }
    }

    static Retrofit getGoogleInstance() {
        if (GOOGLE_INSTANSE == null) {
            return createGoogleInstance();
        } else {
            return GOOGLE_INSTANSE;
        }
    }

    static Retrofit getViaCepInstance() {
        if (VIA_CEP_INSTANSE == null) {
            return createViaCepInstance();
        } else {
            return VIA_CEP_INSTANSE;
        }
    }

    private static Retrofit createInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Retrofit createGoogleInstance() {
        return new Retrofit.Builder()
                .baseUrl(GOOGLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Retrofit createViaCepInstance() {
        return new Retrofit.Builder()
                .baseUrl(VIA_CEP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ViaCepApi getViaCepApi() {
        return getViaCepInstance().create(ViaCepApi.class);
    }

    public static Api getApi() {
        return getInstance().create(Api.class);
    }

    public static GoogleApi getGoogleApi() { return getGoogleInstance().create(GoogleApi.class); }
}
