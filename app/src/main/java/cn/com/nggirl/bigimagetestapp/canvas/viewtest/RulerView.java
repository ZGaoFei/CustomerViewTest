package cn.com.nggirl.bigimagetestapp.canvas.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaogaofei on 2017/10/31.
 */

public class RulerView extends View{
    private Context mContext;

    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setColor(Color.GRAY);
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

        drawRect(canvas);
        drawText(canvas);
        drawLine(canvas);
        drawShortLine(canvas);
    }

    private void drawRect(Canvas canvas) {
        canvas.drawRect(20, 20, 880, 250, mPaint);
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        mPaint.setTextSize(28);
        for (int i = 0; i < 10; i++) {
            canvas.drawText(String.valueOf(i), 84, 60, mPaint);
            canvas.translate(80, 0);
        }
    }

    private void drawLine(Canvas canvas) {
        canvas.restore();
        canvas.save();
        for (int i = 0; i < 10; i++) {
            canvas.translate(80, 0);
            canvas.drawLine(20, 200, 20, 250, mPaint);
        }
    }

    private void drawShortLine(Canvas canvas) {
        canvas.restore();
        canvas.translate(80, 0);
        for (int i = 1; i < 90; i++) {
            canvas.translate(8f, 0);
            if (i % 10 == 0) {
            } else if (i % 5 == 0 && i % 10 != 0) {
                canvas.drawLine(20, 220, 20, 250, mPaint);
            } else {
                canvas.drawLine(20, 240, 20, 250, mPaint);
            }
        }
    }
}
