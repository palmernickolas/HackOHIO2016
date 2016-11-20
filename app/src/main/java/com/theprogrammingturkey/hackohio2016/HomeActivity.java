package com.theprogrammingturkey.hackohio2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Map<Integer, Class<?>> buttonLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonLinks = new HashMap<>();

        Button accountButton = (Button) findViewById(R.id.button_account);
        accountButton.setOnClickListener(this);
        buttonLinks.put(accountButton.getId(), AccountActivity.class);

        Button depositButton = (Button) findViewById(R.id.button_deposit);
        depositButton.setOnClickListener(this);
        buttonLinks.put(depositButton.getId(), DepositActivity.class);

        Button mapButton = (Button) findViewById(R.id.button_map);
        mapButton.setOnClickListener(this);
        buttonLinks.put(mapButton.getId(), MapsActivity.class);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, buttonLinks.get(view.getId()));
        this.startActivity(intent);
    }

}
