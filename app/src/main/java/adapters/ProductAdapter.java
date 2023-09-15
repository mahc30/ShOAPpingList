package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shoappinglist.R;
import models.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, int layout, List<Product> products){
        this.context = context;
        this.layout = layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return this.products.size();
    }
    @Override
    public Object getItem(int position) {
        return this.products.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            v = inflater.inflate(R.layout.product_element,null);

        }
        else v=view;

        TextView title=(TextView) v.findViewById(R.id.desc_tb);
        title.setText(products.get(i).getDescription());

        return v;
    }
}
