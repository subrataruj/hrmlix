package com.example.hrmlix.api;


import com.example.hrmlix.models.LoginRequest;
import com.example.hrmlix.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/company_login")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
