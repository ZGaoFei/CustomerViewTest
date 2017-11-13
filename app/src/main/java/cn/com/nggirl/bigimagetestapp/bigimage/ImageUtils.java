package cn.com.nggirl.bigimagetestapp.bigimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.com.nggirl.bigimagetestapp.R;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by zgfei on 2017/7/13.
 */

public class ImageUtils {
    Context context;

    public ImageUtils(Context context) {
        this.context = context;
    }

    private static BitmapFactory.Options option(Context context, int res) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), res, options);
        return options;
    }

    /**
     * 返回需要的压缩比
     *
     * @param width 需要的宽
     * @param height 需要的高
     */
    private static int two(BitmapFactory.Options option, int width, int height) {
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
    public static Bitmap three(Context context, int res, int width, int height) {
        BitmapFactory.Options options = option(context, res);
        options.inSampleSize = two(options, width, height);
        Log.e("====", "=====" + options.inSampleSize);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), res, options);
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
    }

    public static InputStream compressImage2(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 90;

        while (baos.toByteArray().length / 1024 > 512) {
            baos.reset(); // 重置baos即清空baos
            Log.e("==compressImage2==", "==options==" + options);
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

    /**
     * 输出bitmap的字节长度
     *
     * @param image
     * @return
     */
    public static int logBitmapSize(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int length = baos.toByteArray().length;
        return length;
    }

    public static Bitmap getBitmap(String imgPath) {
        // Get bitmap through image path
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = false;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        // Do not compress
        newOpts.inSampleSize = 1;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath, newOpts);
    }

    public static void compressBitmap(Bitmap bit) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = Integer.valueOf(50);
        bit.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight()
                + "bytes.length=  " + (bytes.length / 1024) + "KB"
                + "quality=" + quality);
    }

    public static void compress(Context context, String uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 不创建bitmap，只获取bitmap的属性值
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.image, options);
        String mimeType = options.outMimeType;
        int width = options.outWidth;
        int height = options.outHeight;

    }

    /**
     * 将图片保存到本地
     *
     * @param inputStream：输入流
     * @param fileName：文件名称
     * @return： 返回路径
     */
    public static String writeResponseBodyToDisk(InputStream inputStream, String fileName) {
        try {
            String imageUrl = getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "Pictures/image1.jpg";
            File futureStudioIconFile = new File(/*getExternalStorageDirectory() + File.separator + fileName*/imageUrl);

            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSizeDownloaded = 0;

                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                outputStream.flush();
                return futureStudioIconFile.getAbsolutePath();
            } catch (IOException e) {
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

}
