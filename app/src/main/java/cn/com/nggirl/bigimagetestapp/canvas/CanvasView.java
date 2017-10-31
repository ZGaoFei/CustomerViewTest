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


public class CanvasView extends View {
    private Context mContext;

    private Paint mPaint;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initPaint();
    }

    private void init(Context context) {
        mContext = context;
    }

    /**
     * 初始化画笔
     * FILL：填充，将绘制的区域进行填充绘制
     * STROKE：描边，在绘制的区域外添加一个边界区域，仅绘制边界区域
     * FILL_AND_STROKE：描边和填充，将绘制的区域进行填充绘制，在绘制的区域外再添加一个边界区域进行绘制
     *
     */
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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvasDraw(canvas);
        drawTest(canvas);
    }

    private void canvasDraw(Canvas canvas) {
        // 绘制点
        /*canvas.drawPoint(10, 10, mPaint);//绘制一个点

        canvas.drawPoints(new float[]{// 绘制一组点
                200, 100,
                200, 200,
                200, 300
        }, mPaint);*/

        // 绘制线
//        canvas.drawLine(100, 100, 200, 200, mPaint);

        /*canvas.drawLines(new float[]{
                120, 120, 220, 220,
                150, 150, 250, 250
        }, mPaint);*/

        //绘制矩形
        /*canvas.drawRect(130, 130, 260, 260, mPaint);

        Rect rect = new Rect(140, 140, 270, 270);
        canvas.drawRect(rect, mPaint);

        RectF rectF = new RectF(150, 150, 280, 280);
        canvas.drawRect(rectF, mPaint);*/

        // 绘制圆角矩形
        /*RectF rectF1 = new RectF(150, 150, 280, 280);
        canvas.drawRoundRect(rectF1, 30, 30, mPaint);*/

//        canvas.drawRoundRect(10, 20, 150, 260, 90, 90, mPaint);

        //绘制圆角矩形的圆角半径如果大于边的长度的一半，按一半计算
        /*RectF rectF2 = new RectF(100, 100, 600, 400);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_33));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectF2, mPaint);
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_99));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rectF2, 200, 200, mPaint);*/

        // 绘制椭圆
//        canvas.drawOval(100, 100, 600, 400, mPaint);

//        RectF rectF3 = new RectF(100, 100, 600, 400);
//        canvas.drawOval(rectF3, mPaint);
//        RectF rectF4 = new RectF(100, 100, 600, 600);//当椭圆的长和宽一致是就是圆
//        canvas.drawOval(rectF4, mPaint);

        //绘制圆
//        canvas.drawCircle(300, 300, 200, mPaint);

        // 绘制圆弧
        RectF rectF5 = new RectF(10, 10, 600, 400);
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_99));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectF5, mPaint);
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_33));

        /**
         * RectF rectF5 = new RectF(10, 10, 600, 400);
         * 确定圆弧所在矩形的范围内进行绘制
         *
         * startAngle：开始绘制的角度进行顺时针绘制
         * 圆弧开始绘制的起点的位置
         * sweepAngle：圆弧扫过的角度
         * 圆弧绘制过程经过圆心扫过的角度
         */
        canvas.drawArc(rectF5, 90, 90, false, mPaint);
        canvas.drawArc(rectF5, 0, 90, false, mPaint);
        canvas.drawArc(rectF5, 180, 90, false, mPaint);
        canvas.drawArc(rectF5, 270, 90, false, mPaint);

//        canvas.drawArc(200, 100, 700, 450, 60, 60, false, mPaint);


    }

    /**
     * 绘制一个饼状图
     */
    private void drawTest(Canvas canvas) {
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(ContextCompat.getColor(mContext, R.color.black));
//        canvas.drawCircle(300, 300, 200, mPaint);

        RectF rectF = new RectF(100, 100, 500, 500);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_33));
        canvas.drawArc(rectF, 0, 30, true, mPaint);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.color_99));
        canvas.drawArc(rectF, 30, 80, true, mPaint);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.black));
        canvas.drawArc(rectF, 110, 50, true, mPaint);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        canvas.drawArc(rectF, 150, 80, true, mPaint);

        mPaint.setColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        canvas.drawArc(rectF, 190, 120, true, mPaint);
    }
}
