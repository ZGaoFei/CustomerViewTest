package cn.com.nggirl.bigimagetestapp.bigimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import cn.com.nggirl.bigimagetestapp.R;

/**
 * Created by zgfei on 2017/7/13.
 */

public class ImageUtils {
    Context context;

    public ImageUtils(Context context) {
        this.context = context;
    }

    private BitmapFactory.Options option() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.drawable.image, options);
        return options;
    }

    /**
     * 返回需要的压缩比
     *
     * @param width 需要的宽
     * @param height 需要的高
     */
    private int two(BitmapFactory.Options option, int width, int height) {
        int outWidth = option.outWidth;
        int outHeight = option.outHeight;
        Log.e("====", "==outWidth===" + outWidth + "=outHeight==" + outHeight);

        int inSampleSize = 1;

        if(outWidth > width || outHeight > height) {
            int wRatio = Math.round(outWidth / width);
            int hRatio = Math.round(outHeight / height);

            inSampleSize = wRatio > hRatio ? hRatio : wRatio;// 选择小的
        }

        return inSampleSize;
    }

    /**
     * 根据压缩比进行压缩
     *
     * @param width
     * @param height
     * @return
     */
    public Bitmap three(int width, int height) {
        BitmapFactory.Options options = option();
        options.inSampleSize = two(options, width, height);
        Log.e("====", "=====" + options.inSampleSize);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.image, options);
    }
}
