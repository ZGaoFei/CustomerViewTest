package cn.com.nggirl.bigimagetestapp.customertext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.nggirl.bigimagetestapp.R;

public class CustomerViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, CustomerViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        initView();
    }

    private void initView() {

        CustomerImageView imageView = (CustomerImageView) findViewById(R.id.customer_image_view);

//        ImageUtils utils = new ImageUtils(this);
//        imageView.setSrc(utils.three(400, 400));
    }
}
