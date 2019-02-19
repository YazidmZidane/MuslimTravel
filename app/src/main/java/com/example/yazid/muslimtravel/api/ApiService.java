package com.example.yazid.muslimtravel.api;

import com.example.yazid.muslimtravel.Model.ModelJadwal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("jakarta.json")
    Call<ModelJadwal> getJadwal();
}
