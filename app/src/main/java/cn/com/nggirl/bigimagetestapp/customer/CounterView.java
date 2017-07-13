package cn.com.nggirl.bigimagetestapp.customer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zgfei on 2017/7/13.
 */

public class CounterView extends View implements View.OnClickListener {
    private Paint mPaint;
    private Rect mRect;
    private int mCount;


    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        mCount = 0;

        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制矩形
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        // 绘制数字
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);
        String string = String.valueOf(mCount);
        mPaint.getTextBounds(string, 0, string.length(), mRect);

        int width = mRect.width();
        int height = mRect.height();
        canvas.drawText(string, getWidth() / 2 - width / 2, getHeight() / 2 + height / 2, mPaint);

    }

    @Override
    public void onClick(View v) {
        mCount ++;
        invalidate();
    }
}
