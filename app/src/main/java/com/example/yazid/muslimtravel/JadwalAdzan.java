package com.example.yazid.muslimtravel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.yazid.muslimtravel.Model.ModelJadwal;
import com.example.yazid.muslimtravel.api.ApiService;
import com.example.yazid.muslimtravel.api.ApiUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JadwalAdzan extends AppCompatActivity {

    private TextView txtwilayah, txtsubuh, txtdzuhur, txtashar, txtmagrib, txtisya;
    private FloatingActionButton refreshbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_adzan);

        getSupportActionBar().setTitle("Jadwal Shalat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtwilayah = findViewById(R.id.txtwilayah);
        txtsubuh = findViewById(R.id.txtsubuh);
        txtdzuhur = findViewById(R.id.txtdzuhur);
        txtashar = findViewById(R.id.txtashar);
        txtmagrib = findViewById(R.id.txtmagrib);
        txtisya = findViewById(R.id.txtisya);
        refreshbtn = findViewById(R.id.refreshbtn);

        getJadwal();

        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJadwal();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }

        return super .onOptionsItemSelected(item);
    }

    private void getJadwal () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_HTTPS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ModelJadwal> call = apiService.getJadwal();

        call.enqueue(new Callback<ModelJadwal>() {
            @Override
            public void onResponse(Call<ModelJadwal> call, Response<ModelJadwal> response) {
                if (response.isSuccessful()) {
                    txtwilayah.setText("Bandung, "+ response.body().getItems().get(0).getDateFor());
                    txtsubuh.setText(response.body().getItems().get(0).getFajr());
                    txtdzuhur.setText(response.body().getItems().get(0).getDhuhr());
                    txtashar.setText(response.body().getItems().get(0).getAsr());
                    txtmagrib.setText(response.body().getItems().get(0).getMaghrib());
                    txtisya.setText(response.body().getItems().get(0).getIsha());
                } else {

                }
            }

            @Override
            public void onFailure(Call<ModelJadwal> call, Throwable t) {

            }
        });
    }
}
