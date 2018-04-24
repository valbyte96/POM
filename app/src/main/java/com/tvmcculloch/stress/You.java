package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by tedmcculloch on 4/15/18.
 */

public class You {
    private float x;
    private float y;
    private float unit;
    private int color;
    private float newX;

    // constructor
    public You(float x, float y ){
        this.x = x;
        this.y = y;
        this.unit = Game1View.unit; // one twentieth of the screen width
        this.color = Color.GREEN;
    }

    // methods

    public void move(float x){
        this.x = x;
    }

    public void setNewX(float nX){
        this.newX = nX;
    }
    public float getNewX(){
        return this.newX;
    }

    public void changeColor(){
        if (this.color == Color.RED){
            this.color=Color.GREEN;
        }
        else{
            this.color = Color.RED;
        }
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(this.color);
        canvas.drawRect(x,y,x+unit*3,y+unit,paint);
        canvas.drawRect(x+unit, y, x+unit*2, y-unit/2, paint);

    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public void shoot(){
        Bullet b = new Bullet(this.x, this.y);
        //b.fire();

        // shoot a bullet
    }


}
