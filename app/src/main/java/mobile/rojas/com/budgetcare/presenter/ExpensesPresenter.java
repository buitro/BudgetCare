package mobile.rojas.com.budgetcare.presenter;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.ExpensesSubcategory;
import mobile.rojas.com.budgetcare.view.IExpensesView;

/**
 * Created by Diego on 30/4/17.
 */

public class ExpensesPresenter implements IExpensesPresenter, IExpensesInteractor.OnListener{
    private Context context;
    private IExpensesView iExpensesView;
    private IExpensesInteractor iExpensesInteractor;
    private Realm realm;
    public ExpensesPresenter(Context context, IExpensesView iExpensesView) {
        this.context = context;
        this.iExpensesView = iExpensesView;
        this.iExpensesInteractor = new ExpensesInteractor();
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onDate(int form_field_not_blank) {
        if (this.iExpensesView != null) {
            this.iExpensesView.setDate(this.context.getString(form_field_not_blank));
        }
    }

    @Override
    public void onAmount(int form_field_not_blank) {
        if (this.iExpensesView != null) {
            this.iExpensesView.setAmount(this.context.getString(form_field_not_blank));
        }

    }

    @Override
    public void onDescription(int form_field_not_blank) {
        if (this.iExpensesView != null) {
            this.iExpensesView.setDescription(this.context.getString(form_field_not_blank));
        }
    }

    @Override
    public void onSuccess() {
        if (this.iExpensesView != null){
            this.iExpensesView.navigationTo();
        }
    }

    @Override
    public void onAccount(int form_field_not_blank_account) {
        if (this.iExpensesView != null) {
            this.iExpensesView.setAccount(this.context.getString(form_field_not_blank_account));
        }
    }

    @Override
    public void save(String date, String amount, String description, ExpensesSubcategory expensesSubcategory, ExpensesCategory expensesCategory, Account account) {
        if (iExpensesView != null) {
            this.iExpensesInteractor.save(date,amount,description, expensesSubcategory, expensesCategory, account, this);
        }
    }

    @Override
    public void loadPayFrom() {
        if (this.iExpensesView != null) {
            RealmResults<Account> listAccount = realm.where(Account.class).findAll();
            this.iExpensesView.loadPayFrom(listAccount);
        }
    }
}
