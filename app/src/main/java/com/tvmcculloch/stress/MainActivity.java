package com.tvmcculloch.stress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.game1)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            game1();
            }
        });

        ((Button) findViewById(R.id.settings)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                settings();
            }
        });

    }
    private void game1(){
        startActivity(new Intent(this, Game1Activity.class));
    }

    private void settings(){
        startActivity(new Intent(this, SettingsActivity.class));
    }




}
