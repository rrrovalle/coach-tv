package com.example.coach_tv.Utils;

import com.example.coach_tv.R;

public class IconManager {

    public static int getIcon(String section){
        if(section.equalsIgnoreCase("Culinary")){
            return R.drawable.food_base;
        } else if (section.equalsIgnoreCase("Code")){
            return R.drawable.programming;
        } else if (section.equalsIgnoreCase("Art")){
            return R.drawable.art_base;
        }else if (section.equalsIgnoreCase("Finances")){
            return R.drawable.base_finance;
        }
        return R.drawable.base_music;
    }
}
