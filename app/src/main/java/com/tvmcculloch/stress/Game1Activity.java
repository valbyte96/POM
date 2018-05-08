package com.tvmcculloch.stress;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
/* Activity class for game one
 * displays the Game1View where game takes place */

public class Game1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        Game1View view = (Game1View) findViewById(R.id.game1_view);


    }



}

