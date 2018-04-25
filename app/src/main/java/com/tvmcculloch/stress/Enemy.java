package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by tedmcculloch on 4/15/18.
 */

public class Enemy {
    private static float x;
    private static float y;
    private static int color = Color.WHITE;
    private float unit  = Game1View.unit;
    private float hitCount = 0;
    public boolean drawn = true;

    public Enemy(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getY(){
        return this.y;
    }
    public float getX(){
        return this.x;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(this.color);
        canvas.drawRect(this.x-unit/2,this.y,this.x+unit/2,this.y+unit,paint);

    }


}

