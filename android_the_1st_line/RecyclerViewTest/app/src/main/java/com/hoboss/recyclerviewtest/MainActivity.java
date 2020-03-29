package com.hoboss.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();

    private void initFruits() {
        String[] data = {"Apple", "Banana", "Orange", "Watermelon"};
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            for (String name : data) {
                double price = random.nextDouble() * 10 + 2;
                Fruit fruit = new Fruit(name, price);
                fruitList.add(fruit);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }
}
