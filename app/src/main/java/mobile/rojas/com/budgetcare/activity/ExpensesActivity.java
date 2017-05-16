package mobile.rojas.com.budgetcare.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.ExpensesSubcategory;
import mobile.rojas.com.budgetcare.presenter.ExpensesPresenter;
import mobile.rojas.com.budgetcare.presenter.IExpensesPresenter;
import mobile.rojas.com.budgetcare.view.IExpensesView;


public class ExpensesActivity extends AppCompatActivity implements IExpensesView {

    private Spinner spinnerCategory;
    private Spinner spinnerSubcategory;
    private IExpensesPresenter iExpensesPresenter;
    private EditText mDate;
    private EditText mAmount;
    private Spinner mSpinnerPayFrom;
    private EditText mDesription;
    private Calendar calendar;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        this.spinnerCategory = (Spinner) findViewById(R.id.spinner_expenses_category);
        this.spinnerSubcategory = (Spinner) findViewById(R.id.spinner_expenses_subcategory);
        this.mDate = (EditText) findViewById(R.id.expenses_form_date);
        this.mAmount = (EditText) findViewById(R.id.expenses_form_amount);
        this.mSpinnerPayFrom = (Spinner) findViewById(R.id.spinner_expenses_pay);
        this.mDesription = (EditText) findViewById(R.id.expenses_form_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarExpenses);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Realm realm = Realm.getDefaultInstance();

        final RealmResults<ExpensesCategory> list = realm.where(ExpensesCategory.class).findAll();
        this.loadCategory(list);
        if (list.size() > 0) {

            this.loadSubcategory(list.get(0).getSubcategory().where().findAll());
        }
        this.iExpensesPresenter = new ExpensesPresenter(this.getApplicationContext(), this);
        this.iExpensesPresenter.loadPayFrom();


        this.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(list.get(i).getSubcategory().size());
                System.out.println(list.get(i).getName());
                ExpensesCategory categoryTemp = (ExpensesCategory) spinnerCategory.getSelectedItem();
                System.out.println("El que todo lo puede" + categoryTemp.getName() + " cantidad de subcategorias" + categoryTemp.getSubcategory().size());
                ArrayAdapter<ExpensesSubcategory> adapter1 = new ArrayAdapter<ExpensesSubcategory>(view.getContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        categoryTemp.getSubcategory());
                spinnerSubcategory.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }



    @Override
    public void loadCategory(RealmResults<ExpensesCategory> list) {
        ArrayAdapter<ExpensesCategory> adapter = new ArrayAdapter<ExpensesCategory>(this, android.R.layout.simple_dropdown_item_1line, list);
        spinnerCategory.setAdapter(adapter);

    }

    @Override
    public void loadSubcategory(RealmResults<ExpensesSubcategory> list) {
        ArrayAdapter<ExpensesSubcategory> adapter1 = new ArrayAdapter<ExpensesSubcategory>(this, android.R.layout.simple_dropdown_item_1line,list);
        spinnerSubcategory.setAdapter(adapter1);
    }

    @Override
    public void loadPayFrom(RealmResults<Account> list) {
        ArrayAdapter<Account> adapter1 = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line,list);
        mSpinnerPayFrom.setAdapter(adapter1);
    }

    @Override
    public void setAmount(String message) {
        this.mAmount.setError(message);
    }

    @Override
    public void setDate(String string) {
        this.mAmount.setError(string);
    }

    @Override
    public void setDescription(String string) {
        this.mDesription.setError(string);
    }

    @Override
    public void navigationTo() {
        Intent i = new Intent(this, ExpensesListActivity.class);
        startActivity(i);
    }

    @Override
    public void setAccount(String string) {
        Toast.makeText(this.getApplicationContext(), string,Toast.LENGTH_LONG).show();
    }


    public void save(View view) {
        System.out.println("Ingreso a guardar expenses");

        String date = this.mDate.getText().toString();
        String amount = this.mAmount.getText().toString();
        String description = this.mDesription.getText().toString() ;

        this.iExpensesPresenter.save(date, amount, description,
                (ExpensesSubcategory) this.spinnerSubcategory.getSelectedItem(),
                (ExpensesCategory) this.spinnerCategory.getSelectedItem(),
                (Account) this.mSpinnerPayFrom.getSelectedItem());
        System.out.println("fin de guardar expenses");
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
        this.mDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        this.mAmount.requestFocus();
    }

}
