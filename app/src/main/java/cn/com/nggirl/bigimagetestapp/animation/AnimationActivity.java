package cn.com.nggirl.bigimagetestapp.animation;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import cn.com.nggirl.bigimagetestapp.R;

public class AnimationActivity extends AppCompatActivity {

    public static void start(Activity context) {
//        context.startActivity(new Intent(context, AnimationActivity.class));

        Intent intent = new Intent(context, AnimationActivity.class);
        ActivityCompat.startActivity(context, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(context).toBundle());
    }

    public static void start(Context context, View view) {
        ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);

        ActivityCompat.startActivity(context,
                new Intent(context, AnimationActivity.class), compat.toBundle());
    }

    public static void start(Activity context, View view, int position) {
        ActivityOptionsCompat options5 = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, "options:"/* + position*/);
        ActivityCompat.startActivity(context, new Intent(context, AnimationActivity.class), options5.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_animation);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
        }*/

        /*ImageView imageView = (ImageView) findViewById(R.id.image_view_animation);
        ViewCompat.setTransitionName(imageView, "options5");*/

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        List<Fragment> list = new ArrayList<>();
        list.add(ViewScaleFragment.newInstance());
        list.add(ImageViewFragment.newInstance(1));
        list.add(ImageViewFragment.newInstance(2));
        list.add(ImageViewFragment.newInstance(3));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(AnimationActivity.this);
    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> list;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
