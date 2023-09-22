package com.example.shoappinglist;

import android.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.shoappinglist.adapters.ProductAdapter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.shoappinglist.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import com.example.shoappinglist.models.Product;
import com.example.shoappinglist.services.IProductService;
import com.example.shoappinglist.services.SOAP.ProductClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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
    private final ProductClient productSoapClient = new ProductClient();

    private final IProductService productService = ShoappingListApplication.getRetrofitInstance().create(IProductService.class);

    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<Product>();
        productAdapter = new ProductAdapter(this, R.layout.product_element,items);
        lvItems = (ListView) findViewById(R.id.lvProduct);


        Call<List<Product>> call = productService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    for (Product product : products) {
                        items.add(product);
                    }

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

    public void deleteProduct(View view) {
        Button button = (Button)view;
        Long id = Long.parseLong(button.getText().toString().split(" ")[2]);
        Call<Void> call = productService.deleteProduct(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    for(int i = 0; i < items.size(); i++){
                        Product p = items.get(i);
                        if(p.getId() == id){
                            items.remove(i);
                            lvItems.setAdapter(productAdapter);
                            break;
                        }
                    }
                } else {
                    // Handle the error
                    Log.e("API Error", "Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network or other errors
                Log.e("API Error", "Request failed: " + t.getMessage());
            }
        });


    }

    public void upcreateProduct(View view) {

        TextInputEditText inId = findViewById(R.id.input_id);
        TextInputEditText inName = findViewById(R.id.input_name);
        TextInputEditText inNote = findViewById(R.id.input_note);
        TextInputEditText inPrice = findViewById(R.id.input_price);

        Product product = new Product(-1L, inName.getText().toString(), inNote.getText().toString(), Integer.parseInt(inPrice.getText().toString()));

        if(inId.length() == 0) {
            productSoapClient.createProduct(product);
            items.add(product);
        }
        else{
        Long id = Long.parseLong(inId.getText().toString());
        product.setId(id);
            Call<Void> call = productService.updateProduct(product);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {

                        lvItems.setAdapter(productAdapter);
                    } else {
                        // Handle the error
                        Log.e("API Error", "Request failed with code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle network or other errors
                    Log.e("API Error", "Request failed: " + t.getMessage());
                }
            });
        }

        lvItems.setAdapter(productAdapter);
    }

       public void editProduct(View view){
           TextInputEditText inId = findViewById(R.id.input_id);
           TextInputEditText inName = findViewById(R.id.input_name);
           TextInputEditText inNote = findViewById(R.id.input_note);
           TextInputEditText inPrice = findViewById(R.id.input_price);
    }
}