package cn.com.nggirl.bigimagetestapp.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.com.nggirl.bigimagetestapp.R;

public class ViewScaleFragment extends Fragment {
    private View scaleView;
    private Button button;

    public static ViewScaleFragment newInstance() {
        ViewScaleFragment fragment = new ViewScaleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_scale, container, false);

        scaleView = view.findViewById(R.id.view_scale);
        button = (Button) view.findViewById(R.id.view_scale_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scale();
            }
        });

        return view;
    }

    private void scale() {
//        ScaleAnimation animation = new ScaleAnimation(
//                1.0f, 1.0f, 1.0f, 0.0f,
//                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f
//        );
//        animation.setDuration(1000);
//        animation.setInterpolator(new AccelerateInterpolator());
//        scaleView.startAnimation(animation);


        ObjectAnimator animator = ObjectAnimator.ofFloat(scaleView, "scaleY", 1f, 0f);
        scaleView.setPivotX(0);
        scaleView.setPivotY(scaleView.getHeight());
        animator.setDuration(5000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                int percentage = (int) (value * 100);
                button.setText(percentage + "%");

            }
        });
        animator.start();
    }

}
