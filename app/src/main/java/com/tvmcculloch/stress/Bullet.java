package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by tedcculloch on 4/15/18.
 * bullet class:
 * There are two types of bullets:
 * > user bullets
 * > enemy bullets
 *
 * two constructors for each type.
 *
 */

public class Bullet {
    private float x;
    private float y;
    private int color = Color.WHITE;
    private float unit  = Game1View.unit;
    private int dy = 5;
    private boolean active = true;
    private int hardness;


    // Constructor 1 - user bullet
    public Bullet(float x, float y){
        this.x = x;
        this.y = y;

    }

    // Constructor 2 - enemy bullet
    public Bullet(float x, float y, int hardness){
        this.x = x;
        this.y = y;
        this.hardness = hardness;

    }

    public void fireDown(){
        /* Method for firing enemy bullets;
         * moves bullets toward user (You.class) at a speed
         * dependent on system settings
         *  */
        this.y +=(dy+hardness*unit/8);
    }

    public void fire(){
        /* Method for firing user bullets;
         * moves bullets toward the top of screen
         */
        this.y -=dy;
    }


    public void draw(Canvas canvas, Paint paint){
        /* draws bullets on application screen*/
        paint.setColor(this.color);
        canvas.drawRect(x-unit/4,y,x+unit/4,y+unit/2,paint);

    }

    // SETTERS
    public void setActive(){
        /* makes bullets that have hit something inactive*/
        this.color = Color.RED;
        this.active = false;
    }

    // ACCESSORS
    public boolean getActive(){
        /* Returns true if bullet is active; false otherwise*/
        return this.active;
    }
    public float getY(){
        /* Getter for this.y*/
        return this.y;
    }
    public float getX(){
        /* Getter for this.x*/
        return this.x;
    }

}
