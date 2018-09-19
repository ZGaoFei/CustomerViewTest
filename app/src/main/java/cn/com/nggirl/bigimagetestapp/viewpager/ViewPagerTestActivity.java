package cn.com.nggirl.bigimagetestapp.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.viewpager.widget.CopyViewPager;
import cn.com.nggirl.bigimagetestapp.viewpager.widget.ScaleTransformer;
import cn.com.nggirl.bigimagetestapp.viewpager.widget.StackTransformer;
import cn.com.nggirl.bigimagetestapp.viewpager.widget.VerticalViewPager;

public class ViewPagerTestActivity extends AppCompatActivity {
    private VerticalViewPager verticalViewPager;
    private ViewPager viewPager;
    private CopyViewPager copyVerticalViewPager;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ViewPagerTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_test);

        verticalViewPager = (VerticalViewPager) findViewById(R.id.vertical_view_pager);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        copyVerticalViewPager = (CopyViewPager) findViewById(R.id.copy_view_pager);

//        initView();
//        initView2();
        initView3();

    }

    private void initView() {
        verticalViewPager.setAdapter(initViewAdapter());

        verticalViewPager.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.GONE);
        copyVerticalViewPager.setVisibility(View.GONE);

        verticalViewPager.setPageTransformer(true, new StackTransformer());
    }

    /**
     * 设置同一屏幕显示多个item
     * 结合布局的clipchildren使用
     */
    private void initView2() {
        viewPager.setAdapter(initViewAdapter());
        verticalViewPager.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
        copyVerticalViewPager.setVisibility(View.GONE);

        viewPager.setPageMargin(80);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(false, new ScaleTransformer());
    }

    private void initView3() {
        copyVerticalViewPager.setAdapter(initViewAdapter());

        verticalViewPager.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        copyVerticalViewPager.setVisibility(View.VISIBLE);
    }

    private MyViewAdapter initViewAdapter () {
        List<View> views = new ArrayList<>();

        views.add(layoutView(0, Color.BLUE));
        views.add(layoutView(1, Color.RED));
        views.add(layoutView(2, Color.YELLOW));
        views.add(layoutView(3, Color.GRAY));
        views.add(layoutView(4, Color.CYAN));

        return new MyViewAdapter(views);
    }

    private View layoutView(int position, int color) {
        View view = LayoutInflater.from(this).inflate(R.layout.vertical_item_view, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_vertical_item);
        textView.setText(position + "pager");
        textView.setBackgroundColor(color);
        return view;
    }

    private static class MyViewAdapter extends PagerAdapter {
        private List<View> mList;

        public MyViewAdapter(List<View> list) {
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
