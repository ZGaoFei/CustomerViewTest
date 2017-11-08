package cn.com.nggirl.bigimagetestapp.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import cn.com.nggirl.bigimagetestapp.R;

public class CanvasTestView extends View {
    private Context mContext;

    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    // 在XML文件中调用此构造方法
    public CanvasTestView(Context context) {
        this(context, null);
    }

    // new CanvasTestView()调用此构造方法
    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.black));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvasTranslate(canvas);
//        canvasScale(canvas);
//        canvasScaleTest(canvas);
//        canvasRotate(canvas);
//        canvasRotateTest(canvas);
//        canvasSkew(canvas);
        canvasSaveOrRestore(canvas);
    }

    /**
     * translate方法
     * 表示移动画布，参照指标为上一次绘制的坐标点，可以叠加
     *
     *
     * @param canvas
     */
    private void canvasTranslate(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_33));
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_99));
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
    }

    /**
     * scale方法
     * 缩放
     * scale(float, float)缩放中心就是坐标轴
     *
     * scale(float, float, int, int)根据坐标轴向x/y轴进行平移后缩放
     *
     * 前两个参数是缩放比例，后两个参数是x/y轴的偏移量
     * 缩放比例如果小于0，则会分别按照x/y轴反转缩放
     *
     * 缩放可以叠加
     *
     * @param canvas
     */
    private void canvasScale(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rectF = new RectF(0, -100, 200, 0);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_33));
        canvas.drawRect(rectF, mPaint);

//        canvas.scale(0.5f, 0.5f);
//        canvas.scale(-0.5f, -0.5f);
//        canvas.scale(0.5f, 0.5f, 30, 40);
        canvas.scale(-0.5f, -0.5f, 30, 40);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_99));
        canvas.drawRect(rectF, mPaint);
    }

    private void canvasScaleTest(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rectF = new RectF(-200, -200, 200, 200);

        for (int i = 0; i < 20; i++) {
            canvas.drawRect(rectF, mPaint);
            canvas.scale(0.9f, 0.9f);
        }
    }

    /**
     * rotate方法，与scale类似
     *
     * 根据坐标轴进行旋转
     *
     * rotate(float)旋转的角度
     *
     * rotate(float, int, int)向x/y轴偏移一定值后旋转
     *
     * 偏移量为旋转中心的偏移
     *
     * 旋转也是可以叠加的
     *
     * @param canvas
     */
    private void canvasRotate(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rectF = new RectF(0, -100, 200, 0);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_33));
        canvas.drawRect(rectF, mPaint);

//        canvas.rotate(120);
        canvas.rotate(180, 100, 0);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_99));
        canvas.drawRect(rectF, mPaint);
    }

    private void canvasRotateTest(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0, 0, 300, mPaint);
        canvas.drawCircle(0, 0, 280, mPaint);

        for (int i = 0; i < 36; i++) {
            canvas.drawLine(280, 0, 300, 0, mPaint);
            canvas.rotate(10);
        }
    }

    /**
     * skew错切
     *
     * skew(float sx, float sy)
     *
     * float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
     * float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
     * 变换后：
     * X = x + sx * y
     * Y = sy * x + y
     *
     * 错切是可以叠加的
     *
     * @param canvas
     */
    private void canvasSkew(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);

//        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rectF = new RectF(100, 100, 150, 150);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        canvas.drawRect(rectF, mPaint);

        canvas.skew(0.5f, 0);
//        canvas.skew(0, 0.5f);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        canvas.drawRect(rectF, mPaint);
    }

    /**
     * save()保存状态
     * restore()恢复状态
     *
     * @param canvas
     */
    private void canvasSaveOrRestore(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rectF = new RectF(0, -100, 200, 0);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_33));
        canvas.drawRect(rectF, mPaint);

        canvas.save();
        canvas.rotate(60);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_99));
        canvas.drawRect(rectF, mPaint);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        canvas.restore();
        canvas.drawRect(rectF, mPaint);

    }

}
