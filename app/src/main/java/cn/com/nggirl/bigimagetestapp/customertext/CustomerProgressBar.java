package cn.com.nggirl.bigimagetestapp.customertext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.com.nggirl.bigimagetestapp.R;


public class CustomerProgressBar extends View {
    private int firstColor;
    private int secondColor;
    private int circleWidth;
    private int speed;

    private int progress;
    private boolean isNext = false;
    private Paint mPaint;

    public CustomerProgressBar(Context context) {
        this(context, null);
    }

    public CustomerProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomerProgressBar, defStyleAttr, 0);
        firstColor = typedArray.getColor(R.styleable.CustomerProgressBar_firstColor, Color.RED);
        secondColor = typedArray.getColor(R.styleable.CustomerProgressBar_secondColor, Color.GREEN);
        circleWidth = typedArray.getDimensionPixelSize(R.styleable.CustomerProgressBar_circleWidth, 10);
        speed = typedArray.getInt(R.styleable.CustomerProgressBar_speed, 20);
        typedArray.recycle();

        mPaint = new Paint();
        /*new Thread() {
            public void run() {
                while (true) {
                    progress++;
                    if (progress == 360) {
                        progress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();*/

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - circleWidth / 2;// 半径
        mPaint.setStrokeWidth(circleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(firstColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(secondColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, progress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(secondColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(firstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, progress, false, mPaint); // 根据进度画圆弧
        }
    }
}
