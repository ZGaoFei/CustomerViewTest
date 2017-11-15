package cn.com.nggirl.bigimagetestapp.textview;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.nggirl.bigimagetestapp.R;

public class ExpandTextView extends LinearLayout implements View.OnClickListener {
    private Context mContext;

    private TextView mTvContent;
    private TextView mTvFold;

    private int mWidth;

    private String mContent;
    private boolean mType = false;// false:fold, true:expand

    public ExpandTextView(Context context) {
        this(context, null);
    }

    public ExpandTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.expand_text_view, null);
        mTvContent = (TextView) view.findViewById(R.id.tv_content_expand_or_fold);
        mTvFold = (TextView) view.findViewById(R.id.tv_expand_or_fold);
        mTvFold.setOnClickListener(this);
        addView(view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = mTvContent.getWidth();
        mWidth = mTvContent.getMeasuredWidth();
        Log.e("==1==", "====" + width);
        Log.e("==2==", "====" + mWidth);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        checkFold();
    }

    public void setContentText(String string) {
        if (TextUtils.isEmpty(string)) {
            return;
        }
        mContent = string;

        mTvContent.setText(mContent);

//        invalidate();
        requestLayout();

//        checkFold();
    }

    public void setBtContent(String string) {
        if (TextUtils.isEmpty(string)) {
            return;
        }
        mTvFold.setText(string);
    }

    /*public void setType(boolean type) {
        mType = type;
    }*/

    @Override
    public void onClick(View v) {
        if (mType) {
            mTvContent.setMaxLines(Integer.MAX_VALUE);
            mTvFold.setText("折叠");
        } else {
            mTvContent.setMaxLines(2);
            mTvFold.setText("展开");
        }
        mType = !mType;
    }

    private void checkFold() {
        TextPaint mTextPaint = mTvContent.getPaint();
        mTextPaint.setTextSize(mTvContent.getTextSize());
        int mTextViewWidth = (int) mTextPaint.measureText(mContent);
        int width = mWidth * 2;
        Log.e("====", "====" + mTextViewWidth + "===" + width);
        if (mTextViewWidth > width) {
            mTvFold.setVisibility(VISIBLE);
            mTvFold.setText("展开");
            mType = true;
        } else {
            mTvFold.setVisibility(GONE);
            mType = false;
        }
    }

}
