package com.example.shoappinglist.services;

import com.example.shoappinglist.models.Product;
import retrofit2.Call;

import java.util.List;

public class ProductService implements IProductService{
    @Override
    public Call<List<Product>> getProducts() {
        return null;
    }

    @Override
    public Call<Product> createProduct(Product product) {
        return null;
    }

    @Override
    public Call<Product> updateProduct(long productId, Product product) {
        return null;
    }

    @Override
    public Call<Void> deleteProduct(long productId) {
        return null;
    }
}
