package cn.com.nggirl.bigimagetestapp.canvas.viewtest;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.com.nggirl.bigimagetestapp.R;

public class CanvasAnimationView extends View {
    private int width;
    private int height;
    private Bitmap bitmap;
    private Paint paint;
    private double mDecoration = 0;
    private int drawWidth = 0;
    private int bitmapWidth;
    private int bitmapHeight;
    private float scale;

    public CanvasAnimationView(Context context) {
        super(context);
        init();
    }

    public CanvasAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        drawBitmap(canvas);
        drawBitmap(canvas);
    }

    private void drawBitmap(Canvas canvas) {

        canvas.translate(width/2, height/2);
        canvas.drawCircle(0, 0, 200, paint);

        Rect src = new Rect(0, 0, (int) (bitmapWidth * scale), bitmapHeight);
        Rect dst = new Rect(-200, -200, (int) (bitmapWidth * scale), 200);

        canvas.drawBitmap(bitmap, src, dst, paint);
    }

    private AniHandler handler = new AniHandler(new Update() {
        @Override
        public void onUpdate() {
            invalidate();
        }});

    public void startAni(final int decoration) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (mDecoration <= decoration) {
                        handler.sendEmptyMessage(0);
                        Thread.sleep(100);
                        mDecoration += 100;
                        scale = (float) (mDecoration / decoration);
                        Log.e("====", "====" + scale);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class AniHandler extends Handler {
        Update update;
        AniHandler(Update update) {
            this.update = update;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            update.onUpdate();
        }
    }

    interface Update{
        void onUpdate();
    }
}
