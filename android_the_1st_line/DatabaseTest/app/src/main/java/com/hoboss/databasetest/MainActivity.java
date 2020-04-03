package com.hoboss.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MyDatabaseHelper(this, "book_store.db",
                null, 2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
    }

    public void addData(View view) {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        db.insert("book", null, values);
        values.clear();
        values.put("name", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.95);
        db.insert("book", null, values);
    }

    public void updateData(View view) {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 10.99);
        db.update("book", values, "name = ?",
                new String[]{"The Da Vinci Code"});
    }

    public void deleteData(View view) {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        db.delete("book", "pages > ?",
                new String[]{"500"});
    }

    public void queryData(View view) {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d(TAG, "queryData: " + name + " " + author + " "
                        + String.valueOf(pages) + " " + String.valueOf(price));
                Toast.makeText(this, name + " " + author + " "
                        + String.valueOf(pages) + " " + String.valueOf(price),
                        Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
