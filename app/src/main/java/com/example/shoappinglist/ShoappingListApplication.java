package com.example.shoappinglist;

import android.app.Application;
import com.example.shoappinglist.services.socket.SocketClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ShoappingListApplication extends Application {
    private static final String BASE_URL = "http://10.0.2.2:8080/";

    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Retrofit instance
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofitInstance() {
        return retrofit;
    }
}
