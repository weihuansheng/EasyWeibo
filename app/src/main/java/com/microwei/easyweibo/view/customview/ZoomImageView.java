package com.microwei.easyweibo.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2016-11-24 .
 */

public class ZoomImageView extends SimpleDraweeView {
    private static final String TAG="ZoomImageView";
    private Matrix mMatrix;

    private double prePointsDistance;

    private float preTouchX;
    private float preTouchY;

    private float preMoveX;
    private float preMoveY;
    private float translateDistanceX;
    private float translateDistanceY;

    private float scaleCenterX;
    private float scaleCenterY;
    private float scaleRatio;

    private static final int STATUS_INIT=-1;
    private static final int STATUS_TRANSLATE = 0;
    private static final int STATUS_SCALE = 1;
    private int currentStatus;

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZoomImageView(Context context) {
        super(context);
        init();
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mMatrix = new Matrix();
        currentStatus=STATUS_INIT;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                preTouchX=event.getX();
                preTouchY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float currentTouchX=event.getX();
                float currentTouchY=event.getY();
                if(Math.abs(currentTouchX-preTouchX)>Math.abs(currentTouchY-preTouchY)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                preMoveX = event.getX();
                preMoveY = event.getY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() > 1) {
                    prePointsDistance = calculatePointsDistance(event);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() > 1) {
//                    Log.d(TAG, "onTouchEvent: scale");
                    currentStatus = STATUS_SCALE;
                    double currentPointsDistance = calculatePointsDistance(event);
                    scaleRatio = (float) (currentPointsDistance / prePointsDistance);
                    scaleCenterX = calculateScaleCenterX(event);
                    scaleCenterY = calculateScaleCenterY(event);
                    prePointsDistance = currentPointsDistance;
                } else {
//                    Log.d(TAG, "onTouchEvent: translate");
                    currentStatus = STATUS_TRANSLATE;
                    float currentMoveX = event.getX();
                    float currentMoveY = event.getY();
                    translateDistanceX = currentMoveX - preMoveX;
                    translateDistanceY = currentMoveY - preMoveY;
                    preMoveX = currentMoveX;
                    preMoveY = currentMoveY;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentStatus == STATUS_SCALE) {
            scale(canvas);
        } else if(currentStatus==STATUS_TRANSLATE){
            translate(canvas);
        }
        super.onDraw(canvas);
    }

    private void translate(Canvas canvas) {
        mMatrix.postTranslate(translateDistanceX,translateDistanceY);
        canvas.setMatrix(mMatrix);
    }

    private void scale(Canvas canvas) {
        mMatrix.postScale(scaleRatio,scaleRatio,scaleCenterX,scaleCenterY);
        canvas.setMatrix(mMatrix);
    }

    private double calculatePointsDistance(MotionEvent event) {
        float distanceX = event.getX(0) - event.getX(1);
        float distanceY = event.getY(0) - event.getY(1);
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    private float calculateScaleCenterX(MotionEvent event) {
        return (event.getX(0) + event.getX(1)) / 2.0f;
    }

    private float calculateScaleCenterY(MotionEvent event) {
        return (event.getY(0) + event.getY(1)) / 2.0f;
    }
}
