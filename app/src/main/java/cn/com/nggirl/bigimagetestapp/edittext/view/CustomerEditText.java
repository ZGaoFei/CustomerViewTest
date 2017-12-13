package cn.com.nggirl.bigimagetestapp.edittext.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerEditText extends AppCompatEditText implements TextWatcher {

    public CustomerEditText(Context context) {
        super(context);
        init();
    }

    public CustomerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();

        String trim = getText().toString().trim();
        if (length() >= 2) {
            if (String.valueOf(trim.charAt(0)).equals("0") && !String.valueOf(trim.charAt(1))
                    .equals(".")) {

                s.delete(0, 1);
            }
        }

        if (!isOnlyPointNumber(getText().toString()) && length() > 0) {
            //删除多余输入的字（不会显示出来）
            s.delete(selectionStart - 1, selectionEnd);
            setText(s);
            setSelection(s.length());
        }
    }

    public boolean isOnlyPointNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public String getTexts() {
        String string = getText().toString().trim();
        string = deleteEndPoint(string);
        string = deleteStartZero(string);

        return string;
    }

    /**
     * 去除末尾为小数点的情况
     *
     * @param string
     * @return
     */
    private String deleteEndPoint(String string) {
        if (string.endsWith(".")) {
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    /**
     * 去除首位为0，第二位不为小数点的情况
     *
     * @param string
     * @return
     */
    private String deleteStartZero(String string) {
        int length = string.length();
        if (length > 2) {
            if (String.valueOf(string.charAt(0)).equals("0") && !String.valueOf(string.charAt(1))
                    .equals(".")) {
                return string.substring(1, string.length());
            }
        }
        return string;
    }
}
