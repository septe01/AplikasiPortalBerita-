package com.example.aplikasiportalberita.network;

import com.example.aplikasiportalberita.response.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
//    TipeMethode(EndPoint)
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();
}
