package com.example.coach_tv.Utils;

import android.text.TextUtils;

public class Validate {

    public static boolean validate(String txt1, String txt2){
        if(!TextUtils.isEmpty(txt1) && !TextUtils.isEmpty(txt2)){
            return true;
        }
        return false;
    }
}
