package cn.com.nggirl.bigimagetestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.com.nggirl.bigimagetestapp.bigimage.BigImageActivity;
import cn.com.nggirl.bigimagetestapp.customer.CounterViewActivity;
import cn.com.nggirl.bigimagetestapp.customertext.CustomerViewActivity;
import cn.com.nggirl.bigimagetestapp.mylistview.CustomerListViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button btOne = (Button) findViewById(R.id.bt_skip_one);
        btOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigImageActivity.start(MainActivity.this);
            }
        });

        Button btTwo = (Button) findViewById(R.id.bt_skip_two);
        btTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CounterViewActivity.start(MainActivity.this);
            }
        });

        Button btThree = (Button) findViewById(R.id.bt_skip_three);
        btThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerListViewActivity.start(MainActivity.this);
            }
        });

        Button btFour = (Button) findViewById(R.id.bt_skip_four);
        btFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerViewActivity.start(MainActivity.this);
            }
        });

    }

}
