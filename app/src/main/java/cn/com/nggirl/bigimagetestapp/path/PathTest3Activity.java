package cn.com.nggirl.bigimagetestapp.path;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.path.viewtest.CircleView;

public class PathTest3Activity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, PathTest3Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_test3);

        final CircleView circleView = (CircleView) findViewById(R.id.circle_view);

        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group3);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_one3:
                        circleView.setPointIndex(0);
                        break;
                    case R.id.radio_button_two3:
                        circleView.setPointIndex(1);
                        break;
                }
            }
        });
    }
}
