package mobile.rojas.com.budgetcare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.adapter.ExpandableListAccount;
import mobile.rojas.com.budgetcare.constant.Constants;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.Record;
import mobile.rojas.com.budgetcare.view.IAccountListView;

public class AccountListActivity extends AppCompatActivity implements IAccountListView{

    private ExpandableListView expandableListView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.expandableListView = (ExpandableListView) findViewById(R.id.expandableAccountList);

        this.realm = Realm.getDefaultInstance();

        RealmResults<AccountType> list = realm.where(AccountType.class).findAll();

        final Activity activity = this;
        createAdapter(this.getListAccount(list));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, AccountActivity.class);
                startActivity(i);

            }
        });
    }

    public ArrayList<AccountType> getListAccount(RealmResults<AccountType> list){
        System.out.println("GET LIST ACCOUNT");
        ArrayList<AccountType> accountTypesResult = new ArrayList<>();
        Double sumTotal = 0.0;
        for (AccountType accountTypeTemp: list) {
            Double sumAccountVisible = 0.0;
            ArrayList<Account> accountsResult = new ArrayList<>();
            System.out.println("ACCOUNTTYPE = " + accountTypeTemp.getName());
            for (Account accountTemp: accountTypeTemp.getAccounts()) {
                System.out.println("ACCOUNT = " + accountTemp.getName());
                Double amountIngreso = realm.where(Record.class).equalTo("buyFrom.id", accountTemp.getId()).equalTo("type.name", Constants.INGRESO).sum("amount").doubleValue();
                Double amountEgreso = realm.where(Record.class).equalTo("buyFrom.id", accountTemp.getId()).equalTo("type.name",Constants.EGRESO).sum("amount").doubleValue();
                System.out.println("INGRESO ACCOUNT" + amountIngreso);
                System.out.println("EGRESO " + amountEgreso);
                Double sumAccount =  amountIngreso - amountEgreso;
                sumAccountVisible = sumAccountVisible + sumAccount;
                accountTemp.setSaldo(sumAccount);
                accountsResult.add(accountTemp);
                System.out.println("SALDO ES NUEVO " + accountTemp.getSaldo());

            }
            sumTotal = sumTotal + sumAccountVisible;
            accountTypeTemp.setSum(sumAccountVisible);
            accountTypeTemp.setAccountsTemp( accountsResult);
            accountTypesResult.add(accountTypeTemp);
            System.out.println("ACCOUNTTYPE = " + accountTypeTemp.getName());
            System.out.println("ACCOUNTTYPE sUM= " + accountTypeTemp.getSum());


        }
        this.setTitle(this.getString(R.string.menu_cuentas) + " Bs. "+ sumTotal);
        return accountTypesResult;
    }

    @Override
    public void createAdapter(ArrayList<AccountType> list) {
        ExpandableListAdapter adapter = new ExpandableListAccount(this, list);
        this.expandableListView.setAdapter(adapter);
    }
}
