package com.tvmcculloch.stress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Replay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        ((Button) findViewById(R.id.reset)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                replay();
            }
        });

        ((Button) findViewById(R.id.menu)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                menu();
            }
        });

        ((Button) findViewById(R.id.settings)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                settings();
            }
        });
    }

    private void replay(){
        startActivity(new Intent(this, Game1Activity.class));
    }

    private void menu(){
        startActivity(new Intent(this, MainActivity.class));
    }

    private void settings(){
        startActivity(new Intent(this, SettingsActivity.class));
    }

}
