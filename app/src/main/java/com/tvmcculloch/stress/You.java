package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by tedmcculloch on 4/15/18.
 */

public class You {
    private float x;
    private float y;
    private float unit;
    private int color;
    private float newX;
    private int hitCount = 0;
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

        if(hitCount<7){
            this.color = Color.GREEN;
        }
        else if(hitCount>=7 && hitCount<=12){
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
        return this.x;
    }
    public float getY(){
        return this.y;
    }

    public boolean isHit(float x, float y, Bullet b){
        if(x>=this.x-3/2*unit && x<=this.x+3/2*unit && y>=this.y && y<=this.y+unit && b.getActive()){
            b.setActive();
            this.hitCount+=1;
            return true;
        }

        return false;
    }
    public int getHitCount(){
        return this.hitCount;
    }


}
