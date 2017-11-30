package cn.com.nggirl.bigimagetestapp.path;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathTestView extends View {
    private Paint paint;
    private Path path;

    private int width;
    private int height;

    public PathTestView(Context context) {
        super(context);
        init();
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        path = new Path();
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

//        pathTest1(canvas);
//        pathTest2(canvas);
//        pathTest3(canvas);
//        pathTest4(canvas);
//        pathTest5(canvas);
//        pathTest6(canvas);
//        pathTest7(canvas);
        pathTest8(canvas);

    }

    private void pathTest1(Canvas canvas) {
        canvas.translate(width / 2, height / 2);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        path.lineTo(200, 200);

//        path.moveTo(100, 100);
        path.setLastPoint(200, 100);

        path.lineTo(200, 0);
        path.close();

        canvas.drawPath(path, paint);//如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。如path.moveTo(100, 100)
    }

    private void pathTest2(Canvas canvas) {
        canvas.translate(width / 2, height / 2);

//        paint.reset();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);

        path.addRect(-100, -100, 100, 100, Path.Direction.CCW);

        canvas.drawPath(path, paint);
    }

    private void pathTest3(Canvas canvas) {
        canvas.translate(width / 2, height / 2);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        path.addRect(-100, -100, 100, 100, Path.Direction.CCW);
        path.setLastPoint(-50, 50);

        canvas.drawPath(path, paint);
    }

    private void pathTest4(Canvas canvas) {
        canvas.translate(width / 2, height / 2);
        canvas.scale(1, -1);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        Path path1 = new Path();
        Path path2 = new Path();

        path1.addRect(-100, -100, 100, 100, Path.Direction.CCW);
        path2.addCircle(0, 0, 100, Path.Direction.CCW);

        path1.addPath(path2, 0, 100);

        canvas.drawPath(path1, paint);
    }

    private void pathTest5(Canvas canvas) {
        canvas.translate(width / 2, height / 2);
        canvas.scale(1, -1);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        path.lineTo(100, 100);

        RectF rectF = new RectF(0, 0, 300, 300);
//        path.addArc(rectF, 0, 270);
        path.arcTo(rectF, 0, 270, false);

        canvas.drawPath(path, paint);
    }

    private void pathTest6(Canvas canvas) {
        canvas.translate(width / 2, height / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1, -1);                         // <-- 注意 翻转y坐标轴

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
//        paint.setAntiAlias(true);

        Path path = new Path();
        path.lineTo(100, 100);

        RectF oval = new RectF(0, 0, 300, 300);

//        path.arcTo(oval, 0, 270);
        path.arcTo(oval, 0, 270, true);             // <-- 和上面一句作用等价

        canvas.drawPath(path, paint);
    }

    private void pathTest7(Canvas canvas) {
        canvas.translate(width / 2, height / 2);
        canvas.scale(1, -1);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        Path path1 = new Path();
        path1.addCircle(0, 0, 100, Path.Direction.CCW);

        Path path2 = new Path();
        path2.addRect(-100, -100, 100, 100, Path.Direction.CCW);

        path2.set(path1);
        canvas.drawPath(path2, paint);
    }

    private void pathTest8(Canvas canvas) {
        canvas.translate(width / 2, height / 2);
        canvas.scale(1, -1);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        Path path1 = new Path();
        path1.addCircle(0, 0, 100, Path.Direction.CCW);

        Path path2 = new Path();
        path2.addRect(-100, -100, 100, 100, Path.Direction.CCW);

        path1.offset(200, 0, path2);

        canvas.drawPath(path1, paint);

        paint.setColor(Color.RED);

        canvas.drawPath(path2, paint);
    }
}
