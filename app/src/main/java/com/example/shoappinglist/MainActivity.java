package com.example.shoappinglist;

import android.util.Log;
import com.example.shoappinglist.adapters.ProductAdapter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.shoappinglist.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import com.example.shoappinglist.models.Product;
import com.example.shoappinglist.services.IProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ArrayList<Product> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvProduct);
        items = new ArrayList<Product>();
        productAdapter = new ProductAdapter(this, R.layout.product_element,items);

        IProductService productService = ShoappingListApplication.getRetrofitInstance().create(IProductService.class);

        // Make the GET request
        Call<List<Product>> call = productService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    for (Product product : products) {
                        items.add(product);

                    }

                    items.add(new Product(5L, "Arepa", "EEE", 2500));
                    lvItems.setAdapter(productAdapter);

                } else {
                    // Handle the error
                    Log.e("API Error", "Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Handle network or other errors
                Log.e("API Error", "Request failed: " + t.getMessage());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}