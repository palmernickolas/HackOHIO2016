package com.theprogrammingturkey.hackohio2016;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Map<Integer, Class<?>> buttonLinks;

    private boolean loggedIn;
    private FrameLayout frameLayout;
    private LinearLayout loginLayout;
    private LinearLayout navigationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loginLayout = (LinearLayout) inflater.inflate(R.layout.activity_login, null);
        navigationLayout = (LinearLayout) inflater.inflate(R.layout.activity_navigation, null);

        loggedIn = false;

        if (!loggedIn) {
            frameLayout.removeAllViews();
            frameLayout.addView(loginLayout);
        } else {
            frameLayout.removeAllViews();
            frameLayout.addView(navigationLayout);
        }

        buttonLinks = new HashMap<Integer, Class<?>>();


        Button depositButton = (Button) navigationLayout.findViewById(R.id.deposit);
        depositButton.setOnClickListener(this);
        buttonLinks.put(depositButton.getId(), DepositActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Ian resume");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, buttonLinks.get(view.getId()));
        this.startActivity(intent);
    }
}
