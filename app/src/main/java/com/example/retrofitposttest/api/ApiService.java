package com.example.retrofitposttest.api;

import com.example.retrofitposttest.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

//    https://jsonplaceholder.typicode.com/photos
    Gson GSON =new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService API_SERVICE= new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build().create(ApiService.class);

    @GET("photos")
    Call<List<Product>> callProduct();

}
