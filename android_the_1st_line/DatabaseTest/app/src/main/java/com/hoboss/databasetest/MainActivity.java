package com.hoboss.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MyDatabaseHelper(this, "book_store.db",
                null, 1);
        Button createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
    }
}
