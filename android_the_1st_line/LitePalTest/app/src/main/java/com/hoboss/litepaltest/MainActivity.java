package com.hoboss.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void createDatabase(View view) {
        Connector.getDatabase();
    }

    public void addData(View view) {
        Book book1 = new Book();
        book1.setName("The Da Vinci Code");
        book1.setAuthor("Dan Brown");
        book1.setPages(454);
        book1.setPrice(16.96);
        book1.setPress("unknown");
        book1.save();
        Book book2 = new Book();
        book2.setName("The Lost Symbol");
        book2.setAuthor("Dan Brown");
        book2.setPages(510);
        book2.setPrice(19.95);
        book2.setPress("unknown");
        book2.save();
    }

    public void updateData(View view) {
        Book book = new Book();
        book.setPrice(14.95);
        book.setPress("Anchor");
        book.updateAll("name = ? and author = ?",
                "The Lost Symbol", "Dan Brown");
    }

    public void deleteData(View view) {
        LitePal.deleteAll(Book.class, "price < ?", "15");
    }

    public void queryData(View view) {
        List<Book> books = LitePal.findAll(Book.class);
        for (Book book : books) {
            Log.d(TAG, "queryData: " + book.getName() + " "
                    + book.getAuthor() + " "
                    + String.valueOf(book.getPages()) + " "
                    + String.valueOf(book.getPrice()));
            Toast.makeText(this, book.getName() + " "
                            + book.getAuthor() + " "
                            + String.valueOf(book.getPages()) + " "
                            + String.valueOf(book.getPrice()),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
