package com.example.hrmlix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hrmlix.api.ApiService;
import com.example.hrmlix.api.RetrofitClient;
import com.example.hrmlix.models.LoginRequest;
import com.example.hrmlix.models.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText = view.findViewById(R.id.editTextText2);
        passwordEditText = view.findViewById(R.id.editTextText3);
        loginButton = view.findViewById(R.id.button);

        //Here the click event occurs
        loginButton.setOnClickListener(v -> {
            String company_id = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!company_id.isEmpty() && !password.isEmpty()) {
                loginUser(company_id, password, view);
            } else {
                Toast.makeText(getContext(), "Enter email and password", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loginUser(String company_id, String password, View view) {
        ApiService apiService = RetrofitClient.getApiService();

        String deviceId = "32A12";

        Call<LoginResponse> call = apiService.loginUser(new LoginRequest(company_id, password, deviceId));

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.out.println(response.isSuccessful() );
                if (response.isSuccessful() && response.body() != null) {

                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_dashFragment);
                } else {
                    Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Login error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}