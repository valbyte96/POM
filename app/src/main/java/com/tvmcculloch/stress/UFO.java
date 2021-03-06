package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by tedmcculloch on 4/30/18.
 * Class for UFO or enemy object
 *
 */

public class UFO {
    private static float cX;
    private static float cY;
    private static float unit = Game1View.unit; // screen unit
    public static boolean drawn = true;
    public static int hitCount;


    // CONSTRUCTOR
    public UFO(float x, float y){
        this.cX = x;
        this.cY = y;
        this.hitCount = 0;

    }


    public void moveRight(){
        /* Moves UFO to the right
         */
        this.cX+=unit/25;
    }
    public void moveLeft(){
        /* Moves UFO to the right
         */
        this.cX-=unit/25;
    }

    // Getters: X & Y
    public float getX(){
        return cX;
    }
    public float getY(){
        return cY;
    }



    public boolean isHit(float x, float y){
        /* Reports if instance of UFO has been hit by user bullets*/
        if(x>=cX-10/2*unit && x<=cX+10/2*unit && y>=cY+unit*2 && y<=cY+3*unit){

            hitCount+=1;
            return true;
        }

        return false;
    }


    public void draw(Canvas canvas, Paint paint){
        /* Draws UFO as well as changes color*/
        Log.d("hit",Float.toString(hitCount));

        if(hitCount<5){
            paint.setColor(Color.RED);
        }
        else if(hitCount>=5 && hitCount <10){
            paint.setColor(Color.YELLOW);
        }
        else if(hitCount>=10 && hitCount <15){
            paint.setColor(Color.GREEN);
        }
        else if(hitCount>=15 && hitCount <20){
            paint.setColor(Color.BLUE);
        }
        else if(hitCount>=20 && hitCount<25) {
            paint.setColor(Color.MAGENTA);
        }
        else if(hitCount>=25) {
           this.drawn = false;
        }


        canvas.drawRect(cX-2*unit,cY,cX+2*unit,cY+unit,paint); // top rectangle
        canvas.drawRect(cX-4*unit,cY+unit,cX+4*unit,cY+2*unit,paint); // middle rectangle
        canvas.drawRect(cX-10/2*unit,cY+unit*2,cX+10/2*unit,cY+3*unit,paint); // middle rectangle 2: important for collision
        canvas.drawRect(cX-7/2*unit,cY+unit*3,cX+7/2*unit,cY+4*unit,paint); // bottom rectangle
        canvas.drawRect(cX-2*unit,cY+unit*4,cX-2*unit + unit/2,cY+5*unit,paint); // cannon 1
        canvas.drawRect(cX+2*unit - unit/2,cY+unit*4,cX+2*unit ,cY+5*unit,paint); // cannon 1

    }

}
