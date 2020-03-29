package com.hoboss.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public FruitAdapter(@NonNull Context context,
                        int resource,
                        @NonNull List<Fruit> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    class ViewHolder {
        TextView fruitName;
        TextView fruitPrice;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                false);
            viewHolder = new ViewHolder();
            viewHolder.fruitName = (TextView)view.findViewById(R.id.fruit_name);
            viewHolder.fruitPrice = (TextView)view.findViewById(R.id.fruit_price);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.fruitName.setText(fruit.getName());
        DecimalFormat df = new DecimalFormat("#.00");
        viewHolder.fruitPrice.setText(df.format(fruit.getPrice()));
        return view;
    }
}
