package com.example.hrmlix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.arthenica.ffmpegkit.FFmpegKit;

import okhttp3.EventListener;
import okhttp3.OkHttpClient;

public class CameraFragment extends Fragment {

    private static final String TAG = "CameraFragment";

    private SurfaceView surfaceView;
    private ProcessCameraProvider cameraProvider;
    private Preview preview;

    private OkHttpClient client;


@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_camera, container, false);
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        surfaceView = view.findViewById(R.id.surfaceView);

        startCamera();

        startStreaming();
//
//        client = new OkHttpClient();
//        startSseListening();
    }

    private void startCamera() {
        System.out.println("Camera started...");

        final SurfaceHolder surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {

                System.out.println("Surface created...");

                try{
//                    cameraProvider = ProcessCameraProvider.getInstance(CameraFragment.this).get();//This will not work, that's why i chnaged this

                    cameraProvider = ProcessCameraProvider.getInstance(requireContext()).get();

                   preview = new Preview.Builder().build();

                    preview.setSurfaceProvider(request ->{

                        Surface surface = holder.getSurface();

                        request.provideSurface(surface, ContextCompat.getMainExecutor(requireContext()), result ->{
                            if (result.getResultCode() == SurfaceRequest.Result.RESULT_SURFACE_USED_SUCCESSFULLY) {
                                Log.i("Msg ", "Surface provided successfully.");
                            } else {
                                Log.e("Msg ", "Surface request failed.");
                            }
                        });
                    });

                    System.out.println("Checking....");

                    if (cameraProvider.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)) {
                        System.out.println("Front Camera working...");
                        CameraSelector cameraSelector = new CameraSelector.Builder()
                                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                                .build();
                        cameraProvider.bindToLifecycle(CameraFragment.this, cameraSelector, preview);
                    } else {
                        Log.e("Msg", "Front camera not available");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });
    }

    private void startStreaming()
    {

        System.out.println("Streaming started .....");

//        String serverIp = "192.168.20.83";
        String serverIp = "192.168.1.187";
        String port = "8554";
        String rtspUrl = String.format("rtsp://%s:%s/ch0", serverIp, port);
        System.out.println("rtsp url is "+rtspUrl);

        String command = "-f android_camera -i 1:0 -s 640x480 -r 15 -vcodec mpeg4 -f rtsp " + rtspUrl;
//      String command = "ffmpeg -f v4l2 -i /dev/video0 -s 640x360 -r 15 -vcodec h264 -f rtsp "+rtspUrl;

        System.out.println("Command is: " + command);

        FFmpegKit.executeAsync(command, session -> {
            if (session.getReturnCode().isValueSuccess()) {
                Log.d("FFmpeg", "Streaming started successfully.");
            } else {
                Log.e("FFmpeg", "Failed to start stream: " + session.getFailStackTrace());
            }
        });

    }

}