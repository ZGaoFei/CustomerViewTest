package cn.com.nggirl.bigimagetestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.com.nggirl.bigimagetestapp.bigimage.BigImageActivity;
import cn.com.nggirl.bigimagetestapp.canvas.CanvasActivity;
import cn.com.nggirl.bigimagetestapp.canvas.CanvasTestActivity;
import cn.com.nggirl.bigimagetestapp.clipimage.ClipImageActivity;
import cn.com.nggirl.bigimagetestapp.customer.CounterViewActivity;
import cn.com.nggirl.bigimagetestapp.customertext.CustomerViewActivity;
import cn.com.nggirl.bigimagetestapp.edittext.EditTextActivity;
import cn.com.nggirl.bigimagetestapp.mylistview.CustomerListViewActivity;
import cn.com.nggirl.bigimagetestapp.path.PathTestActivity;
import cn.com.nggirl.bigimagetestapp.textview.ListViewExpandActivity;
import cn.com.nggirl.bigimagetestapp.textview.TextActivity;
import cn.com.nggirl.bigimagetestapp.timepicker.TimePickerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button btOne = (Button) findViewById(R.id.bt_skip_one);
        btOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigImageActivity.start(MainActivity.this);
            }
        });

        Button btTwo = (Button) findViewById(R.id.bt_skip_two);
        btTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CounterViewActivity.start(MainActivity.this);
            }
        });

        Button btThree = (Button) findViewById(R.id.bt_skip_three);
        btThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerListViewActivity.start(MainActivity.this);
            }
        });

        Button btFour = (Button) findViewById(R.id.bt_skip_four);
        btFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerViewActivity.start(MainActivity.this);
            }
        });

        Button btCanvas = (Button) findViewById(R.id.bt_skip_canvas);
        btCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasActivity.start(MainActivity.this);
            }
        });

        Button btCanvasTest = (Button) findViewById(R.id.bt_skip_canvas_test);
        btCanvasTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasTestActivity.start(MainActivity.this);
            }
        });

        Button btClipTest = (Button) findViewById(R.id.bt_skip_clip);
        btClipTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipImageActivity.start(MainActivity.this);
            }
        });

        Button btTextTest = (Button) findViewById(R.id.bt_skip_text_test);
        btTextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextActivity.start(MainActivity.this);
            }
        });

        Button btTextTestExpand = (Button) findViewById(R.id.bt_skip_text_expand);
        btTextTestExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewExpandActivity.start(MainActivity.this);
            }
        });

        Button btTextPath = (Button) findViewById(R.id.bt_skip_path_text);
        btTextPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PathTestActivity.start(MainActivity.this);
            }
        });
        Button editTextTest = (Button) findViewById(R.id.bt_skip_edit_text);
        editTextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextActivity.start(MainActivity.this);
            }
        });
        Button timePickerTest = (Button) findViewById(R.id.bt_skip_time_picker);
        timePickerTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerActivity.start(MainActivity.this);
            }
        });

    }

}
