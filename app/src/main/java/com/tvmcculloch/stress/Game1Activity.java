package com.tvmcculloch.stress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Game1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        extras.getInt("hardness");

        setContentView(R.layout.activity_game1);
        Game1View brickView = (Game1View) findViewById(R.id.game1_view);


    }



}

