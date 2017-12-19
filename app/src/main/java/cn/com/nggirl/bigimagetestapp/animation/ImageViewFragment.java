package cn.com.nggirl.bigimagetestapp.animation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.com.nggirl.bigimagetestapp.R;

public class ImageViewFragment extends Fragment {

    private int position;

    public static ImageViewFragment newInstance(int position) {
        ImageViewFragment fragment = new ImageViewFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_view, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_animation);
        ViewCompat.setTransitionName(imageView, "position:"/* + position*/);

        return view;
    }

}
