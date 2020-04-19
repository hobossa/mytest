package com.hoboss.appwithsettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    public static final String
            KEY_PREF_EXAMPLE_SWITCH = "example_switch";
    public static final String
            KEY_PREF_EXAMPLE_SWITCH2 = "example_switch2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        Boolean switchPref = sharedPref.getBoolean
                (SettingsActivity.KEY_PREF_EXAMPLE_SWITCH, false);
        Boolean switchPref2 = sharedPref.getBoolean
                (SettingsActivity.KEY_PREF_EXAMPLE_SWITCH2, false);
        Toast.makeText(this, switchPref.toString()+switchPref2.toString(),
                Toast.LENGTH_SHORT).show();
    }
}
