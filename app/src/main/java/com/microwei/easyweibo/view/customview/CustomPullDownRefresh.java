package com.microwei.easyweibo.view.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2016-04-02 .
 */
public class CustomPullDownRefresh extends ViewGroup {
    private int mTouchSlop;

    private static final int PULL_DOWN_HEAD_STATUS_HIDE = 0;
    private static final int PULL_DOWN_HEAD_STATUS_SHOW = 1;
    private int pullDownHeadStatus;

    private int touchY;
    private int moveY;
    private int dY;

    private ListView pullDownListView;
    private ProgressBar pullDownHeadProgressBar;
    private TextView pullDownHeadTextView;

    private int pullDownHeadHeight;

    private static final int HIDE_PULL_DOWN_HEAD_ANIM_TIME = 200;
    private static final int NOW_REFRESH_ANIM_TIME = 200;
    private static final int PULL_DOWN_REFRESH_INIT_STATUS = 0;
    private static final int PULL_DOWN_REFRESH = 1;
    private static final int UP_REFRESH = 2;
    private static final int NOW_REFRESH = 3;

    public interface PullDownRefreshListener {
        void nowRefresh();
    }

    private PullDownRefreshListener pullDownRefreshListener;

    public CustomPullDownRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ViewGroup pullDownHead = (ViewGroup) this.getChildAt(0);
        pullDownListView = (ListView) this.getChildAt(1);
        pullDownHeadProgressBar = (ProgressBar) pullDownHead.getChildAt(0);
        pullDownHeadTextView = (TextView) pullDownHead.getChildAt(1);
        pullDownHeadHeight = pullDownHead.getMeasuredHeight();
        int pullDownHeadWidth = pullDownHead.getMeasuredWidth();

        int contentHeight = pullDownListView.getMeasuredHeight();
        int contentWidth = pullDownListView.getMeasuredWidth();
        pullDownListView.layout(0, 0, contentWidth, contentHeight);
        pullDownHead.layout(0, -pullDownHeadHeight, pullDownHeadWidth, 0);
        pullDownHeadStatus = PULL_DOWN_HEAD_STATUS_HIDE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        View mFirstListViewChild = pullDownListView.getChildAt(0);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchY = (int) ev.getY();
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) ev.getY();
                dY = moveY - touchY;
                touchY = moveY;
                if (mFirstListViewChild == null) {
                    intercept = true;
                } else {
                    if (pullDownHeadStatus == PULL_DOWN_HEAD_STATUS_SHOW) {
//                        if (this.getScrollY() >= 0) {
//                            pullDownHeadStatus = PULL_DOWN_HEAD_STATUS_HIDE;
//                        }
                        intercept = true;
                    } else {
                        if (mFirstListViewChild.getTop() == 0 && dY > mTouchSlop) {
                            intercept = true;
                            pullDownHeadStatus = PULL_DOWN_HEAD_STATUS_SHOW;
                        } else {
                            intercept = false;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) ev.getY();
                dY = moveY - touchY;
                int realDY = (int) (0.3 * dY);
                touchY = moveY;
                this.scrollBy(0, -realDY);

                if (Math.abs(this.getScrollY()) >= pullDownHeadHeight) {
                    updatePullDownHeadShow(UP_REFRESH);
                } else {
                    updatePullDownHeadShow(PULL_DOWN_REFRESH);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(this.getScrollY()) >= pullDownHeadHeight) {
                    updatePullDownHeadShow(NOW_REFRESH);
                    startNowRefreshAnim();
                    if(pullDownRefreshListener!=null){
                        pullDownRefreshListener.nowRefresh();
                    }
                } else {
                    updatePullDownHeadShow(PULL_DOWN_REFRESH_INIT_STATUS);
                    startHidePullDownHeadAnim();
                }
                break;
        }
        return true;
    }

    private void updatePullDownHeadShow(int pullDownRefreshStatus) {
        switch (pullDownRefreshStatus) {
            case PULL_DOWN_REFRESH_INIT_STATUS:
                pullDownHeadProgressBar.setVisibility(GONE);
                pullDownHeadTextView.setText("");
                break;
            case PULL_DOWN_REFRESH:
                pullDownHeadProgressBar.setVisibility(GONE);
                pullDownHeadTextView.setText("下拉刷新");
                break;
            case UP_REFRESH:
                pullDownHeadProgressBar.setVisibility(GONE);
                pullDownHeadTextView.setText("释放立即刷新");
                break;
            case NOW_REFRESH:
                pullDownHeadProgressBar.setVisibility(VISIBLE);
                pullDownHeadTextView.setText("正在刷新...");
                break;
        }
    }

    public void startHidePullDownHeadAnim() {
        ObjectAnimator objectAnimator;
        objectAnimator = ObjectAnimator.ofInt(this, "scrollY", 0);
        objectAnimator.setDuration(HIDE_PULL_DOWN_HEAD_ANIM_TIME);
        objectAnimator.start();
        pullDownHeadStatus = PULL_DOWN_HEAD_STATUS_HIDE;
    }

    public void startNowRefreshAnim() {
        ObjectAnimator objectAnimator;
        objectAnimator = ObjectAnimator.ofInt(this, "scrollY", -pullDownHeadHeight);
        objectAnimator.setDuration(NOW_REFRESH_ANIM_TIME);
        objectAnimator.start();
    }

    public void setPullDownRefreshListener(PullDownRefreshListener pullDownRefreshListener) {
        this.pullDownRefreshListener = pullDownRefreshListener;
    }
}
