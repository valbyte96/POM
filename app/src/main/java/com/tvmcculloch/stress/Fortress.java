package com.tvmcculloch.stress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by valeriemcculloch on 4/15/18.
 */

public class Fortress {
    private float x;
    private float y;
    private int color;
    private float unit  = Game1View.unit;
    private int hitCount = 0;
    public boolean drawn = true;



    public Fortress(float x, float y){
        this.x = x;
        this.y = y;
        this.color = Color.GREEN;
    }

    public void draw(Canvas canvas, Paint paint){
        if(hitCount>=15){
            drawn = false;

        }
        else if(hitCount>=10){
            this.color = Color.RED;
        }
        else if (hitCount>=5){
            this.color = Color.YELLOW;
        }
        else{
            this.color = Color.GREEN;
        }
        paint.setColor(this.color);
        canvas.drawRect(x,y,x+unit*3,y+unit*3,paint);
    }

    public boolean isHit(float bx, float by){
        if(bx>=x && bx<=x+unit*3 && by<=y+unit*3 && by>=y){

            hitCount+=1;
            return true;
        }

        return false;
    }

    public int getHitCount(){
        return hitCount;
    }
}
