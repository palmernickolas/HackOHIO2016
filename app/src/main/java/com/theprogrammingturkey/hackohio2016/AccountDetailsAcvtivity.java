package com.theprogrammingturkey.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.theprogrammingturkey.hackohio2016.util.NessieHook;

public class AccountDetailsAcvtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_details_acvtivity);

        String s = getIntent().getStringExtra("ACCOUNT_ID");

        NessieHook.getNessie().ACCOUNT.getAccount(s, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                loadAccountData((Account) result);
            }

            @Override
            public void onFailure(NessieError error) {
                System.err.println(error.getMessage());
            }
        });
    }

    public void loadAccountData(Account a) {
        TextView name = (TextView) findViewById(R.id.account_name);
        name.setText(a.getNickname());

        TextView type = (TextView) findViewById(R.id.account_type);
        type.setText(a.getType().toString());

        TextView balance = (TextView) findViewById(R.id.account_balance);
        balance.setText("$" + a.getBalance());

        TextView rewards = (TextView) findViewById(R.id.account_rewards);
        rewards.setText(a.getRewards() + " pts");
    }

}
