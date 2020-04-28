package com.hoboss.largeimages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int toggle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeImage(View view) {
        if (toggle == 0) {
            try {
                Thread.sleep(32); // two refreshes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.setBackgroundResource(R.drawable.dinosaur_medium);
            toggle = 1;
        } else if (toggle == 1) {
            try {
                Thread.sleep(64); // two refreshes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.setBackgroundResource(R.drawable.dinosaur_large);
            toggle = 2;
        } else {
            view.setBackgroundResource(R.drawable.ankylo);
            toggle = 0;
        }
    }
}
