package com.example.shoappinglist.services;

import java.util.List;

import com.example.shoappinglist.models.Product;
import retrofit2.Call;
import retrofit2.http.*;

public interface IProductService {
    @GET("/product/")
    Call<List<Product>> getProducts();

    @POST("/product")
    Call<Product> createProduct(@Body Product product);

    @PUT("/product/{id}")
    Call<Product> updateProduct(@Path("id") long productId, @Body Product product);

    @DELETE("/product/{id}")
    Call<Void> deleteProduct(@Path("id") long productId);
}
