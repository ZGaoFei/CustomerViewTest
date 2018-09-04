package cn.com.nggirl.bigimagetestapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.XMLReader;

public class HtmlTextActivity extends AppCompatActivity {
    private TextView textView;

    private ImageView imageView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, HtmlTextActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text);

        textView = (TextView) findViewById(R.id.text_html);
        imageView = (ImageView) findViewById(R.id.image_view_resize);


//        setTextView();
    }

    private void setImageView() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        int width = 150 * 3;
        int height = 267 * 3;

        int width1 = bitmap.getWidth();
        int height1 = bitmap.getHeight();

        float ratio = (float) width / (float) height;


        Bitmap bitmap1 = resizeImageView(bitmap, 0, 0);
        imageView.setImageBitmap(bitmap1);
    }

    private Bitmap resizeImageView(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        // calculate the scale
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        return Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
    }

    private void setTextView() {
        String topic1 = "<topic>" + "#宝马#" + "<topic/>";
        String topic2 = "<topic>" + "#随手拍车#" + "<topic/>";
        String topic3 = "<topic>" + "#看车#" + "<topic/>";
        String string = topic1 + topic2 + topic3 + "显示四行文字文字文字显示四行文字文字文字显示四行文字文字文字显示四行文字文字文字显示四行文字文字文字显示四行文字文字文字显示四行文字文字文字显示四行文字文字文字显示四行文字文字文字";

//        Spanned spanned = Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY, null, new DetailTagHandler(this, topic1, topic2, topic3));
//        Spanned spanned = Html.fromHtml(string);
//        textView.setText(spanned);

        textView.setText(Html.fromHtml(string, null, new DetailTagHandler(this, topic1, topic2, topic3)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public class DetailTagHandler implements Html.TagHandler {
        private Context context;
        private String topic1;
        private String topic2;
        private String topic3;

        private int startIndex;
        private int stopIndex;

        public DetailTagHandler(Context context, String topic1, String topic2, String topic3) {
            this.context = context;
            this.topic1 = topic1;
            this.topic2 = topic2;
            this.topic3 = topic3;
        }

        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            if (tag.toLowerCase().equals("topic")) {
                if (opening) {
                    startGame(tag, output, xmlReader);
                } else {
                    endGame(tag, output, xmlReader);
                }
            }

            int length = 0;
            /*output.setSpan(new ClickableImage(context, topic1), length, topic1.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            length = length + topic1.length();
            output.setSpan(new ClickableImage(context, topic2), length, length + topic2.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            length = length + topic2.length();
            output.setSpan(new ClickableImage(context, topic3), length, length + topic3.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        }

        public void startGame(String tag, Editable output, XMLReader mlReader) {
            startIndex = output.length();

            Log.e("zgf", "start index" + startIndex + "====output===" + output);
        }

        public void endGame(String tag, Editable output, XMLReader xmlReader) {
            stopIndex = output.length();
            Log.e("zgf", "stop index" + stopIndex + "====output====" + output);

            output.setSpan(new ClickableImage(context, output.toString()), 0, 4,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        private class ClickableImage extends ClickableSpan {
            private String string;
            private Context context;

            public ClickableImage(Context context, String string) {
                this.string = string;
                this.context = context;
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
            }

            @Override
            public void onClick(View widget) {
                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
