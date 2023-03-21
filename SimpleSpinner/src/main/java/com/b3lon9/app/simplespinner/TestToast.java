package com.b3lon9.app.simplespinner;

import android.content.Context;
import android.widget.Toast;

public class TestToast {
    public static void show(Context context, String testMsg) {
        Toast.makeText(context, testMsg, Toast.LENGTH_SHORT).show();
    }
}
