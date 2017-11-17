package cn.com.nggirl.bigimagetestapp.textview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.textview.view.ExpandableTextView;

public class TextActivity extends AppCompatActivity {
    private boolean type = true;

    private static final String CONTENT = "你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好";
    private static final String CONTENTS = "你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好";
    private static final String CONTENTSS = "NineOldAndroids is deprecated. No new development will be taking place. Existing versions will (of course) continue to function. New applications should use minSdkVersion=\"14\" or higher which has access to the platform animation APIs.\n" +
            "\n" +
            "Thanks for all your support!";

    private TextView mTextView;

    private ExpandableTextView eptv;
    private TextView tv;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TextActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        initView();
        checkLine();
        checkLines();
        measureTextViewHeight();
    }

    private void initView() {
        final TextView textView = (TextView) findViewById(R.id.tv_text_test);
        Button button = (Button) findViewById(R.id.bt_text_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type) {
                    textView.setMaxLines(Integer.MAX_VALUE);
                } else {
                    textView.setMaxLines(3);
                }
                type = !type;
            }
        });

        ExpandTextView expandTextView = (ExpandTextView) findViewById(R.id.expand_text_view);
        expandTextView.setContentText("hello world");

        mTextView = (TextView) findViewById(R.id.tv_default_text);

        eptv = (ExpandableTextView) findViewById(R.id.expand_text_view_box);
        eptv.setText(CONTENTSS);
        eptv.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Log.e("====", "=====" + isExpanded);
            }
        });
    }

    private void checkLine() {
        TextView textView = new TextView(this);
        textView.setTextSize(sp2px(this, 17));// sp to px
        int width = (int) (getWidth(this) - dpToPx(this, 15) * 2);

        TextPaint mTextPaint = textView.getPaint();
        mTextPaint.setTextSize(sp2px(this, 17));
        int mTextViewWidth = (int) mTextPaint.measureText(CONTENTSS);

        int i = mTextViewWidth / width;

        float v = dpToPx(this, 15);
        int i1 = sp2px(this, 17);
        int width2 = getWidth(this);
        Log.e("+===", "==15dp==" + v);
        Log.e("====", "==17sp==" + i1);
        Log.e("====", "==width2==" + width2);

        Log.e("====", "==mTextViewWidth==" + mTextViewWidth);
        Log.e("====", "==width==" + width);
        Log.e("====", "=i===" + i);

        mTextView.setText(CONTENTSS);
        int lineCount = mTextView.getLineCount();
        Log.e("=====", "==lineCount==" + lineCount);
        float measureText = mTextView.getPaint().measureText(CONTENTSS);
        int width1 = mTextView.getWidth();
        Log.e("====", "==measureText==" + measureText);
        Log.e("====", "==width1==" + width1);
    }

    private void measureTextViewHeight() {
        TextView textView = new TextView(this);
        textView.setText(CONTENTSS);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);

        int screenWidth = (int) (getWidth(this) - dpToPx(this, 15) * 2);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);

        /*int measuredHeight = textView.getMeasuredHeight();
        Log.e("====", "==viewHeight==" + measuredHeight);

        int width = textView.getHeight();
        int measuredWidth = textView.getMeasuredHeight();
        Log.e("====", "==width==" + width);
        Log.e("====", "==measuredWidth==" + measuredWidth);

        int lineHeight = textView.getLineHeight();
        Log.e("====", "==lineHeight==" + lineHeight);*/

        int lineCount = textView.getLineCount();
        Log.e("====", "===lineCount=" + lineCount);

    }

    private void checkLines() {
        final TextView textView = new TextView(this);
        textView.setTextSize(sp2px(this, 17));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int)dpToPx(this, 15), 0, (int)dpToPx(this, 15), 0);
        textView.setLayoutParams(layoutParams);
        textView.setText(CONTENTS);

        textView.post(new Runnable() {
            @Override
            public void run() {
                int lines = textView.getLineCount();
                Log.e("====", "=lines===" + lines);
            }
        });
        int lineCount = textView.getLineCount();
        Log.e("====", "=lineCount===" + lineCount);
    }

    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public int getWidth(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
