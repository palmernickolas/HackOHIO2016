package com.theprogrammingturkey.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.AccountType;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.theprogrammingturkey.hackohio2016.util.NessieHook;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean savingsCollapsed = false;
    private final List<Account> savingsAccounts = new ArrayList<>();
    private boolean checkingCollapsed = false;
    private final List<Account> checkingAccounts = new ArrayList<>();
    private boolean creditCollapsed = false;
    private final List<Account> creditAccounts = new ArrayList<>();

    private Button savingsEC;
    private LinearLayout savingLayout;
    private Button checkingEC;
    private LinearLayout checkingLayout;
    private Button creditEC;
    private LinearLayout creditLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        savingsEC = (Button) findViewById(R.id.expand_collapse_savings);
        savingLayout = (LinearLayout) findViewById(R.id.savings_layout);
        checkingEC = (Button) findViewById(R.id.expand_collapse_checking);
        checkingLayout = (LinearLayout) findViewById(R.id.checking_layout);
        creditEC = (Button) findViewById(R.id.expand_collapse_credit);
        creditLayout = (LinearLayout) findViewById(R.id.credit_layout);

        loadAccounts();
    }

    public void loadAccounts() {
        NessieHook.getNessie().ACCOUNT.getAccounts(AccountType.SAVINGS, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                savingsAccounts.clear();
                savingsAccounts.addAll((List<Account>) result);
                updateSavingsList();
            }

            @Override
            public void onFailure(NessieError error) {

            }
        });

        NessieHook.getNessie().ACCOUNT.getAccounts(AccountType.CHECKING, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                checkingAccounts.clear();
                checkingAccounts.addAll((List<Account>) result);
                updateCheckingList();
            }

            @Override
            public void onFailure(NessieError error) {

            }
        });

        NessieHook.getNessie().ACCOUNT.getAccounts(AccountType.CREDITCARD, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                creditAccounts.clear();
                creditAccounts.addAll((List<Account>) result);
                updateCreditList();
            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.expand_collapse_savings) {
            savingsCollapsed = !savingsCollapsed;
            this.updateSavingsList();
        } else if (view.getId() == R.id.expand_collapse_checking) {
            checkingCollapsed = !checkingCollapsed;
            updateCheckingList();
        } else if (view.getId() == R.id.expand_collapse_credit) {
            creditCollapsed = !creditCollapsed;
            updateCreditList();
        }

    }

    public void updateSavingsList() {
        if (savingsAccounts.size() == 0) {
            savingsCollapsed = true;
        }
        if (savingsCollapsed) {
            savingLayout.setVisibility(View.GONE);
            savingLayout.removeAllViewsInLayout();
        } else {
            savingLayout.setVisibility(View.VISIBLE);
            for (Account a : this.savingsAccounts) {
                Button b = new Button(this);
                b.setText(a.getNickname());
                savingLayout.addView(b);
            }
        }
        savingsEC.setBackground(getResources().getDrawable(savingsCollapsed ? android.R.drawable.arrow_down_float : android.R.drawable.arrow_up_float, null));
    }

    public void updateCheckingList() {
        if (checkingAccounts.size() == 0) {
            checkingCollapsed = true;
        }
        if (checkingCollapsed) {
            checkingLayout.setVisibility(View.GONE);
            checkingLayout.removeAllViewsInLayout();
        } else {
            checkingLayout.setVisibility(View.VISIBLE);
            for (Account a : this.checkingAccounts) {
                Button b = new Button(this);
                b.setText(a.getNickname());
                checkingLayout.addView(b);
            }
        }
        checkingEC.setBackground(getResources().getDrawable(checkingCollapsed ? android.R.drawable.arrow_down_float : android.R.drawable.arrow_up_float, null));
    }

    public void updateCreditList() {
        if (creditAccounts.size() == 0) {
            creditCollapsed = true;
        }
        if (creditCollapsed) {
            creditLayout.setVisibility(View.GONE);
            creditLayout.removeAllViewsInLayout();
        } else {
            creditLayout.setVisibility(View.VISIBLE);
            for (Account a : this.creditAccounts) {
                Button b = new Button(this);
                b.setText(a.getNickname());
                creditLayout.addView(b);
            }
        }
        creditEC.setBackground(getResources().getDrawable(creditCollapsed ? android.R.drawable.arrow_down_float : android.R.drawable.arrow_up_float, null));
    }
}
