package cn.com.nggirl.bigimagetestapp.clipimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.util.Locale;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.clipimage.utils.Luban;
import cn.com.nggirl.bigimagetestapp.clipimage.utils.OnCompressListener;

import static android.os.Environment.getExternalStorageDirectory;

public class ClipImageActivity extends AppCompatActivity {
    private static final String IMAGE_URL_BEFORE = getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "Pictures/image.jpg";
    private static final String IMAGE_URL_AFTER = getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "Pictures/";

    private ImageView imageView;

    public static void start(Context context) {
        Log.e("====", "====");
        context.startActivity(new Intent(context, ClipImageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_image);

        imageView = (ImageView) findViewById(R.id.iv_clip_image);

        initData();
    }

    private void initData() {
        Log.e("====", "====" + IMAGE_URL_BEFORE);
        File file = new File(IMAGE_URL_BEFORE);
        Log.e("====", "====" + file.exists());

        compressWithLs(IMAGE_URL_BEFORE);
    }

    private void compressWithLs(final String photo) {
        Luban.with(this)
                .load(photo)
                .setTargetDir(IMAGE_URL_AFTER)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        showResult(photo, file);

                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();
    }

    private void showResult(String photo, File file) {
        int[] originSize = computeSize(photo);
        int[] thumbSize = computeSize(file.getAbsolutePath());
        String originArg = String.format(Locale.CHINA, "原图参数：%d*%d, %dk", originSize[0], originSize[1], new File(photo).length() >> 10);
        String thumbArg = String.format(Locale.CHINA, "压缩后参数：%d*%d, %dk", thumbSize[0], thumbSize[1], file.length() >> 10);

        Log.e("====", "===before==" + originArg);
        Log.e("====", "===after==" + thumbArg);
    }

    private int[] computeSize(String srcImg) {
        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;

        BitmapFactory.decodeFile(srcImg, options);
        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }
}
