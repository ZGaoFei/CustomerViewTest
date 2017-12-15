package cn.com.nggirl.bigimagetestapp.path.viewtest;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TaijiView extends View {

    private Paint mDeafultPaint;
    private int mViewWidth;
    private int mViewHeight;

    public TaijiView(Context context) {
        super(context);
        init();
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.FILL);
        mDeafultPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        Path path0 = new Path();
        path0.addCircle(0, 0, 200, Path.Direction.CW);
        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.STROKE);
        mDeafultPaint.setStrokeWidth(10);
        canvas.drawPath(path0, mDeafultPaint);

        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.FILL);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);

        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);

        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);

        /*Path path5 = new Path();
        Path path6 = new Path();
        Path path7 = new Path();

        path5.addRect(-200, -200, 0, 200, Path.Direction.CW);
        path6.addCircle(0, -100, 100, Path.Direction.CW);
        path7.addCircle(0, 100, 100, Path.Direction.CCW);

        path1.op(path5, Path.Op.DIFFERENCE);
        path1.op(path6, Path.Op.DIFFERENCE);
        path1.op(path7, Path.Op.UNION);*/


        canvas.drawPath(path1, mDeafultPaint);

        Path whiteCircle = new Path();
        whiteCircle.addCircle(0, -100, 50, Path.Direction.CW);
        mDeafultPaint.setColor(Color.WHITE);
        mDeafultPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(whiteCircle, mDeafultPaint);

        Path blackCircle = new Path();
        blackCircle.addCircle(0, 100, 50, Path.Direction.CW);
        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(blackCircle, mDeafultPaint);
    }
}
