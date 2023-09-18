package com.example.shoappinglist.services;

import java.util.List;

import com.example.shoappinglist.models.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IProductService {
    @GET("/product/")
    Call<List<Product>> getProducts();
}
