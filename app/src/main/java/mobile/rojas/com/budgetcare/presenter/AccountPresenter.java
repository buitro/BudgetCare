package mobile.rojas.com.budgetcare.presenter;

import android.content.Context;

import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.CurrencyType;
import mobile.rojas.com.budgetcare.view.IAccountView;



public class AccountPresenter implements IAccountPresenter, IAccountInteractor.OnListener {

    private Context context;
    private IAccountView iAccountView;
    private IAccountInteractor  iAccountInteractor;
    public AccountPresenter(Context context, IAccountView view) {
        this.context = context;
        this.iAccountView = view;
        this.iAccountInteractor = new AccountInteractor();

    }

    @Override
    public void getCurrencyTypes() {
        if (this.iAccountView != null) {
            this.iAccountInteractor.setCurrencyTypes(this);
        }
    }

    @Override
    public void getAccountTypes() {
        if (this.iAccountView != null) {
            this.iAccountInteractor.setAccountTypes(this);
        }
    }

    @Override
    public void save(String name,String balance, String date, Boolean isSum,  AccountType accountType, CurrencyType currencyType) {
        if (this.iAccountView != null) {
            this.iAccountInteractor.save(name,balance, date, isSum, accountType, currencyType,this);
        }
    }

    @Override
    public void onCurrencyType(RealmResults<CurrencyType> list) {
        if (this.iAccountView != null) {
            this.iAccountView.setCurrencyTypes(list);
        }
    }

    @Override
    public void onAccountType(RealmResults<AccountType> list) {
        if (this.iAccountView != null) {
            this.iAccountView.setAccountTypes(list);
        }
    }

    @Override
    public void onDate(int form_field_not_blank) {
        if (this.iAccountView != null) {
            this.iAccountView.setDate(this.context.getString(form_field_not_blank));
        }
    }

    @Override
    public void onBalance(int form_field_not_blank) {
        if (this.iAccountView != null) {
            this.iAccountView.setBalance(this.context.getString(form_field_not_blank));
        }
    }

    @Override
    public void onName(int form_field_not_blank) {
        if (this.iAccountView != null) {
            this.iAccountView.setName(this.context.getString(form_field_not_blank));
        }
    }

    @Override
    public void onSuccess() {
        if (this.iAccountView != null) {
            this.iAccountView.navigationTo();
        }
    }

    @Override
    public void onError() {
        if (this.iAccountView != null) {
            this.iAccountView.showMessage(context.getString(R.string.message_error_save));
        }
    }




}
