//package com.example.hrmlix;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.navigation.Navigation;
//
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class SplashFragment extends Fragment {
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_splash, container, false);
//
//        // Navigate to login after 3 seconds
//        new Handler().postDelayed(() -> {
//            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
//        }, 2000); // 3 seconds
//
//        return view;
//    }
//
//}