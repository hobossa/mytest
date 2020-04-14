package com.hoboss.twoactivitieslifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // Class name for Log tag.
    private static final String TAG
            = SecondActivity.class.getSimpleName();
    // Unique tag required for the intent extra
    public static final String EXTRA_MESSAGE
            = "com.hoboss.twoactivities.extra.MESSAGE";
    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY =
            "com.hoboss.twoactivities.extra.REPLY";

    // EditText for the reply.
    private EditText mReply;

    public static void startForResult(AppCompatActivity activity, String message, int requestCode) {
        Intent intent = new Intent(activity, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize view variables.
        mReply = findViewById(R.id.editText_second);

        // Get the intent that launched this activity, and the message in
        // the intent extra.
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        // Put that message into the text_message TextView.
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    public void returnReply(View view) {
        // Get the reply message from the edit text.
        String reply = mReply.getText().toString();

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        Log.d(TAG, "End SecondActivity");
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
