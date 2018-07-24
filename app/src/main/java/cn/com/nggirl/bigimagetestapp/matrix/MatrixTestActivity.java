package cn.com.nggirl.bigimagetestapp.matrix;

import android.os.Bundle;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.com.nggirl.bigimagetestapp.R;

public class MatrixTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_test);

        View.MeasureSpec spec;
        LinearLayout.LayoutParams params;
        ViewGroup.LayoutParams params1;
        Trace trace;
        ImageView imageView;

    }
}
