package mobile.rojas.com.budgetcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.presenter.BudgetPresenter;
import mobile.rojas.com.budgetcare.presenter.IBudgetPresenter;
import mobile.rojas.com.budgetcare.view.IBudgetView;

public class BudgetActivity extends AppCompatActivity implements IBudgetView {

    private EditText mAmount;
    private IBudgetPresenter iBudgetPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mAmount = (EditText) findViewById(R.id.budget_form_amount);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy MMM");

        setTitle(this.getString(R.string.title_activity_budget) + " " + simpleDateFormat.format(new Date()) );

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.iBudgetPresenter = new BudgetPresenter(this.getApplicationContext(),this);


    }

    public void saveBudget(View view) {
        this.iBudgetPresenter.save(this.mAmount.getText().toString());
    }

    @Override
    public void setAmount(String message) {
        this.mAmount.setError(message);
    }

    @Override
    public void navigateTo() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}
