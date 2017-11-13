package cn.com.nggirl.bigimagetestapp.bigimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import cn.com.nggirl.bigimagetestapp.R;

import static android.os.Environment.getExternalStorageDirectory;

public class BigImageActivity extends AppCompatActivity {
    private static final String imageUrl = getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "Pictures/image.jpg";

    private ImageView imageView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, BigImageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        testMemory();
        testFile();

//        one();
        initView();
    }

    private void initView() {
        // 原图
        ImageView image = (ImageView) findViewById(R.id.iv_big_image);
//        image.setImageResource(R.drawable.image);

        // 压缩后
        imageView = (ImageView) findViewById(R.id.iv_big_image2);
        clipImage();

//        Bitmap three = ImageUtils.three(this, R.drawable.image, 600, 400);
//        Log.e("==111==", "====");
//        InputStream inputStream = ImageUtils.compressImage2(three);
//        three.recycle();
//        Log.e("==222==", "====");
//        String imagesss = ImageUtils.writeResponseBodyToDisk(inputStream, "imagesss");
//        Log.e("====", "====" +imagesss);
//        Bitmap bitmap = BitmapFactory.decodeFile(imagesss);
//        Log.e("====", "====" + (bitmap == null));
//        image2.setImageBitmap(bitmap);

    }

    private void testMemory() {
        int memory = (int) (Runtime.getRuntime().maxMemory()) / 1024 / 1024;
        Log.e("===允许的最大内存==", "======" + memory + "M");
    }

    private void testFile() {
        File file = new File(imageUrl);
        Log.e("=====", "=====" + file.exists() + "===" + file.getAbsolutePath());
    }

    /**
     * 获取图片的基本信息
     *
     * 预估一下加载整张图片所需占用的内存
     * 为了加载这一张图片你所愿意提供多少内存
     * 用于展示这张图片的控件的实际大小
     * 当前设备的屏幕尺寸和分辨率
     */
    private void one() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

//        options.inPreferredConfig = Bitmap.Config.RGB_565;

//        BitmapFactory.decodeFile(new File(imageUrl).getAbsolutePath(), options);// 本地
        BitmapFactory.decodeResource(getResources(), R.drawable.image, options);// 资源文件
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        String outMimeType = options.outMimeType;

        Log.e("====", "==outWidth==" + outWidth + "=outHeight=" + outHeight + "=outMimeType=" + outMimeType);
    }

    private BitmapFactory.Options option() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.image, options);
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
    private Bitmap three(int width, int height) {
        BitmapFactory.Options options = option();
        options.inSampleSize = two(options, width, height);
        Log.e("==three==", "===inSampleSize==" + options.inSampleSize);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), R.drawable.image, options);
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public Bitmap compressImage(Bitmap image) {
        Log.e("=====", "=====");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }

        Log.e("====", "====" + (baos.toByteArray().length / 1024));
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null);
    }


    private ClipHandler clipHandler = new ClipHandler(new ClipImageListener() {
        @Override
        public void onSuccess(Message msg) {
            String url = (String) msg.obj;
            Log.e("==onSuccess==", "==url==" + url);
            Bitmap bitmap = BitmapFactory.decodeFile(url);
            imageView.setImageBitmap(bitmap);
        }
    });

    private void clipImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap three = three(600, 400);
                InputStream inputStream = ImageUtils.compressImage2(three);
                Log.e("==222==", "====");
                String imagesss = ImageUtils.writeResponseBodyToDisk(inputStream, "imagesss");
                Log.e("==clipImage==", "==imagesss==" + imagesss);

                Message obtain = Message.obtain();
                obtain.obj = imagesss;
                clipHandler.sendMessage(obtain);
            }
        }).start();
    }

    private static class ClipHandler extends Handler {
        ClipImageListener listener;

        ClipHandler(ClipImageListener listener) {
            this.listener = listener;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            listener.onSuccess(msg);
        }
    }

    interface ClipImageListener{
        void onSuccess(Message msg);
    }

}
