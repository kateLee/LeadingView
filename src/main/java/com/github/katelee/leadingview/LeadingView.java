package com.github.katelee.leadingview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created with IntelliJ IDEA.
 * User: Kate
 * Date: 2014/12/9
 * Time: 下午12:48
 */
public class LeadingView extends RelativeLayout implements View.OnTouchListener {
    private final Context context;
    private Resources resources;
    private AreaModel areaModel;
    private int foreground_rId;
    private Paint spotLightPaint;
    private int mTouchSlop;
    private OnClickListener onMaskListener = null;
    private OnClickListener onLeaderListener = null;
    private ImageView iv_foreground;

    public LeadingView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LeadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public LeadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (areaModel!=null && inRange(event))
        {
            if (onLeaderListener != null)
            {
                onLeaderListener.onClick(v);
            }
        }
        else
        {
            if (onMaskListener != null)
            {
                onMaskListener.onClick(v);
            }
        }
        return true;
    }

    private boolean inRange(MotionEvent event) {
        boolean isInRange = false;
        switch (areaModel.shapeType) {
            case RECTANGLE:
                Rect rect = areaModel.leadingElement.getRect(this);
                isInRange = event.getX() > rect.left - mTouchSlop && event.getX() < rect.right + mTouchSlop
                    && event.getY() > rect.top - mTouchSlop && event.getY() < rect.bottom + mTouchSlop;
                break;
        }
        return isInRange;
    }

    private void init()
    {
        resources = getResources();
        areaModel = null;
        foreground_rId = R.color.transparent;

        spotLightPaint = new Paint();
        spotLightPaint.setColor(Color.TRANSPARENT);
        spotLightPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        spotLightPaint.setAntiAlias(true);
        //avoid eraser to black
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
        setOnTouchListener(this);

        iv_foreground = new ImageView(context);
        iv_foreground.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addView(iv_foreground, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void insertAreaModel(AreaModel areaModel)
    {
        this.areaModel = areaModel;
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        Drawable fgDrawable = resources.getDrawable(foreground_rId);
//        fgDrawable.setBounds(0, 0, getWidth(), getHeight());
//        fgDrawable.draw(canvas);

        if (areaModel != null)
        {
//            Drawable leaderDrawable = resources.getDrawable(areaModel.background_rId);
            switch (areaModel.shapeType) {
                case RECTANGLE:
                    canvas.drawRect(areaModel.leadingElement.getRect(this), spotLightPaint);
//                    leaderDrawable.setColorFilter(areaModel.colorMask, PorterDuff.Mode.MULTIPLY);
//                    leaderDrawable.setBounds(areaModel.leadingElement.getRect());
//                    leaderDrawable.draw(canvas);
                    break;
            }
        }

        super.dispatchDraw(canvas);
    }

    public void hide(boolean isAnimate)
    {
//        if (!isAnimate)
        {
            setVisibility(GONE);
        }
//        else
//        {
//            startAnimation();
//        }
    }

//    /**
//     * <p>Start the indeterminate progress animation.</p>
//     */
//    void startAnimation() {
//        if (getVisibility() != VISIBLE) {
//            return;
//        }
//        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
//        animation.setDuration(500);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                setVisibility(GONE);
//            }
//        });
//        animation.start();
//    }

    public void setOnMaskListener(OnClickListener onMaskListener) {
        this.onMaskListener = onMaskListener;
    }

    public void setOnLeaderListener(OnClickListener onLeaderListener) {
        this.onLeaderListener = onLeaderListener;
    }

//    public void setForegroundResource(int foreground_rId) {
//        this.foreground_rId = foreground_rId;
//        invalidate();
//    }

    public ImageView getForegroundImage() {
        return iv_foreground;
    }
}
