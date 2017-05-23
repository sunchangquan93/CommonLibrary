package com.quanquan.jcenterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView tv1;
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv1= (TextView) findViewById(R.id.tv1);
        tv1.setVisibility(View.GONE);
        tv2= (TextView) findViewById(R.id.tv2);
        tv2.setText(tv1.getText().toString()+"lalla");
    }
}
