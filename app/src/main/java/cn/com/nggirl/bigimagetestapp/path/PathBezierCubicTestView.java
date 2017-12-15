package cn.com.nggirl.bigimagetestapp.path;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PathBezierCubicTestView extends View {
    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;

    private PointF mStart;
    private PointF mEnd;
    private PointF mControl1;
    private PointF mControl2;

    private int mPointIndex;

    public PathBezierCubicTestView(Context context) {
        super(context);
        init();
    }

    public PathBezierCubicTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathBezierCubicTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        mStart = new PointF(0, 0);
        mEnd = new PointF(0, 0);
        mControl1 = new PointF(0, 0);
        mControl2 = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w / 2;
        mCenterY = h / 2;

        // 初始化数据点和控制点的位置
        mStart.x = mCenterX - 200;
        mStart.y = mCenterY;
        mEnd.x = mCenterX + 200;
        mEnd.y = mCenterY;
        mControl1.x = mCenterX - 200;
        mControl1.y = mCenterY - 100;
        mControl2.x = mCenterX + 200;
        mControl2.y = mCenterY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mPointIndex == 0) {
            mControl1.x = event.getX();
            mControl1.y = event.getY();
        } else {
            mControl2.x = event.getX();
            mControl2.y = event.getY();
        }
        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw point
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(mStart.x, mStart.y, mPaint);
        canvas.drawPoint(mEnd.x, mEnd.y, mPaint);
        canvas.drawPoint(mControl1.x, mControl1.y, mPaint);
        canvas.drawPoint(mControl2.x, mControl2.y, mPaint);

        // draw help line
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(8);
        canvas.drawLine(mStart.x, mStart.y, mControl1.x, mControl1.y, mPaint);
        canvas.drawLine(mControl1.x, mControl1.y, mControl2.x, mControl2.y, mPaint);
        canvas.drawLine(mEnd.x, mEnd.y, mControl2.x, mControl2.y, mPaint);

        // draw bezier line
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(mStart.x, mStart.y);
        path.cubicTo(mControl1.x, mControl1.y, mControl2.x, mControl2.y, mEnd.x, mEnd.y);

        canvas.drawPath(path, mPaint);
    }

    public void setPointIndex(int pointIndex) {
        this.mPointIndex = pointIndex;
    }
}
