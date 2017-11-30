package cn.com.nggirl.bigimagetestapp.path;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.path.viewtest.RadarView;

public class PathTestActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, PathTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_test);

        RadarView view = (RadarView) findViewById(R.id.radar_view);
        view.setTitles(new String[]{"1","2","3","4","5"/*,"6","7", "8"*/});
        view.setData(new double[]{90, 80, 90, 66, 70/*, 75, 92, 79*/});
        view.setCount(5);
        view.setMaxValue(100);
        view.update();
    }
}
