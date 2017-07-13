package cn.com.nggirl.bigimagetestapp.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.nggirl.bigimagetestapp.R;

public class CounterViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, CounterViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_view);

        initView();
    }

    private void initView() {
        CounterView view = (CounterView) findViewById(R.id.counter_view);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
