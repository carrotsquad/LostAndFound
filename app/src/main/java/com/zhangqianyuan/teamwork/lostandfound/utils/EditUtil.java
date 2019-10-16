package com.zhangqianyuan.teamwork.lostandfound.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUtil {
    public static void EditAllClear(final Button button, final EditText editText){
        if(TextUtils.isEmpty(editText.getText())){
            button.setVisibility(View.INVISIBLE);
        }else {
            button.setVisibility(View.VISIBLE);
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 0){
                    button.setVisibility(View.INVISIBLE);
                }else {
                    button.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
