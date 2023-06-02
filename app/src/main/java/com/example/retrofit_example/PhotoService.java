package com.example.retrofit_example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoService {
    @GET("photos")
    Call<List<PhotoResponse>> getPhotos();
}
