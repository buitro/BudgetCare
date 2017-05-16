package mobile.rojas.com.budgetcare.view;

import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.CurrencyType;

/**
 * Created by Diego on 27/4/17.
 */

public interface IAccountView {
    void setAccountTypes(RealmResults<AccountType> list);
    void setCurrencyTypes(RealmResults<CurrencyType> list);
    void setName(String message);
    void setBalance(String message);
    void setDate(String string);
    void navigationTo();
    void showMessage(String string);
}
