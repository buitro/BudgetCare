package mobile.rojas.com.budgetcare.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.CurrencyType;
import mobile.rojas.com.budgetcare.presenter.AccountPresenter;
import mobile.rojas.com.budgetcare.presenter.IAccountPresenter;
import mobile.rojas.com.budgetcare.view.IAccountView;

public class AccountActivity extends AppCompatActivity implements IAccountView {

    private Spinner mSpinnerAccountType;
    private Spinner mSpinnerCurrencyType;
    private IAccountPresenter iAccountPresenter;
    private EditText mName;
    private EditText mBalance;
    private EditText mDateTime;
    private Switch mSum;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_account);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.mSpinnerAccountType = (Spinner) findViewById(R.id.spinner_account_type);
        this.mSpinnerCurrencyType = (Spinner) findViewById(R.id.spinner_account_type_currency);
        this.mName = (EditText) findViewById(R.id.mAccountName);
        this.mBalance = (EditText) findViewById(R.id.mAcccountBalance);
        this.mDateTime = (EditText) findViewById(R.id.mAccountDateTime);
        this.mSum = (Switch) findViewById(R.id.mSwitchSum);

        this.iAccountPresenter = new AccountPresenter(this.getApplicationContext(), this);
        this.iAccountPresenter.getCurrencyTypes();
        this.iAccountPresenter.getAccountTypes();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

    }

    @Override
    public void setAccountTypes(RealmResults<AccountType> list) {
        ArrayAdapter<AccountType> adapter = new ArrayAdapter<AccountType>(this, android.R.layout.simple_dropdown_item_1line, list);
        this.mSpinnerAccountType.setAdapter(adapter);
    }

    @Override
    public void setCurrencyTypes(RealmResults<CurrencyType> list) {
        ArrayAdapter<CurrencyType> adapter = new ArrayAdapter<CurrencyType>(this, android.R.layout.simple_dropdown_item_1line, list);
        this.mSpinnerCurrencyType.setAdapter(adapter);
    }

    @Override
    public void setName(String message) {
        this.mName.setError(message);
    }

    @Override
    public void setBalance(String message) {
        this.mBalance.setError(message);
    }

    @Override
    public void setDate(String string) {
        this.mDateTime.setError(string);
    }

    @Override
    public void navigationTo() {
        Intent i = new Intent(this, AccountListActivity.class);
        startActivity(i);
    }

    @Override
    public void showMessage(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }


    public void saveAccount(View view) {
        System.out.println("Ingreso al boton");
        AccountType accountType = (AccountType) this.mSpinnerAccountType.getSelectedItem();
        System.out.println(accountType.getName() + " " + accountType.getId());

        CurrencyType currencyType = (CurrencyType) this.mSpinnerCurrencyType.getSelectedItem();
        System.out.println(currencyType.getName() + " " + currencyType.getId());
        System.out.println("No ingreso al boton");

        String name = this.mName.getText().toString();
        String balance = this.mBalance.getText().toString();
        String date = this.mDateTime.getText().toString();
        Boolean isSum = this.mSum.isChecked();

        this.iAccountPresenter.save(name,balance, date, isSum, accountType, currencyType);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        this.mDateTime.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

    }
}
