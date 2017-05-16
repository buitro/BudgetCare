package mobile.rojas.com.budgetcare.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.activity.AccountListActivity;
import mobile.rojas.com.budgetcare.activity.BudgetActivity;
import mobile.rojas.com.budgetcare.activity.ExpensesListActivity;
import mobile.rojas.com.budgetcare.constant.Constants;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.Budget;
import mobile.rojas.com.budgetcare.model.Menu;
import mobile.rojas.com.budgetcare.model.Record;
import mobile.rojas.com.budgetcare.view.IMainView;

import static mobile.rojas.com.budgetcare.R.drawable.cuentas;

public class MainPresenter implements IMainPresenter{

    private Context context;
    private IMainView iMainView;
    private Realm realm;

    public MainPresenter(Context context, IMainView iMainView) {
        this.context = context;
        this.iMainView = iMainView;
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void loadAdapterMenu(ArrayList<Menu> list) {
        if (this.iMainView != null) {
            this.iMainView.setAdapterMenu(list);
        }
    }

    @Override
    public ArrayList<Menu> getMenus() {
        ArrayList<Menu> menus = new ArrayList<>();
                    //Menu Gastos
                    Menu menuGastos = new Menu();
                    menuGastos.setName(context.getString(R.string.menu_gastos));
                    menuGastos.setIcon(R.drawable.gastos);
                    menuGastos.setIntent( new Intent(this.context, ExpensesListActivity.class));
                    Double expenses = realm.where(Record.class).equalTo("type.name", Constants.EGRESO).sum("amount").doubleValue();
                    menuGastos.setSum(expenses);
                    menus.add(menuGastos);

                    //Menu Presupuesto
                    Menu menuPresupuesto =  new Menu();
                    menuPresupuesto.setName(context.getString(R.string.menu_presupuesto));
                    menuPresupuesto.setIcon(R.drawable.presupuestos);
                    RealmResults<Budget> budget = realm.where(Budget.class).findAll();
                    if (budget.size() > 0) {
                        menuPresupuesto.setSum(budget.get(budget.size()-1).getAmount());
                    }
                    menuPresupuesto.setIntent(new Intent(this.context, BudgetActivity.class));
                    menus.add(menuPresupuesto);

                    //Menu Cuentas
                    Menu menuCuentas =  new Menu();
                    menuCuentas.setName(context.getString(R.string.menu_cuentas));
                    menuCuentas.setIcon(cuentas);
                    menuCuentas.setIntent( new Intent(this.context, AccountListActivity.class));
                    RealmResults<Account> accounts = realm.where(Account.class).equalTo("sumTotal", true).findAll();
                    Double sumAccountVisible = 0.0;

                    for (Account temp : accounts) {
                        Double amountIngreso = realm.where(Record.class).equalTo("buyFrom.id", temp.getId()).equalTo("type.name",Constants.INGRESO).sum("amount").doubleValue();
                        Double amountEgreso = realm.where(Record.class).equalTo("buyFrom.id", temp.getId()).equalTo("type.name",Constants.EGRESO).sum("amount").doubleValue();
                        sumAccountVisible = sumAccountVisible + amountIngreso - amountEgreso;
                    }

                    menuCuentas.setSum(sumAccountVisible);
        menus.add(menuCuentas);

        return menus;
    }
}
