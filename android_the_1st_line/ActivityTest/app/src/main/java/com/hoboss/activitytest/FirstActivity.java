package com.hoboss.activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("com.hoboss.activitytest.ACTION_START");
                // intent.addCategory("com.hoboss.activitytest.MY_CATEGORY");
                String data = "Hello SecondActivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                // startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("data_return");
                    Log.d(TAG, "onActivityResult: " + returnData);
                    Toast.makeText(this, returnData, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this,
                        "You clicked Add",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,
                        "You clicked Remove",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    public void goBaidu(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(intent);
    }
}
