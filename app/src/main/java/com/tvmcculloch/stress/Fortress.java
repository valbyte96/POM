package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by tedmcculloch on 4/15/18.
 * Class for barriers between user (You.class) and enemy (UFO.class)
 * Change color based health
 */

public class Fortress {
    private float x;
    private float y;
    private int color;
    private float unit  = Game1View.unit; // global screen unit
    private int hitCount = 0;
    public boolean drawn = true;


    // Constructor
    public Fortress(float x, float y){
        this.x = x;
        this.y = y;
        this.color = Color.GREEN;
    }

    public void draw(Canvas canvas, Paint paint){
        /* Draws instance of Fortress.class on application screen
         * Colors vary depending on fortress health*/
        if(hitCount>=50){ // fortress destroyed
            drawn = false;
        }
        else if(hitCount>=40){ // turn fortress red
            this.color = Color.RED;
        }
        else if (hitCount>=25){ // turn fortress yellow
            this.color = Color.YELLOW;
        }
        else{
            this.color = Color.GREEN;
        }
        paint.setColor(this.color);
        canvas.drawRect(x,y,x+unit*3,y+unit*3,paint);
    }

    public boolean isHit(float bx, float by){
        /* Given the x and y coordinates of a bullet, this reports
        * if instance of fortress was hit*/
        if(bx>=x && bx<=x+unit*3 && by<=y+unit*3 && by>=y){

            hitCount+=1;
            return true;
        }

        return false;
    }

}
