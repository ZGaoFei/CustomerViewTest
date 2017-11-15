package cn.com.nggirl.bigimagetestapp.textview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.com.nggirl.bigimagetestapp.R;

public class TextActivity extends AppCompatActivity {
    private boolean type = true;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TextActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        initView();
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
    }
}
