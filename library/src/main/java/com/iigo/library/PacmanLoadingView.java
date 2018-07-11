package com.iigo.library;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/7/6 0006 14:15
 */
public class PacmanLoadingView extends View {
    private  final static int MAX_ANGEL = 90;
    private float peaDeltaRatio = 1.8f;//Control the peas movement speed.

    private final static int SPEED_NORMAL = 0;
    private final static int SPEED_FAST = 1;
    private final static int SPEED_SLOW = 2;

    private Paint peasPaint;
    private Paint eaterPaint;

    private int eaterColor = Color.WHITE; //The eater's color, the default is white.
    private int peasColor = Color.WHITE; //The peas's color, the default is white.

    private ValueAnimator eaterAnimator; //The eater's animator.
    private float eaterAngleRatio = 0; //The eater's angle ratio, 0°- 90°

    private float eaterRadius;
    private float peaRadius;
    private float peaStartXWhenCreate;
    private float peaStartYWhenCreate;

    private float howLongToCreateNewPea;

    private List<PointF> peasPointList; //To record the point of every peas.
    private int speed = SPEED_NORMAL;

    public PacmanLoadingView(Context context) {
        super(context);

        init();
    }

    public PacmanLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        parseAttrs(attrs);
        init();
    }

    public PacmanLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttrs(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PacmanLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttrs(attrs);
        init();
    }

    private void parseAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PacmanLoadingView);
        eaterColor = typedArray.getColor(R.styleable.PacmanLoadingView_eater_color, Color.WHITE);
        peasColor = typedArray.getColor(R.styleable.PacmanLoadingView_eater_color, Color.WHITE);
        speed = typedArray.getInt(R.styleable.PacmanLoadingView_speed, SPEED_NORMAL);
        typedArray.recycle();
    }

    private void init(){
        eaterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eaterPaint.setColor(eaterColor);
        eaterPaint.setStyle(Paint.Style.FILL);

        peasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        peasPaint.setColor(peasColor);
        peasPaint.setStyle(Paint.Style.FILL);

        peaDeltaRatio = getPeaDeltaRatio();

        eaterAnimator = ValueAnimator.ofFloat(0, 1);
        eaterAnimator.setInterpolator(new LinearInterpolator());
        eaterAnimator.setDuration(getAnimatorDuration());
        eaterAnimator.setRepeatMode(ValueAnimator.REVERSE);
        eaterAnimator.setRepeatCount(ValueAnimator.INFINITE);
        eaterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                eaterAngleRatio = (float) animation.getAnimatedValue();
                invalidate(); //go to refresh ui
            }
        });
    }

    private float getPeaDeltaRatio(){
        switch (speed){
            case SPEED_NORMAL:
                return 1.8f;

            case SPEED_FAST:
                return 3.6f;

            case SPEED_SLOW:
                return 0.9f;

        }

        return 1.8f;
    }

    private int getAnimatorDuration(){
        switch (speed){
            case SPEED_NORMAL:
                return 300;

            case SPEED_FAST:
                return 150;

            case SPEED_SLOW:
                return 600;

        }

        return 300;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        eaterRadius = w < h ? w / 2 : h / 2;

        peaRadius = eaterRadius / 20;
        howLongToCreateNewPea = peaRadius * 10;
        peaStartXWhenCreate = w + peaRadius;
        peaStartYWhenCreate = (h - peaRadius) / 2;

        if (peasPointList == null){
            peasPointList = new ArrayList<>();
        }
        peasPointList.clear();

        PointF peaPoint = new PointF(peaStartXWhenCreate, peaStartYWhenCreate);
        peasPointList.add(peaPoint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPeas(canvas);
        drawEater(canvas);
    }

    private void drawPeas(Canvas canvas){
        if (peasPointList == null || peasPointList.isEmpty()){
            return;
        }

        PointF newPeaPoint = null;
        PointF endPeaPoint = null;

        for (int i = 0; i < peasPointList.size(); i++){
            PointF peaPoint = peasPointList.get(i);
            canvas.drawCircle(peaPoint.x, peaPoint.y, peaRadius, peasPaint);

            if (i == (peasPointList.size() - 1)){
                if ((canvas.getWidth() - peaPoint.x) > howLongToCreateNewPea){
                    newPeaPoint = new PointF(peaStartXWhenCreate, peaPoint.y);
                }
            }

            float x = peaPoint.x -= peaDeltaRatio;
            if (x >= 0){
                peaPoint.set(x, peaPoint.y);
            }else{
                endPeaPoint = peaPoint;
            }
        }

        if (endPeaPoint != null) {
            peasPointList.remove(endPeaPoint); //when some pea hava  reached the end point, remove it.
        }

        if (newPeaPoint != null){
            peasPointList.add(newPeaPoint);
        }
    }

    private void drawEater(Canvas canvas){
        canvas.save();
        canvas.translate(0,(canvas.getHeight() - eaterRadius) / 2);
        RectF rectF = new RectF(0, 0, eaterRadius, eaterRadius);
        float angel = MAX_ANGEL * eaterAngleRatio;
        canvas.drawArc(rectF, angel / 2, 360 - angel, true, eaterPaint);
        canvas.restore();
    }

    public void start(){
        if (!eaterAnimator.isRunning()) {
            eaterAnimator.start();
        }
    }

    public void stop(){
        if (eaterAnimator.isRunning()) {
            eaterAnimator.cancel();
        }
    }

    public void setEaterColor(int eaterColor) {
        this.eaterColor = eaterColor;
        eaterPaint.setColor(eaterColor);
        invalidate();
    }

    public void setPeasColor(int peasColor) {
        this.peasColor = peasColor;
        peasPaint.setColor(peasColor);
        invalidate();
    }

    public float getEaterAngleRatio() {
        return eaterAngleRatio;
    }

    public int getPeasColor() {
        return peasColor;
    }

    public void destroy(){
        stop();
        if (peasPointList != null) {
            peasPointList.clear();
        }
    }
}
