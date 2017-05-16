package mobile.rojas.com.budgetcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.adapter.MainMenuAdapter;
import mobile.rojas.com.budgetcare.constant.Constants;
import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.Budget;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.ExpensesSubcategory;
import mobile.rojas.com.budgetcare.model.Record;
import mobile.rojas.com.budgetcare.model.RecordType;
import mobile.rojas.com.budgetcare.presenter.IMainPresenter;
import mobile.rojas.com.budgetcare.presenter.MainPresenter;
import mobile.rojas.com.budgetcare.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView {

    private Realm myRealm;
    private IMainPresenter iMainPresenter;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myRealm = Realm.getDefaultInstance();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_menu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");

        setTitle(this.getString(R.string.app_name)+" "+ simpleDateFormat.format(new Date()) );
        RealmResults<ExpensesCategory> list = myRealm.where(ExpensesCategory.class).findAll();
        if (list.size() == 0) {
            insertData();
        }

        this.iMainPresenter = new MainPresenter(this.getApplicationContext(),this);
        this.iMainPresenter.loadAdapterMenu(this.iMainPresenter.getMenus());


    }

    private void insertData() {

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RecordType recordType = realm.createObject(RecordType.class, UUID.randomUUID().toString());
                recordType.setName("Ingreso");

                RecordType recordType1 = realm.createObject(RecordType.class,UUID.randomUUID().toString());
                recordType1.setName("Egreso");

                AccountType accountType = realm.createObject(AccountType.class,  UUID.randomUUID().toString());
                accountType.setName("Ahorro");
                AccountType accountType1 = realm.createObject(AccountType.class,  UUID.randomUUID().toString());
                accountType1.setName("Efectivo");

                ExpensesCategory expensesCategory = realm.createObject(ExpensesCategory.class, UUID.randomUUID().toString());
                expensesCategory.setName("Alimentación");
                ExpensesSubcategory expensesSubcategory = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory.setName("Productos Alimenticios");
                expensesCategory.getSubcategory().add(expensesSubcategory);
                ExpensesSubcategory expensesSubcategory1 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory1.setName("Restaurante");
                expensesCategory.getSubcategory().add(expensesSubcategory1);

                ExpensesCategory expensesCategory1 = realm.createObject(ExpensesCategory.class, UUID.randomUUID().toString());
                expensesCategory1.setName("Transporte");
                ExpensesSubcategory expensesSubcategory2 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory2.setName("Transporte público");
                expensesCategory1.getSubcategory().add(expensesSubcategory2);
                ExpensesSubcategory expensesSubcategory3 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory3.setName("Gasolina");
                expensesCategory1.getSubcategory().add(expensesSubcategory3);
                ExpensesSubcategory expensesSubcategory4 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory4.setName("Aparcamiento");
                expensesCategory1.getSubcategory().add(expensesSubcategory4);
                ExpensesSubcategory expensesSubcategory5 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory5.setName("Lavado del auto");
                expensesCategory1.getSubcategory().add(expensesSubcategory5);

                ExpensesCategory expensesCategory2 = realm.createObject(ExpensesCategory.class, UUID.randomUUID().toString());
                expensesCategory2.setName("Casa");
                ExpensesSubcategory expensesSubcategory6 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory6.setName("Expensas");
                expensesCategory2.getSubcategory().add(expensesSubcategory6);
                ExpensesSubcategory expensesSubcategory7 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory7.setName("Alquiler");
                expensesCategory2.getSubcategory().add(expensesSubcategory7);
                ExpensesSubcategory expensesSubcategory8 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory8.setName("Limpieza del hogar");
                expensesCategory2.getSubcategory().add(expensesSubcategory8);

                ExpensesCategory expensesCategory3 = realm.createObject(ExpensesCategory.class, UUID.randomUUID().toString());
                expensesCategory3.setName("Servicios");
                ExpensesSubcategory expensesSubcategory9 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory9.setName("Electricidad");
                expensesCategory3.getSubcategory().add(expensesSubcategory9);
                ExpensesSubcategory expensesSubcategory10 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory10.setName("Internet");
                expensesCategory3.getSubcategory().add(expensesSubcategory10);
                ExpensesSubcategory expensesSubcategory11 = realm.createObject(ExpensesSubcategory.class, UUID.randomUUID().toString());
                expensesSubcategory11.setName("Gas/Calefacción");
                expensesCategory3.getSubcategory().add(expensesSubcategory11);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRealm.close();
    }

    public void showAccount(View view) {
        Intent i = new Intent(this.getApplicationContext(), AccountListActivity.class);
        startActivity(i);
    }

    public void showExpenses(View view) {
        Intent i = new Intent(this.getApplicationContext(), ExpensesListActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.iMainPresenter.loadAdapterMenu(this.iMainPresenter.getMenus());
    }

    @Override
    public void setAdapterMenu(ArrayList<mobile.rojas.com.budgetcare.model.Menu> list) {
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(list, this.getApplicationContext());
        this.mRecyclerView.setAdapter(mainMenuAdapter);

        PieChart pieChart ;
        ArrayList<PieEntry> entries ;
        PieDataSet pieDataSet ;
        PieData pieData ;

        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();
        RealmResults<Budget> budget = this.myRealm.where(Budget.class).findAll();
        float presupuestoTotal = 0;
        if (budget.size() > 0) {
            presupuestoTotal = budget.get(budget.size()-1).getAmount().floatValue();
        }
        float gastoTotal = this.myRealm.where(Record.class).equalTo("type.name", Constants.EGRESO).sum("amount").floatValue();
        float gastoPercent =  (gastoTotal*100)/presupuestoTotal;
        entries.add(new PieEntry(gastoPercent, this.getApplicationContext().getString(R.string.menu_gastos) + "("+gastoTotal+")" ,gastoTotal));
        entries.add(new PieEntry((100-gastoPercent), this.getApplicationContext().getString(R.string.menu_presupuesto) + "("+presupuestoTotal+")",presupuestoTotal));

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);

        pieChart.animateY(3000);
    }
}
