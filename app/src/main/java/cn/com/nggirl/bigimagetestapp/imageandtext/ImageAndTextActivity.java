package cn.com.nggirl.bigimagetestapp.imageandtext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.com.nggirl.bigimagetestapp.R;

/**
 * 在图片的指定位置绘制相应的文字或者图片，并保存下来
 *
 * 将布局View转换为bitmap
 *
 */
public class ImageAndTextActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageView imageView;
    private LinearLayout linearLayout;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ImageAndTextActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_and_text);

        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edit_text_input);
        imageView = (ImageView) findViewById(R.id.image_view_input);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        Button button = (Button) findViewById(R.id.bt_start_draw);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = editText.getText().toString().trim();

        // 在bitmap上绘制文字
        /*Bitmap bitmap = drawText(text);
//        imageView.setImageBitmap(bitmap);
        saveBitmap(bitmap, "image.png");

        // 在bitmap上绘制bitmap
        Bitmap drawImage = drawImage(bitmap, R.mipmap.ic_launcher);
        saveBitmap(drawImage, "drawImage.png");

        Log.e("====", "====" + (bitmap == drawImage));
        bitmap.recycle();
        drawImage.recycle();*/

        // 生成bitmap
//        Bitmap bitmap1 = viewToBitmap(linearLayout, 500, 500);
//        Bitmap bitmap2 = convertViewToBitmap(linearLayout);
//        saveBitmap(bitmap1, "bitmap1.png");
//        saveBitmap(bitmap2, "bitmap2.png");

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image).copy(Bitmap.Config.RGB_565, true);
        Bitmap imageAndText = drawImageAndText(bitmap, text, R.mipmap.ic_launcher);
        saveBitmap(imageAndText, "bitmap0.png");

        bitmap.recycle();
        imageAndText.recycle();
    }

    private Bitmap drawText(String text) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image).copy(Bitmap.Config.RGB_565, true);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint();
        p.setColor(0xffff0000);
        p.setAntiAlias(true);
        p.setTextSize(25);

        int textW = (int) p.measureText(text);
        Paint.FontMetrics fm = p.getFontMetrics();
        int textH = (int) (fm.bottom - fm.top);
        Log.e("====", "====" + textW + "====" + textH);

        canvas.drawText(text, width / 2 - textW / 2, height / 2 - textH / 2 + 100, p);

        return bitmap;
    }

    private Bitmap drawImage(Bitmap bitmap, int res) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap resource = BitmapFactory.decodeResource(getResources(), res);
        int resWidth = resource.getWidth();
        int resHeight = resource.getHeight();

        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint();
        canvas.drawBitmap(resource, width / 2 - resWidth / 2, height / 2 - resHeight / 2, p);

        Log.e("====", "====" + resource.getWidth() + "====" + resource.getHeight());
        resource.recycle();

        return bitmap;
    }

    private Bitmap drawImageAndText(Bitmap bitmap, String text, int res) {
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint();
        p.setColor(0xffff0000);
        p.setAntiAlias(true);
        p.setTextSize(35);

        if (!TextUtils.isEmpty(text)) {
            int textW = (int) p.measureText(text);
            Paint.FontMetrics fm = p.getFontMetrics();
            int textH = (int) (fm.bottom - fm.top);
            canvas.drawText(text, width / 2 - textW / 2, height / 2 - textH / 2 + 100, p);
        }

        Bitmap resource = BitmapFactory.decodeResource(getResources(), res);
        if (resource != null) {
            int resWidth = resource.getWidth();
            int resHeight = resource.getHeight();

            p.reset();
            canvas.drawBitmap(resource, width / 2 - resWidth / 2, height / 2 - resHeight / 2, p);
            resource.recycle();
        }

        return bitmap;
    }

    private void saveBitmap(Bitmap bitmap, String name) {
        String path = Environment.getExternalStorageDirectory() + "/customertest/";
        File f = new File(path, name);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传递宽和高可能会导致布局绘制不完整
     *
     * @param view         view
     * @param bitmapWidth  bitmap width
     * @param bitmapHeight bitmap height
     * @return bitmap
     */
    private Bitmap viewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));

        return bitmap;
    }

    /**
     * 可以完整的绘制出bitmap
     */
    public Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

}
