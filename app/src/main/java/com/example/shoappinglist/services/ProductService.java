package com.example.shoappinglist.services;

import com.example.shoappinglist.models.Product;
import retrofit2.Call;

import java.util.List;

public class ProductService implements IProductService{
    @Override
    public Call<List<Product>> getProducts() {
        return null;
    }
}
