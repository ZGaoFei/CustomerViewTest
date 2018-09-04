package cn.com.nggirl.bigimagetestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cn.com.nggirl.bigimagetestapp.animation.AnimationActivity;
import cn.com.nggirl.bigimagetestapp.bigimage.BigImageActivity;
import cn.com.nggirl.bigimagetestapp.canvas.CanvasActivity;
import cn.com.nggirl.bigimagetestapp.canvas.CanvasTestActivity;
import cn.com.nggirl.bigimagetestapp.clipimage.ClipImageActivity;
import cn.com.nggirl.bigimagetestapp.customer.CounterViewActivity;
import cn.com.nggirl.bigimagetestapp.customertext.CustomerViewActivity;
import cn.com.nggirl.bigimagetestapp.edittext.EditTextActivity;
import cn.com.nggirl.bigimagetestapp.imageandtext.ImageAndTextActivity;
import cn.com.nggirl.bigimagetestapp.mylistview.CustomerListViewActivity;
import cn.com.nggirl.bigimagetestapp.path.CircleArrowActivity;
import cn.com.nggirl.bigimagetestapp.path.PathTest2Activity;
import cn.com.nggirl.bigimagetestapp.path.PathTest3Activity;
import cn.com.nggirl.bigimagetestapp.path.PathTestActivity;
import cn.com.nggirl.bigimagetestapp.superbigimageview.SuperBigImageActivity;
import cn.com.nggirl.bigimagetestapp.superbigimageview.SuperBigImageViewPagerActivity;
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
        Button btTextPath2 = (Button) findViewById(R.id.bt_skip_path2_text);
        btTextPath2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PathTest2Activity.start(MainActivity.this);
            }
        });
        Button btTextPath3 = (Button) findViewById(R.id.bt_skip_path3_text);
        btTextPath3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PathTest3Activity.start(MainActivity.this);
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
        final Button animationTest = (Button) findViewById(R.id.bt_skip_animation_test);
        animationTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationActivity.start(MainActivity.this, animationTest);
            }
        });

        final ImageView imageView0 = (ImageView) findViewById(R.id.image_view0);
        imageView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationActivity.start(MainActivity.this, imageView0, 0);
            }
        });
        final ImageView imageView1 = (ImageView) findViewById(R.id.image_view1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationActivity.start(MainActivity.this, imageView1, 1);
            }
        });
        final ImageView imageView2 = (ImageView) findViewById(R.id.image_view2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationActivity.start(MainActivity.this, imageView2, 2);
            }
        });
        final ImageView imageView3 = (ImageView) findViewById(R.id.image_view3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationActivity.start(MainActivity.this, imageView3, 3);
            }
        });
        Button imageAndTextTest = (Button) findViewById(R.id.bt_skip_image_and_text);
        imageAndTextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageAndTextActivity.start(MainActivity.this);
            }
        });
        Button circleArrow = (Button) findViewById(R.id.bt_skip_circle_arrow_test);
        circleArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleArrowActivity.start(MainActivity.this);
            }
        });
        Button htmlText = (Button) findViewById(R.id.bt_skip_html_test);
        htmlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HtmlTextActivity.start(MainActivity.this);
            }
        });
        Button bigImageTest = (Button) findViewById(R.id.bt_skip_big_image);
        bigImageTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuperBigImageActivity.start(MainActivity.this);
            }
        });
        Button bigImageViewPagerTest = (Button) findViewById(R.id.bt_skip_big_image_view_pager);
        bigImageViewPagerTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuperBigImageViewPagerActivity.start(MainActivity.this);
            }
        });

    }

}
