package cn.com.nggirl.bigimagetestapp.timepicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import cn.com.nggirl.bigimagetestapp.R;

public class TimePickerActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, TimePickerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        initView();

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.add("item" + i);
        }

        for (int i = 0; i < 3; i++) {
            list2.add("haha" + i);
        }

        Collections.copy(list, list2);

        for (int i = 0; i < list.size(); i++) {
            Log.e("====", "==list==" + list.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            Log.e("====", "==list2==" + list2.get(i));
        }
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });

        Button button = (Button) findViewById(R.id.button_time_picker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("====", "====" + year + "===" + month + "===" + day);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.e("=====", "===year==" + year + "==month==" + monthOfYear + "==day==" + dayOfMonth);

            }
        }, year, month, day).show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("====", "====" + year + "===" + month + "===" + day);
        new DatePickerShowDialog(this, new DatePickerShowDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.e("====", "====" + year + "===" + month + "===" + dayOfMonth);
            }
        }, year, month, day).show();
    }
}
