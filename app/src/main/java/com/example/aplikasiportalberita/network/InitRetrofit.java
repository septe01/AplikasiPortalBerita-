package com.example.aplikasiportalberita.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {

//    URl Server Api
    public static String Api_Url = "https://portalberitaapi.000webhostapp.com/";

    public static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(Api_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
