package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by valeriemcculloch on 4/15/18.
 */

public class Bullet {
    private float x;
    private float y;
    private int color = Color.WHITE;
    private float unit  = Game1View.unit;
    private int dy = 5;



    public Bullet(float x, float y){
        this.x = x;
        this.y = y;

    }

    public void center(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void fire(){
        this.y -=dy;
    }

    public float getY(){
        return this.y;
    }
    public float getX(){
        return this.x;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(this.color);
        canvas.drawRect(x-unit/4,y,x+unit/4,y+unit/2,paint);

    }

}
