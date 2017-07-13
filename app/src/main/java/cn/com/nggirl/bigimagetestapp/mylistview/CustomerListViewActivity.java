package cn.com.nggirl.bigimagetestapp.mylistview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.com.nggirl.bigimagetestapp.R;

public class CustomerListViewActivity extends AppCompatActivity {
    private List<String> list;
    private MyAdapter adapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, CustomerListViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_view);

        initView();
    }

    private void initView() {
        CustomerListView listView = (CustomerListView) findViewById(R.id.lv_customer);
        listView.setOnDeleteListener(new CustomerListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                list.remove(index);
                adapter.notifyDataSetChanged();
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item:" + i);
        }

        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
