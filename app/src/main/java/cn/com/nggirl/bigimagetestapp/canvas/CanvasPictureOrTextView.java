package cn.com.nggirl.bigimagetestapp.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.com.nggirl.bigimagetestapp.R;

/**
 * 使用Canvas绘制图片和文字
 */

public class CanvasPictureOrTextView extends View {
    private Picture picture;

    private Paint paint;

    public CanvasPictureOrTextView(Context context) {
        super(context);
        init();
    }

    public CanvasPictureOrTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasPictureOrTextView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        picture = new Picture();
        paint = new Paint();
//        recording();
    }

    // failure
    private void recording() {
        Canvas canvas = picture.beginRecording(500, 500);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 100, 50, paint);
        picture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        picture.draw(canvas);
//        canvas.drawPicture(picture);
//        canvas.drawPicture(picture, new RectF(0, 0, picture.getWidth(), 200));

//        drawBitmap(canvas);
//        drawHalfBitmap(canvas);
        drawText(canvas);
        drawText2(canvas);
        drawPostText(canvas);
    }

    private void drawBitmap(Canvas canvas) {
        // bitmap resource can from file or stream or res or assets
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        canvas.drawBitmap(bitmap, 200, 300, new Paint());
    }

    private void drawHalfBitmap(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        // in (0, 0, 200, 300) show 1/4 of bitmap
        Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);// bitmap rect
        Rect dst = new Rect(0, 0, 200, 300);// show rect

        canvas.drawBitmap(bitmap, src, dst, new Paint());
    }

    private void drawText(Canvas canvas) {
        String text = "ABCDEFG";

        paint.setTextSize(36);
        paint.setColor(Color.RED);

        canvas.drawText(text, 200, 200, paint);
    }

    private void drawText2(Canvas canvas) {
        char[] text = new char[]{'1','2','3','4','5','6','7','8','9','0'};
        paint.reset();
        paint.setColor(Color.BLUE);
        paint.setTextSize(40);

        canvas.drawText(text, 2, 7, 300, 300, paint);

    }

    private void drawPostText(Canvas canvas) {
        String text = "1234567890";
        canvas.drawPosText(text, new float[]{
                330, 330,
                360, 360,
                390, 390,
                400, 340,
                430, 370,
                360, 360,
                370, 370,
                380, 380,
                390, 390,
                400, 400
        }, paint);
    }

}
