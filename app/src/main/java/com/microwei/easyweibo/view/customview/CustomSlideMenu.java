package com.microwei.easyweibo.view.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2016-04-02 .
 */
public class CustomSlideMenu extends HorizontalScrollView{
    private int touchX;
    private int touchY;
    private int moveX;
    private int moveY;
    private int slideMenuWidth;
    private int halfSlideMenuWidth;
    private final int SLIDE_MENU_SHOW_OR_HIDE_TIME=200;
    public CustomSlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        slideMenuWidth=((ViewGroup)getChildAt(0)).getChildAt(0).getMeasuredWidth();
        halfSlideMenuWidth= (int) (0.5*slideMenuWidth);
        this.scrollBy(slideMenuWidth, 0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept=false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX= (int) ev.getX();
                touchY= (int) ev.getY();
                intercept=false;
                break;
            case MotionEvent.ACTION_MOVE:
                moveX= (int) ev.getX();
                moveY= (int) ev.getY();
                if(Math.abs(moveX-touchX)>Math.abs(moveY-touchY)){
                    intercept=true;
                }
                else {
                    intercept=false;
                }
                touchX=moveX;
                touchY=moveY;
                break;
            case MotionEvent.ACTION_UP:
                intercept=false;
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_UP:
                //Log.v("WEI","scrollx:"+getScrollX());
                if(getScrollX()<halfSlideMenuWidth) {
                    showSlideMenu();
                }
                else {
                    hideSlineMenu();
                }
                return true;
        }

        return super.onTouchEvent(ev);
    }
    private void showSlideMenu(){
        ObjectAnimator hideSlideMenuAnim=ObjectAnimator.ofInt(this,"scrollX",0);
        hideSlideMenuAnim.setDuration(SLIDE_MENU_SHOW_OR_HIDE_TIME);
        hideSlideMenuAnim.start();
    }
    private void hideSlineMenu(){
        ObjectAnimator showSlideMenuAnim=ObjectAnimator.ofInt(this,"scrollX",slideMenuWidth);
        showSlideMenuAnim.setDuration(SLIDE_MENU_SHOW_OR_HIDE_TIME);
        showSlideMenuAnim.start();

    }
}
