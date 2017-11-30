package cn.com.nggirl.bigimagetestapp.path.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class RadarView extends View {
    private Paint paint;
    private Paint textPaint;
    private Paint valuesPaint;

    private int width;
    private int height;

    private int count = 7;
    private float angle = (float) (Math.PI * 2 / count);
    private float radius;

    private String[] titles = {"a", "b", "c", "d", "e", "f", "g"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20, 50};
    private float maxValue = 100;

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setStrokeWidth(5);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);

        valuesPaint = new Paint();
        valuesPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuesPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;
        width = w / 2;
        height = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(width, height);
            float x = (float) (width + radius * Math.cos(angle * i));
            float y = (float) (height + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, paint);
        }
    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int i = 1; i < count; i++) {//中心点不用绘制
            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(width + curR, height);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (width + curR * Math.cos(angle * j));
                    float y = (float) (height + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, paint);
        }
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (width + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (height + (radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第3象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuesPaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (width + radius * Math.cos(angle * i) * percent);
            float y = (float) (height + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, height);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuesPaint);
        }
        valuesPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuesPaint);
        valuesPaint.setAlpha(127);
        //绘制填充区域
        valuesPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuesPaint);
    }

    //======set======
    public void setCount(int count) {
        this.count = count;
        this.angle = (float) (Math.PI * 2 / count);
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void update() {
        invalidate();
    }

    public void setPaintClolr(int color) {
        paint.setColor(color);
    }

    public void setTextPaintColor(int color) {
        textPaint.setColor(color);
    }

    public void setValuesPaintColor(int color) {
        valuesPaint.setColor(color);
    }
}
