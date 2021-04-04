package com.example.coach_tv.Utils;

import android.content.Context;
import android.widget.Toast;

public class Message {

    public static void printMessage(Context context, String message){
        //message = format(message);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String format(String message){
        String[] replace = message.split(":");
        String result = replace[1];
        String[] response = result.split("]");
        return response[0];
    }
}
