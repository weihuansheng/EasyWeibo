package com.microwei.easyweibo.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.microwei.easyweibo.R;

/**
 * Created by Administrator on 2016-04-10 .
 */
public class CustomLine extends View{
    private Paint mPaint;
    public CustomLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        mPaint=new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(R.color.color_gray));
        canvas.drawLine(getPaddingLeft(), 0, getMeasuredWidth()-getPaddingRight(), 0, mPaint);
    }
}
