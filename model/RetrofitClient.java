package com.example.wedhall_reservationsystem.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    // Method to get the Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/wedding_hall/") // Base URL for your API
                    .addConverterFactory(GsonConverterFactory.create()) // Gson converter for JSON
                    .build();
        }
        return retrofit;
    }
}
