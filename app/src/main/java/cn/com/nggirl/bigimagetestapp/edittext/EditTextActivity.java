package cn.com.nggirl.bigimagetestapp.edittext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.edittext.view.CustomerEditText;

public class EditTextActivity extends AppCompatActivity {
    private EditText editText;
    private CustomerEditText customerEditText;
    private Button button;

    public static void start(Context context) {
        context.startActivity(new Intent(context, EditTextActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edit_text);
        customerEditText = (CustomerEditText) findViewById(R.id.custom_edit_text);
        button = (Button) findViewById(R.id.button_get_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditTextActivity.this, customerEditText.getTexts(), Toast.LENGTH_SHORT).show();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                selectionStart = editText.getSelectionStart();
                selectionEnd = editText.getSelectionEnd();

                if (!isOnlyPointNumber(editText.getText().toString()) && s.length() > 0) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }
            }
        });
    }

    public static boolean isOnlyPointNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
