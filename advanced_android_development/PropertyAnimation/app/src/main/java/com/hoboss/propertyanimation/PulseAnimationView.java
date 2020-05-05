package com.hoboss.propertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class PulseAnimationView extends View {
    private static final int COLOR_ADJUSTER = 5;
    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;

    private float radius;
    private final Paint paint = new Paint();
    private float x;
    private float y;
    private AnimatorSet pulseAnimatorSet = new AnimatorSet();

    public PulseAnimationView(Context context) {
        this(context, null);
    }

    public PulseAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Required setter for the animated property.
     * Called by the Animator to update the property.
     *
     * @param radius This view's radius property.
     */
    public void setRadius(float radius) {
        this.radius = radius;
        paint.setColor(Color.GREEN + (int) radius / COLOR_ADJUSTER);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
            if(pulseAnimatorSet != null && pulseAnimatorSet.isRunning()) {
                pulseAnimatorSet.cancel();
            }
            pulseAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // propertyName "radius" means animator will change radius by invoking setRadius
        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this,
                "radius", 0, getWidth());
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this,
                "radius", getWidth(), 0);
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        ObjectAnimator repeatAnimator = ObjectAnimator.ofFloat(this,
                "radius", 0, getWidth());
        repeatAnimator.setStartDelay(ANIMATION_DELAY);
        repeatAnimator.setDuration(ANIMATION_DURATION);
        repeatAnimator.setRepeatCount(1);
        repeatAnimator.setRepeatMode(ValueAnimator.REVERSE);

        pulseAnimatorSet.play(growAnimator).before(shrinkAnimator);
        pulseAnimatorSet.play(repeatAnimator).after(shrinkAnimator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }
}
