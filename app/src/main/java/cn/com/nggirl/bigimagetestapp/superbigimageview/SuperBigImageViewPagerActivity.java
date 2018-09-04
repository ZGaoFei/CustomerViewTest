package cn.com.nggirl.bigimagetestapp.superbigimageview;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.superbigimageview.lib.ImageSource;
import cn.com.nggirl.bigimagetestapp.superbigimageview.lib.SubsamplingScaleImageView;

/**
 * 不同的图片展示不同的效果
 *
 * 仿照微博大长图展示效果
 */
public class SuperBigImageViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private List<String> mImageUrls;
    private List<View> mViews;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SuperBigImageViewPagerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_big_image_view_pager);

        initData();
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager_big_image);
        viewPager.setAdapter(new ViewPagerAdapter(mViews));
    }

    private void initData() {
        mImageUrls = new ArrayList<>();
        mImageUrls.add("sanmartino.jpg");
        mImageUrls.add("swissroad.jpg");
        mImageUrls.add("card.png");
        mImageUrls.add("default1.png");
        mImageUrls.add("image1.jpg");
        mImageUrls.add("image2.jpg");
        mImageUrls.add("image3.jpg");
        mImageUrls.add("image4.jpg");
        mImageUrls.add("image.jpg");

        mViews = new ArrayList<>();
        for (int i = 0; i < mImageUrls.size(); i++) {
            mViews.add(getView(mImageUrls.get(i)));
        }
    }

    private View getView(String imageUrl) {
        final View view = LayoutInflater.from(this).inflate(R.layout.big_image_item, null);
        final SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) view.findViewById(R.id.sub_image_view_item);

        boolean type = setScaleType(imageUrl);
        Log.e("zgf", "===type====" + type);
        if (type) {
            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_START);
        } else {
            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE);
        }

        imageView.setImage(ImageSource.asset(imageUrl));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return view;
    }

    private boolean setScaleType(String imageUrl) {
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();
        Log.e("zgf", "==screenWidth====" + screenWidth + "===screenHeight==" + screenHeight);
        int[] params = getImageFromAssetsFile(imageUrl);
        int width = params[0];
        int height = params[1];
        Log.e("zgf", "===bitmap====" + width + "=====" + height);

        float screenRatio = (float)screenWidth / (float) screenHeight;
        float ratio = (float)width / (float) height;

        if (width < height && height > screenHeight && ratio < screenRatio) {
            return true;
        }
        return false;
    }

    private int[] getImageFromAssetsFile(String fileName) {
        int[] params = new int[2];
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        params[0] = image.getWidth();
        params[1] = image.getHeight();
        image.recycle();
        return params;
    }

    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    static class ViewPagerAdapter extends PagerAdapter {
        private List<View> mList;

        public ViewPagerAdapter(List<View> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

    }
}
