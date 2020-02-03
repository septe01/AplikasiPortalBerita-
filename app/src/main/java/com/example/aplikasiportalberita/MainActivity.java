package com.example.aplikasiportalberita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.aplikasiportalberita.network.ApiServices;
import com.example.aplikasiportalberita.network.InitRetrofit;
import com.example.aplikasiportalberita.response.BeritaItem;
import com.example.aplikasiportalberita.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView; // deklarasi wiget
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inisialisasi wiget
        recyclerView = (RecyclerView)findViewById(R.id.rvListBerita);
        //recyclerview hrs pakai layoutmanager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //eksekusi data
        tampilBerita();
    }

    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();
        //siapkan request
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                //pastikan response sukses
                if(response.isSuccessful()){
                    Log.d("response Api", response.body().toString());
                            //tampung response body to variable
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    if (status){
                        //buat adapter untuk recycler view
                        AdapterBerita Adapter = new AdapterBerita(MainActivity.this,data_berita);

                        Log.i("data",data_berita.toString());
                        recyclerView.setAdapter(Adapter);
                    }else{ //kalau response tidak true
                        Toast.makeText(MainActivity.this,"Tidak Ada Berita Untuk Saat Ini!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                //print ke log jika Error
                t.printStackTrace();
            }
        });

    }
}
