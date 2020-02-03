package com.example.aplikasiportalberita;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiportalberita.response.BeritaItem;
import com.squareup.picasso.Picasso;

import java.util.List;

class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder> {
    //buat global variable untuk menampung context
    Context context;
    List<BeritaItem> berita;
    public AdapterBerita(Context context, List<BeritaItem> data_berita){
        //inisialisasi
        this.context = context;
        this.berita = data_berita;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Layout Inflater
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item,parent,false);
        //hubungkan dengan myViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //set widget
        holder.tvJudul.setText(berita.get(position).getJudulBerita());
        holder.tvTglTerbit.setText(berita.get(position).getTanggalPosting());

        //dapatkan url Gambar
        final String urlGambarBerita = "https://portalberitaapi.000webhostapp.com/images/"+berita.get(position).getFoto();
                //set image ke widget menggunakan library piccasso
                //karena imagenya dari internet
        Picasso.with(context).load(urlGambarBerita).into(holder.ivGambarBerita);

                //Even click ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Tidak Ada Berita Untuk Saat Ini!", Toast.LENGTH_SHORT).show();
                //Mulai activity detail
//                Intent varIntent = new Intent(context, DetailActivity.class);
                // sisipkan data ke intent
//                varIntent.putExtra("JDL_BERITA",berita.get(position).getJudulBerita());
//                varIntent.putExtra("TGL_BERITA",berita.get(position).getTanggalPosting());
//                varIntent.putExtra("PNS_BERITA",berita.get(position).getPenulis());
//                varIntent.putExtra("FTO_BERITA",urlGambarBerita);
//                varIntent.putExtra("ISI_BERITA",berita.get(position).getIsiBerita());

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
//                context.startActivity();
            }
        });
    }

    @Override
    public int getItemCount() {// Menentukan Jumlah item yang tampil
        return berita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //Declarasi widget
        ImageView ivGambarBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGambarBerita = (ImageView)itemView.findViewById(R.id.ivPosterBerita);
            tvJudul = (TextView)itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = (TextView)itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = (TextView)itemView.findViewById(R.id.tvPenulis);


        }
    }
}
