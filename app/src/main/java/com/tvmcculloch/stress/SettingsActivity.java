package com.tvmcculloch.stress;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    public SeekBar bar;
    public static int hardness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //get switches
        bar = (SeekBar) findViewById(R.id.seekBar);

        //save preferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        int settings = prefs.getInt("SeekValue", 1);
        hardness = settings;
        if(settings!=1){
            bar.setProgress(settings);
        }


    }
    @Override
    public void onBackPressed(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt("SeekValue", bar.getProgress());

        editor.commit();
        super.onBackPressed();
    }
}
