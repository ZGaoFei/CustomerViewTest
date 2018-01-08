package cn.com.nggirl.bigimagetestapp.path;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.nggirl.bigimagetestapp.R;

public class CircleArrowActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, CircleArrowActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_arrow);
    }
}
