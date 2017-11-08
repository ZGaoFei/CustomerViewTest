package cn.com.nggirl.bigimagetestapp.canvas.viewtest;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TimeView extends View {
    private Context mContext;

    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    private String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTime(canvas);

        drawText(canvas);
    }

    private void drawTime(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 200, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 8, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, 0, 100, 100, mPaint);
        canvas.drawLine(0, 0, -50, -80, mPaint);
        canvas.drawLine(0, 0, 150, -0, mPaint);

        mPaint.setColor(Color.GRAY);
        for (int i = 0; i < 60; i++) {
            if (i % 5 != 0) {
                canvas.drawLine(180, 0, 200, 0, mPaint);
            }
            canvas.rotate(6);
        }

        mPaint.setColor(Color.RED);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(170, 0, 200, 0, mPaint);
            canvas.rotate(30);
        }
    }

    private void drawText(Canvas canvas) {
        mPaint.setColor(Color.BLACK);

        for (int i = 0; i < 12; i++) {

            if (i <= 9) {
                canvas.drawText(String.valueOf(i + 3), 165, 8, mPaint);
                canvas.rotate(30);
            } else {
                canvas.drawText(String.valueOf(i - 9), 165, 8, mPaint);
                canvas.rotate(30);
            }
        }

    }
}
