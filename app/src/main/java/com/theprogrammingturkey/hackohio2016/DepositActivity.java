package com.theprogrammingturkey.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class DepositActivity extends AppCompatActivity implements OnItemSelectedListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.deposit_arrays, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new DepositActivity());

        System.out.println("Ian yes");
        textView = (TextView) findViewById(R.id.text_view);
        System.out.println("Ian yes2");


    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


        if (textView != null){
            textView.setText("YO");

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
    }

}
