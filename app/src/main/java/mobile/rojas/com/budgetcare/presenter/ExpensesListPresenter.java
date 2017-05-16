package mobile.rojas.com.budgetcare.presenter;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.Record;
import mobile.rojas.com.budgetcare.view.IExpensesListView;


public class ExpensesListPresenter implements IExpensesListPresenter {
    private Context context;
    private IExpensesListView iExpensesListView;
    private Realm realm;
    public ExpensesListPresenter(Context context, IExpensesListView iExpensesListView) {
        this.context = context;
        this.iExpensesListView = iExpensesListView;
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void loadExpensesList(ArrayList<ExpensesCategory> list) {
        if (this.iExpensesListView != null ) {
            this.iExpensesListView.hideListDefault();
            if (list.size() == 0) {
                this.iExpensesListView.showListDefault();
            }

            this.iExpensesListView.createAdapter(list);
        }
    }

    @Override
    public ArrayList<ExpensesCategory> getExpensesList() {
        RealmResults<ExpensesCategory> expensesCategories = realm.where(ExpensesCategory.class).findAll();//greaterThan("subcategory.expenses.amount", 0).findAll();
        ArrayList<ExpensesCategory> categorias = new ArrayList<>();

        RealmResults<Record> expenses = realm.where(Record.class).findAll();
        //ArrayList<String> idExpenses = new ArrayList<>();

        //for (Record expensesTem: expenses) {
        //    idExpenses.add(expensesTem.getId());
        //}
        Double sumTotal = 0.0;
        for(ExpensesCategory cateTem:expensesCategories) {
            RealmResults<Record> expenses1 = realm.where(Record.class).equalTo("expensesCategory.name", cateTem.getName()).findAll();
            if (expenses1.size() > 0) {
                ArrayList<Record> listaGastos = new ArrayList<>();
                ExpensesCategory miCategoria = new ExpensesCategory();
                miCategoria.setName(cateTem.getName());
                miCategoria.setSum(expenses1.sum("amount").doubleValue());
                for (Record expTem : expenses1) {
                    listaGastos.add(expTem);
                    sumTotal  = sumTotal + expTem.getAmount();
                    //miCategoria.getExpenses().add(expTem);
                }
                miCategoria.setExpenses(listaGastos);
                categorias.add(miCategoria);
            }
            //RealmResults<Record> expenses2 = realm.where(Record.class).findAll();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");
        this.iExpensesListView.changeTitle(simpleDateFormat.format(new Date())+  " Bs." + sumTotal);

        return categorias;
    }
}
