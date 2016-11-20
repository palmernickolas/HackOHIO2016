package com.theprogrammingturkey.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.models.Deposit;
import com.theprogrammingturkey.hackohio2016.util.NessieHook;


public class DepositActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner spinner;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.deposit_arrays, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        getSupportActionBar().setTitle("Hack OHI/O 2016");

        String test = (String) spinner.getSelectedItem();


    }


    public void getDeposit(String depositId){
        NessieHook.getNessie().DEPOSIT.getDeposit(depositId, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }

    public void createDeposit(String accountId, Deposit deposit){
        NessieHook.getNessie().DEPOSIT.createDeposit(accountId, deposit, new NessieResultsListener() {

            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }

    public void updateDeposit(String depositId, Deposit deposit){
        NessieHook.getNessie().DEPOSIT.updateDeposit(depositId, deposit, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }

    public void deleteDeposit(String depositId) {
        NessieHook.getNessie().DEPOSIT.deleteDeposit(depositId, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        String textSelected = (String) parentView.getSelectedItem();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
    }

}
