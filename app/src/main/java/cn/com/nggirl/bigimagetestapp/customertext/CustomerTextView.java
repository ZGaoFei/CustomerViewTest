package cn.com.nggirl.bigimagetestapp.customertext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cn.com.nggirl.bigimagetestapp.R;


public class CustomerTextView extends View implements View.OnClickListener {

    private String textStr;
    private int textColor;
    private int textSize;

    private Paint mPaint;
    private Rect mRect;


    public CustomerTextView(Context context) {
        this(context, null);
    }

    public CustomerTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomerTextView, defStyleAttr, 0);
        /*int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.CustomerTextView_text:
                    textStr = typedArray.getString(index);
                    break;
                case R.styleable.CustomerTextView_color:
                    textColor = typedArray.getColor(index, Color.BLACK);
                    break;
                case R.styleable.CustomerTextView_size:
                    // 默认为16
                    textSize = (int) typedArray.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }*/

        // 与上面对等
        textStr = typedArray.getString(R.styleable.CustomerTextView_text);
        textColor = typedArray.getColor(R.styleable.CustomerTextView_color, Color.BLACK);
        textSize = typedArray.getDimensionPixelSize(R.styleable.CustomerTextView_size, 16);

        typedArray.recycle();

        mPaint = new Paint();
        mRect = new Rect();
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
        mPaint.getTextBounds(textStr, 0, textStr.length(), mRect);

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width;
        int height;
        // 分别判断宽和高
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(textStr, 0, textStr.length(), mRect);
            int rectWidth = mRect.width();
            // 计算实际宽度，只添加 padding，因为 margin 不会计算到布局里面
            width = getPaddingLeft() + rectWidth + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(textStr, 0, textStr.length(), mRect);
            int rectHeight = mRect.height();
            height = getPaddingTop() + rectHeight + getPaddingBottom();
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(Color.BLACK);

        // 确定绘制的起点，即左上角的位置
        canvas.drawText(textStr, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        textStr = getText();
        // 在文字为三位时，点击改变里面的数字位数为4，布局不会重新测量长度，即不会走 onMeasure() 和 onLayout() 方法
//        invalidate();
//        postInvalidate();

        // 强制要求重新走完三个方法，当位数改变时也会自适应
        requestLayout();
    }

    private String getText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
