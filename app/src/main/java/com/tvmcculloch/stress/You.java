package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by tedmcculloch on 4/15/18.
 * User class: allows user to move and fire bullets
 */

public class You {
    private float x;
    private float y;
    private float unit;
    private int color;
    private float newX; // when a user taps on the screen this is the X coord of their tap
    private int hitCount = 0;

    // CONSTRUCTOR
    public You(float x, float y ){
        this.x = x;
        this.y = y;
        this.unit = Game1View.unit; // one twentieth of the screen width
        this.color = Color.GREEN;
    }

    // METHODS

    public void move(float x){
        /* moves user*/
        this.x = x;
    }

    public void setNewX(float nX){
        /* Sets new X*/
        this.newX = nX;
    }
    public float getNewX(){
        /* Gets new X*/
        return this.newX;
    }



    public void draw(Canvas canvas, Paint paint){
        /*
        * Draws user
        * */
        if(hitCount<6){
            this.color = Color.GREEN;
        }
        else if(hitCount>=6 && hitCount<=9){
            this.color = Color.YELLOW;
        }
        else{
            this.color = Color.RED;
        }
        paint.setColor(this.color);


        canvas.drawRect(x-unit*3/2,y,x+unit*3/2,y+unit,paint);
        canvas.drawRect(x-unit/2, y-unit/2, x+unit/2, y, paint);



    }
    public float getX(){
        /* Get current X of user*/
        return this.x;
    }
    public float getY(){
        /* Get current Y of user*/
        return this.y;
    }

    public boolean isHit(float x, float y, Bullet b){
        /* Determines if user has been hit*/
        if(x>=this.x-3/2*unit && x<=this.x+3/2*unit && y>=this.y && b.getActive()){
            b.setActive();
            this.hitCount+=1;
            return true;
        }

        return false;
    }
    public int getHitCount(){
        /* Get the number of times user has been hit*/
        return this.hitCount;
    }


}
