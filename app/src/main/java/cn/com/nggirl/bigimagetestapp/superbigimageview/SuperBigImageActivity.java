package cn.com.nggirl.bigimagetestapp.superbigimageview;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.superbigimageview.lib.ImageSource;
import cn.com.nggirl.bigimagetestapp.superbigimageview.lib.SubsamplingScaleImageView;

/**
 * 判断大长图是宽大于高还是高大于宽
 * 显示效果，如果宽大于高则一屏居中显示
 * 如果高大于宽则宽占满全屏，高度从头开始展示，可滑动
 *
 * imageView.setImage(ImageSource.asset("image4.jpg").region(new Rect(100, 0, 620, 1080))); // 设置图片显示的区域，从图片中截取一部分显示
 * imageView.setImage(ImageSource); // 只能加载本地图片
 * imageView.setScaleAndCenter(1.5f, new PointF(0, 0)); // 从某个位置开始方法1.5倍显示
 * imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_**); // 类似于ImageView的ScaleType
 * imageView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_**); // 跟放大和缩小的动画有关
 * imageView.setDoubleTapZoomDuration(100); // 设置缩放动画时间
 * imageView.recycle(); // 回收资源
 * imageView.setOrientation(90); // 设置旋转角度
 */
public class SuperBigImageActivity extends AppCompatActivity {
    private SubsamplingScaleImageView imageView;

    private int i = 0;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SuperBigImageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_big_image);

        initView();
        initData();
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.change_image_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
//                change1();
                i++;
            }
        });

        imageView = (SubsamplingScaleImageView) findViewById(R.id.sub_image_view);

//        imageView.setScaleAndCenter(1.5f, new PointF(0, 0));
//        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE);
        imageView.setImage(ImageSource.asset("image4.jpg").tilingEnabled());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.recycle();
                finish();
            }
        });
    }

    private void initData() {

    }

    public void change() {
        imageView.recycle();
        if (i == 0) {
            imageView.setImage(ImageSource.asset("sanmartino.jpg"));
        } else if (i == 1) {
            imageView.setImage(ImageSource.asset("swissroad.jpg"));
        } else if (i == 2) {
            imageView.setImage(ImageSource.asset("card.png"));
        } else if (i == 3) {
            imageView.setImage(ImageSource.asset("default1.png"));
        } else if (i == 4) {
            imageView.setImage(ImageSource.asset("image1.jpg"));
        } else if (i == 5) {
            imageView.setImage(ImageSource.asset("image2.jpg"));
        } else if (i == 6) {
            imageView.setImage(ImageSource.asset("image3.jpg"));
        } else if (i == 7) {
            imageView.setImage(ImageSource.asset("image4.jpg"));
            imageView.setScaleAndCenter(1.5f, new PointF(0, 0));
        } else if (i == 8) {
            imageView.setImage(ImageSource.asset("image.jpg"));
        } else if (i == 9) {
            imageView.setImage(ImageSource.resource(R.drawable.images));
        } else if (i == 10) {
            imageView.setImage(ImageSource.resource(R.mipmap.ic_launcher));
        } else {
            i = 0;
            imageView.setImage(ImageSource.asset("sanmartino.jpg"));
        }
//        imageView.setScaleAndCenter(1.5f, new PointF(0, 0));
    }

    /**
     * 设置展示，同ImageView
     */
    private void changeMinimumScaleType() {
        if (i == 0) {
            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 1) {
            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_START);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 2) {
            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 3) {
            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        }
    }

    /**
     * 设置放大缩小限制
     */
    private void changePanLimit() {
        if (i == 0) {
            imageView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_CENTER);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 1) {
            imageView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_INSIDE);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 2) {
            imageView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        }
    }

    private void changeDoubleTap() {
        if (i == 0) {
            imageView.setDoubleTapZoomDpi(10);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 1) {
            imageView.setDoubleTapZoomDuration(100); // 设置缩放动画时间
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 2) {
            imageView.setDoubleTapZoomScale(SubsamplingScaleImageView.ORIGIN_DOUBLE_TAP_ZOOM);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        } else if (i == 3) {
            imageView.setDoubleTapZoomStyle(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER);
            imageView.setImage(ImageSource.asset("image4.jpg"));
        }
    }

    private void change1() {
        imageView.setImage(ImageSource.asset("image4.jpg").region(new Rect(100, 0, 620, 1080)));
    }

    private void recycle() {
        imageView.recycle();
    }

    private void set() {
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_START);
    }

}
