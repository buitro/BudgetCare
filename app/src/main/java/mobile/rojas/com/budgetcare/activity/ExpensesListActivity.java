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

import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.adapter.ExpandableListExpenses;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.presenter.ExpensesListPresenter;
import mobile.rojas.com.budgetcare.presenter.IExpensesListPresenter;
import mobile.rojas.com.budgetcare.view.IExpensesListView;

public class ExpensesListActivity extends AppCompatActivity implements IExpensesListView {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private IExpensesListPresenter presenter;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);
        this.expandableListView = (ExpandableListView) findViewById(R.id.expandableExpensesList);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarExpensesList);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        this.view = (View) findViewById(R.id.sin_gasto);
        this.presenter = new ExpensesListPresenter(this.getApplicationContext(), this);
        this.presenter.loadExpensesList(this.presenter.getExpensesList());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Activity activity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, ExpensesActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void createAdapter(ArrayList<ExpensesCategory> list) {
        adapter = new ExpandableListExpenses(this,list);
        this.expandableListView.setAdapter(adapter);
    }

    @Override
    public void hideListDefault() {
        view.setVisibility(View.GONE);
    }

    @Override
    public void showListDefault() {
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeTitle(String title) {
        setTitle(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.loadExpensesList(this.presenter.getExpensesList());


    }
}
