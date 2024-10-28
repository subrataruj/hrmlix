package com.example.hrmlix.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
//                    .baseUrl("https://dev14.ivantechnology.in/meterpark/api/v1/")
                    .baseUrl("http://192.168.1.227:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static ApiService getApiService() {
        return getInstance().create(ApiService.class);
    }
}
