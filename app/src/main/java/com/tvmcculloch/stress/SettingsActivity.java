package com.tvmcculloch.stress;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.Switch;

/* Adjusts game settings with difficulty bar
 */

public class SettingsActivity extends AppCompatActivity {
    private SeekBar bar;
    //private ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //get switches
        bar = (SeekBar) findViewById(R.id.seekBar);

        //save preferences
        SharedPreferences pref = getApplicationContext().getSharedPreferences("SeekValue", Context.MODE_PRIVATE);
        bar.setProgress(pref.getInt("SeekValue",1));


    }
    @Override
    public void onBackPressed(){
        /* When back arrow is pressed, it records the state of the bar into global preferences
         */
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("SeekValue", Context.MODE_PRIVATE).edit();
        editor.putInt("SeekValue", bar.getProgress());
        editor.commit();


        super.onBackPressed();
    }
}
