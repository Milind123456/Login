package com.example.test.login;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by test on 12-06-2017.
 */

public class Message {
    public static void message(Context context,String Message){
        Toast.makeText(context,Message,Toast.LENGTH_SHORT).show();
    }
}
