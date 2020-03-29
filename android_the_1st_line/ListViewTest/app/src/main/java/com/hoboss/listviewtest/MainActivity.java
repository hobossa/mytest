package com.hoboss.listviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransitionImpl;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();

    private void initFruits() {
        String[] data = {"Apple", "Banana", "Orange", "Watermelon"};
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
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
        FruitAdapter adapter = new FruitAdapter(
                MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
