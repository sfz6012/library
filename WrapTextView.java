package com.example.admin.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.admin.myapplication.R;

/**
 * Created by admin on 2018/11/9.
 * <p>
 * 封装内容：圆角   按下的颜色
 */

public class WrapTextView extends android.support.v7.widget.AppCompatTextView {


    private int pressedColor;
    private float radius;
    private RectF rect;
    private Paint paint;

    public WrapTextView(Context context) {
        super(context);

    }

    public WrapTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initPaint();
    }


    public WrapTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WrapTextView);
        pressedColor = typedArray.getColor(R.styleable.WrapTextView_press_state, Color.RED);
        radius = typedArray.getDimension(R.styleable.WrapTextView_radius, 10);
        typedArray.recycle();
    }


    private void initPaint() {
        rect = new RectF();
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(rect, radius, radius, paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        rect.set(0, 0, width, height);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                paint.setColor(pressedColor);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                paint.setColor(Color.RED);
                postInvalidate();
                break;
            default:

                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
