package com.b3lon9.app.spinnermodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.b3lon9.app.simplespinner.TestToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestToast.show(this, "테스트");
    }
}