package com.b3lon9.app.simplespinner.util;

import android.util.Log;

public class Debug {
    public static void Write(String msg) {
        Exception e = new Exception();
        StackTraceElement s = e.getStackTrace()[1];

        Log.d("Neander", "[" + s.getFileName() + ":" + s.getLineNumber() + "]" + msg);
    }
}
