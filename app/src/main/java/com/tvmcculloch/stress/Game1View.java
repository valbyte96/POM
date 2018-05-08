package com.tvmcculloch.stress;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 *
 * Where game1 takes place; this class is responsible for drawing all of the players
 * as well as maintaining and tracking the game.
 */

public class Game1View extends View {
        // system variables
        public static int height = getScreenHeight();
        public static int width = getScreenWidth();
        public static int unit = width/20;
        public static int maxTime = 100; // for tuning double tap detection
        public static long startTime; // for detecting double tap
        public static boolean move = false; // for detecting You.class movement
        private Paint mPaint;

        // Game objects and data structures
        private You you;
        private Fortress fortress1;
        private Fortress fortress2;
        private LinkedList<Bullet> bullets = new LinkedList<>(); // user bullets
        private LinkedList<Bullet> eBullets = new LinkedList<>(); // enemy bullets
        private LinkedList<Bullet> removeB = new LinkedList<>(); // list of inactive bullets to undraw
        public static Fortress[] hitList = new Fortress[2]; // stores fortresses
        private static UFO enemy;

        // clocks for timing enemy moves
        public static long time_1 = System.currentTimeMillis();
        public static long time_2 = System.currentTimeMillis();
        public static boolean dir = true; // keeps track of enemy direction

        // retrieve difficulty setting from global preferences
        public SharedPreferences pref = this.getContext().getSharedPreferences("SeekValue", Context.MODE_PRIVATE);
        public int h = pref.getInt("SeekValue",10);




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

            // instantiate/set variables and objects
            mPaint = new Paint(); // instantiate "painbrush"
            you = new You(width/2, height-7*unit);
            fortress1 = new Fortress(width/2 + 2*unit,height/2);
            fortress2 = new Fortress(width/2 - 4*unit,height/2);
            hitList[0] = fortress1;
            hitList[1] = fortress2;
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
                                       if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN){ // double tap
                                           if(System.currentTimeMillis()-startTime<=maxTime){
                                               justFired = true;
                                               bullets.add(new Bullet(you.getX(), you.getY())); // create a new bullet
                                           }

                                           else{ // single tap
                                                    you.setNewX(motionEvent.getX());
                                                    move = true;
                                           }
                                            return true;
                                       }

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
            if( System.currentTimeMillis()-time_1 >= 2000-h*350 && enemy.drawn){
                // fire
                eBullets.add(new Bullet(enemy.getX(), enemy.getY(), h));
                time_1 = System.currentTimeMillis();


            }
            // UFO: MOVEMENT
            else if(System.currentTimeMillis()-time_2 >= 3000 && enemy.drawn){
                eBullets.add(new Bullet(enemy.getX(), enemy.getY(), h));
                time_2 = System.currentTimeMillis();
                dir = !dir; // change direction

            }


            // MANGE ENEMY BULLETS
            for(Bullet b: eBullets){
                if(b.getY()>=height-2*unit){ // out of bounds
                    removeB.add(b);
                }
                else if(you.isHit(b.getX(),b.getY(),b)){ // hit user
                    removeB.add(b);
                }
                else if(fortress1.isHit(b.getX(), b.getY()) && fortress1.drawn){ // hit fortress1
                    removeB.add(b);
                }
                else if(fortress2.isHit(b.getX(), b.getY()) && fortress2.drawn){ // hit fortress2
                    removeB.add(b);
                }
                else{
                    b.fireDown(); // fire
                    b.draw(canvas,mPaint); // draw
                }

            }

            // Draw UFO: if it is alive
            if(enemy.drawn){
                if(dir){
                    enemy.moveRight();
                }
                else{
                    enemy.moveLeft();
                }
                enemy.draw(canvas,mPaint);
            }

            // USER: MANAGE BULLETS
            for(Bullet b: bullets){
                if (b.getY()<0){ // out of bounds
                    removeB.add(b);
                }
                else if(fortress1.isHit(b.getX(), b.getY()) && fortress1.drawn){ // hit fortress1
                    removeB.add(b);
                }
                else if(fortress2.isHit(b.getX(), b.getY()) && fortress2.drawn){ // hit fortress2
                    removeB.add(b);
                }
                else if(enemy.isHit(b.getX(), b.getY()) && enemy.drawn){ // hit enemy
                    removeB.add(b);
                }
                else{ // fire bullet
                    b.draw(canvas, mPaint);
                    b.fire();
                }
            }

            // REMOVE INACTIVE BULLETS
            for(Bullet b: removeB){
                bullets.remove(b);
            }
            removeB.clear(); // empty list of undrawn objects
            you.draw(canvas, mPaint);

            // USER: MOVE
            if (move){
                float newX = you.getNewX();
                float origX = you.getX();
                float dx;
                int res = 40; //
                for (int i=0; i<res; i++){ // animation loop
                    if(newX>origX){ // move right
                        dx = (newX-origX)/res;
                        you.move(you.getX()+dx);
                    }
                    else{ // move left
                        dx = (origX-newX)/res;
                        you.move(you.getX()-dx);
                    }
                    you.draw(canvas, mPaint); // draw

                }
                you.setNewX(you.getX()); // record movement
                move = false; }


            you.draw(canvas, mPaint);

            // FORTRESSES: draw if not destroyed
            if(fortress1.drawn){
                fortress1.draw(canvas, mPaint); // TODO: always redrawing?
            }
            if (fortress2.drawn){
                fortress2.draw(canvas, mPaint);
            }


            postInvalidateOnAnimation(); // staggers animation
            // end game conditions
            if(you.getHitCount()>=10+h){ // user out of lives
                gameOver();
            }


        }

        private void gameOver(){
            /* Ends game by changing activity
            * NOTE: sometimes the animation gets a little wonky here.
            * It takes a moment to switch between activities.*/
            Intent newIntent = new Intent(this.getContext(), Replay.class);
            this.getContext().startActivity(newIntent);
        }


}
