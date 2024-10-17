package com.example.hrmlix;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class DashFragment extends Fragment {

    private Button kioskButton;
    private ImageView settingsIconButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dash, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        kioskButton = view.findViewById(R.id.kioskBtn);
        settingsIconButton = view.findViewById(R.id.settingIcon);

        kioskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement kiosk mode logic here
                Navigation.findNavController(view).navigate(R.id.action_dashFragment_to_cameraFragment);

            }
        });

        settingsIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(view).navigate(R.id.action_dashFragment_to_settingsFragment);

            }
        });
    }

}