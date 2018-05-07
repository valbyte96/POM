package com.tvmcculloch.stress;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by tedmcculloch on 4/15/18.
 */

public class Game1View extends View {
    private Paint mPaint;
    private You you;
    private Fortress fortress1;
    private Fortress fortress2;
    private int score = 0;
    private LinkedList<Bullet> bullets = new LinkedList<>();
    private LinkedList<Bullet> eBullets = new LinkedList<>();

    private LinkedList<Bullet> removeB = new LinkedList<>();
    public static int height = getScreenHeight();
    public static int width = getScreenWidth();
    public static int unit = width/20;
    public static int maxTime = 100;
    public static long startTime;
    public static boolean move = false;
    public static Fortress[] hitList = new Fortress[2]; // stores fortresses

    private static UFO enemy;
    public static long time_1 = System.currentTimeMillis();
    public static long time_2 = System.currentTimeMillis();//
    public static boolean dir = true;
    public int hardness = SettingsActivity.hardness;



    //<---CONSTRUCTORS-->
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Game1View(Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Game1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Game1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Game1View(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        //Log.d("screen Width",Float.toString(width));
        //Log.d("screen height",Float.toString(height));
        mPaint = new Paint();
        you = new You(width/2, height-7*unit);
        fortress1 = new Fortress(width/2 + 2*unit,height/2);
        fortress2 = new Fortress(width/2 - 4*unit,height/2);
        hitList[0] = fortress1;
        hitList[1] = fortress2;
        // create enemies
        enemy = new UFO(width/2,height/10);
        enemy.drawn = true;


        //SET UP TOUCH LISTENER
        setOnTouchListener(new OnTouchListener() {
                               @Override
                               public boolean onTouch(View v, MotionEvent motionEvent) {
                                   boolean justFired = false;
                                   if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                                       startTime = System.currentTimeMillis();
                                   }
                                   if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN){
                                       if(System.currentTimeMillis()-startTime<=maxTime){
                                           justFired = true;
                                           bullets.add(new Bullet(you.getX(), you.getY())); // create a new bullet
                                       }

                                       //else if (motionEvent.getActionMasked() == MotionEvent.ACTION_MOVE) {
                                       else{
                                                you.setNewX(motionEvent.getX());
                                                move = true;
                                       }
                                        return true;
                                   }

                                   /**if (motionEvent.getActionMasked() == MotionEvent.ACTION_MOVE) {
                                       if(!justFired){
                                           you.move(motionEvent.getX());
                                       }



                                       return true;
                                   }*/
                                   return false;
                               }
                           }
        );}

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas){
        // UFO: BULLET CREATION
        if( System.currentTimeMillis()-time_1 >= 2000 && enemy.drawn){
            // fire
            eBullets.add(new Bullet(enemy.getX(), enemy.getY()));
            time_1 = System.currentTimeMillis();


        }
        else if(System.currentTimeMillis()-time_2 >= 3000 && enemy.drawn){
            eBullets.add(new Bullet(enemy.getX(), enemy.getY()));
            time_2 = System.currentTimeMillis();
            dir = !dir; // change direction

        }
        // DRAW STUFF




        // MANGE ENEMY BULLETS
        for(Bullet b: eBullets){
            if(b.getY()>=height-2*unit){
                removeB.add(b);
            }
            else if(you.isHit(b.getX(),b.getY(),b)){
                removeB.add(b);
            }
            else if(fortress1.isHit(b.getX(), b.getY()) && fortress1.drawn){
                removeB.add(b);
            }
            else if(fortress2.isHit(b.getX(), b.getY()) && fortress2.drawn){
                removeB.add(b);
            }
            else{
                b.fireDown();
                b.draw(canvas,mPaint);
            }

        }

        // draw UFO
        if(enemy.drawn){
            if(dir){
                enemy.moveRight();
            }
            else{
                enemy.moveLeft();
            }
            enemy.draw(canvas,mPaint);
        }

        // YOUR MANAGE BULLETS
        for(Bullet b: bullets){
            if (b.getY()<0){
                removeB.add(b);
            }
            else if(fortress1.isHit(b.getX(), b.getY()) && fortress1.drawn){
                removeB.add(b);
            }
            else if(fortress2.isHit(b.getX(), b.getY()) && fortress2.drawn){
                removeB.add(b);
            }
            else if(enemy.isHit(b.getX(), b.getY()) && enemy.drawn){
                removeB.add(b);
            }
            else{
                b.draw(canvas, mPaint);
                b.fire();
            }
        }

        for(Bullet b: removeB){
            bullets.remove(b);
        }
        removeB.clear();
        you.draw(canvas, mPaint);
        // move paddle
        if (move){
            float newX = you.getNewX();
            float origX = you.getX();
            float dx;
            int res = 40;
            for (int i=0; i<res; i++){
                if(newX>origX){ // move right
                    dx = (newX-origX)/res;
                    you.move(you.getX()+dx);
                }
                else{ // move left
                    dx = (origX-newX)/res;
                    you.move(you.getX()-dx);
                }
                you.draw(canvas, mPaint);

            }
            you.setNewX(you.getX());
            move = false; }





        you.draw(canvas, mPaint);

        if(fortress1.drawn){
            fortress1.draw(canvas, mPaint); // TODO: always redrawing?
        }
        if (fortress2.drawn){
            fortress2.draw(canvas, mPaint);
        }


        postInvalidateOnAnimation();
        // end game conditions
        Log.d("HC", Integer.toString(you.getHitCount()));
        if(you.getHitCount()/25>=15){
            gameOver();
        }




    }

    private void gameOver(){
        Intent newIntent = new Intent(this.getContext(), Replay.class);
        this.getContext().startActivity(newIntent);
    }


}
