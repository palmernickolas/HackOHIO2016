package com.theprogrammingturkey.hackohio2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.transfer) {
            EditText transferAm = (EditText) findViewById(R.id.amount);
            int transferAmount = Integer.parseInt(transferAm.toString());



        }
    }
}