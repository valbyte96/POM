package com.tvmcculloch.stress;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.tvmcculloch.stress.Game1Activity;
import com.tvmcculloch.stress.MainActivity;
import com.tvmcculloch.stress.R;
import com.tvmcculloch.stress.SettingsActivity;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((Button) findViewById(R.id.game1)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                replay();
            }
        });

        ((Button) findViewById(R.id.settings)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                menu();
            }
        });


    }

    private void replay(){
        startActivity(new Intent(this, Game1Activity.class));
    }

    private void menu(){
        startActivity(new Intent(this, MainActivity.class));
    }

}
