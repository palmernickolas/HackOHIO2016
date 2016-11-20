package com.theprogrammingturkey.hackohio2016;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BankBranches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_branches);
    }

    public void showNearbyBranches(View v) {
       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/capital+one/"));
        startActivity(browserIntent);

    }

    public void showNearbyAtms(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/atms/"));
        startActivity(browserIntent);

    }
}
