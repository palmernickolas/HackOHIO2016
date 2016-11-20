package com.theprogrammingturkey.hackohio2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.theprogrammingturkey.hackohio2016.util.NessieHook;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner apiKeyList;
    private TextView addAPITextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        NessieHook.init(this);

        apiKeyList = (Spinner) findViewById(R.id.api_keys_spinner);
        this.updateSpinnerContents();
    }

    public void updateSpinnerContents() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apiKeyList.setAdapter(spinnerAdapter);
        for (String s : NessieHook.getStoredAPIKeys())
            spinnerAdapter.add(s);
        spinnerAdapter.notifyDataSetChanged();
        addAPITextView = (TextView) findViewById(R.id.api_key_text_box);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.select_button) {
            if (apiKeyList.getAdapter().getCount() == 0) {
                return;
            }
            NessieHook.setCurrentApiKey((String) apiKeyList.getSelectedItem());
            Intent intent = new Intent(this, HomeActivity.class);
            this.startActivity(intent);
        } else if (view.getId() == R.id.add_button) {
            NessieHook.addApiKey(this.addAPITextView.getText().toString());
            this.addAPITextView.clearComposingText();
            this.updateSpinnerContents();
        } else if (view.getId() == R.id.remove_button) {
            NessieHook.removeApiKey((String) apiKeyList.getSelectedItem());
            this.updateSpinnerContents();
        }

    }
}
