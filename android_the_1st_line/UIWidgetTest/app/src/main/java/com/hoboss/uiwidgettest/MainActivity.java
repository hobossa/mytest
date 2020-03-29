package com.hoboss.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

// implements View.OnclickListener
//        Button button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                // This class was deprecated in API level 26.
                // ProgressDialog is a modal dialog, which prevents the user from interacting
                // with the app. Instead of using this class, you should use a progress indicator
                // like ProgressBar, which can be embedded in your app's UI. Alternatively, you can
                // use a notification to inform the user of the task's progress.
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is a ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();

                // AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                // dialog.setTitle("This is Dialog");
                // dialog.setMessage("Something important.");
                // dialog.setCancelable(false);
                // dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                //     @Override
                //     public void onClick(DialogInterface dialog, int which) {
                //         Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                //     }
                // });
                // dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                //     @Override
                //     public void onClick(DialogInterface dialog, int which) {
                //         Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                //     }
                // });
                // dialog.show();

                // String inputText = editText.getText().toString();
                // if (!inputText.isEmpty()) {
                //     Toast.makeText(MainActivity.this,
                //             inputText, Toast.LENGTH_SHORT).show();
                // }

                // int progress = progressBar.getProgress();
                // progressBar.setProgress(progress + 10);
                // if (progress > 80) {
                //     imageView.setImageResource(R.drawable.ic_launcher_background);
                // }
                // if (progressBar.getVisibility() == View.GONE) {
                //     progressBar.setVisibility(View.VISIBLE);
                //     imageView.setImageResource(R.drawable.ic_launcher_foreground);
                // } else {
                //     progressBar.setVisibility(View.GONE);
                //     imageView.setImageResource(R.drawable.ic_launcher_background);
                // }
                break;
            default:
                break;
        }
    }
}
