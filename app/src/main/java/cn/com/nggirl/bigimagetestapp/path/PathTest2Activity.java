package cn.com.nggirl.bigimagetestapp.path;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import cn.com.nggirl.bigimagetestapp.R;

public class PathTest2Activity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, PathTest2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_test2);

        final PathBezierCubicTestView cubicTestView = (PathBezierCubicTestView) findViewById(R.id.cubic_view);

        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_one:
                        cubicTestView.setPointIndex(0);
                        break;
                    case R.id.radio_button_two:
                        cubicTestView.setPointIndex(1);
                        break;
                }
            }
        });
    }
}
