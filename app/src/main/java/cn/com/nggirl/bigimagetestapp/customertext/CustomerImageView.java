package cn.com.nggirl.bigimagetestapp.customertext;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import cn.com.nggirl.bigimagetestapp.R;

public class CustomerImageView extends View {
    private static final int IMAGE_SCALE_FITXY = 0;

    private String textStr;
    private int textColor;
    private int textSize;
    private Bitmap bitmap;
    private int imageScaleType;

    private Paint mPaint;
    private Rect mRect;// 字体
    private Rect rect;

    private int mWidth;
    private int mHeight;

    public CustomerImageView(Context context) {
        this(context, null);
    }

    public CustomerImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomerImageView, defStyleAttr, 0);
        textStr = typedArray.getString(R.styleable.CustomerImageView_text);
        textColor = typedArray.getColor(R.styleable.CustomerImageView_color, Color.BLACK);
        textSize = typedArray.getDimensionPixelSize(R.styleable.CustomerImageView_size, 16);
        int resourceId = typedArray.getResourceId(R.styleable.CustomerImageView_image, 0);
        bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        imageScaleType = typedArray.getInt(R.styleable.CustomerImageView_imageScaleType, 0);

        typedArray.recycle();

        mPaint = new Paint();
        mRect = new Rect();
        rect = new Rect();

        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
        mPaint.getTextBounds(textStr, 0, textStr.length(), mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            // 字体宽度
            int textWidth = getPaddingLeft() + getPaddingRight() + mRect.width();
            // 图片宽度
            int imageWidth = getPaddingLeft() + getPaddingRight() + bitmap.getWidth();

            if (widthMode == MeasureSpec.AT_MOST) {
                int max = Math.max(textWidth, imageWidth);
                mWidth = Math.min(max, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            int height = getPaddingTop() + getPaddingBottom() + mRect.height() + bitmap.getHeight();
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(height, heightSize);
            }
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        // 画边框
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        rect.left = getPaddingLeft();
        rect.top = getPaddingTop();
        rect.right = mWidth - getPaddingRight();
        rect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(textColor);
        mPaint.setStyle(Paint.Style.FILL);

        // 字体长度大于设置长度时，字体显示为...
        if (mRect.width() > mWidth) {
            TextPaint textPaint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(textStr, textPaint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {// 正常情况下，居中显示
            canvas.drawText(textStr, mWidth / 2 - mRect.width() / 2, mHeight - getPaddingBottom(), mPaint);
        }

        rect.bottom -= mRect.height();

        if (imageScaleType == IMAGE_SCALE_FITXY) {
            canvas.drawBitmap(bitmap, null, rect, mPaint);
        } else {
            //计算居中的矩形范围
            rect.left = mWidth / 2 - bitmap.getWidth() / 2;
            rect.right = mWidth / 2 + bitmap.getWidth() / 2;
            rect.top = (mHeight - mRect.height()) / 2 - bitmap.getHeight() / 2;
            rect.bottom = (mHeight - mRect.height()) / 2 + bitmap.getHeight() / 2;
            canvas.drawBitmap(bitmap, null, rect, mPaint);
        }

    }

    public void setSrc(Bitmap bitmap) {
        this.bitmap = bitmap;

        invalidate();
    }
}
