package com.example.shoappinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.shoappinglist.R;
import com.example.shoappinglist.models.Product;

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


        TextView title=(TextView) v.findViewById(R.id.name_tb);
        TextView note=(TextView) v.findViewById(R.id.note_tb);
        TextView price=(TextView) v.findViewById(R.id.price_tb);
        title.setText(products.get(i).getName());
        note.setText(products.get(i).getNote());
        price.setText(String.format("%d",products.get(i).getPrice()));

        Button deleteBtn = (Button) v.findViewById((R.id.delete_btn));
        deleteBtn.setText("Delete " + products.get(i).getName());
        deleteBtn.setTag(i);

        Button editBtn = (Button) v.findViewById(R.id.edit_btn);
        editBtn.setTag(i);
        editBtn.setText("Edit " + products.get(i).getName());
        return v;
    }
}
