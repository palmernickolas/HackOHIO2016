package com.theprogrammingturkey.hackohio2016;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
=======
import android.view.View;
import android.widget.EditText;
>>>>>>> Matthew/master

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Map<Integer, Class<?>> buttonLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLinks = new HashMap<Integer, Class<?>>();

        Button accountButton = (Button) findViewById(R.id.account);
        accountButton.setOnClickListener(this);
        buttonLinks.put(accountButton.getId(), AccountActivity.class);

        Button depositButton = (Button) findViewById(R.id.deposit);
        depositButton.setOnClickListener(this);
        buttonLinks.put(depositButton.getId(), DepositActivity.class);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, buttonLinks.get(view.getId()));
        this.startActivity(intent);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.transfer) {
            EditText transferAm = (EditText) findViewById(R.id.amount);
            int transferAmount = Integer.parseInt(transferAm.toString());



        }
    }
}