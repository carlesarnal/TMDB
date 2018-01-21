package com.carlesarnal.themoviedb.app.moviedetail;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by carles on 21/01/18.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final OnSwipeListener onSwipeListener;

    public OnSwipeTouchListener(Context context, OnSwipeListener onSwipeListener) {
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.onSwipeListener = onSwipeListener;
    }

    public void onSwipeLeft() {
        this.onSwipeListener.onSwipeLeft();
    }

    public void onSwipeRight() {
        this.onSwipeListener.onSwipeRight();
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }
    }

    public interface OnSwipeListener {

        void onSwipeLeft();

        void onSwipeRight();
    }
}
