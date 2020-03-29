package com.hoboss.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        TextView fruitName;
        TextView fruitPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitView = itemView;
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
            fruitPrice = (TextView) itemView.findViewById(R.id.fruit_price);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = fruitList.get(position);
                Toast.makeText(v.getContext(), fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        DecimalFormat df = new DecimalFormat("#.00");
        holder.fruitPrice.setText(df.format(fruit.getPrice()));
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
