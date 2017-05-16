package mobile.rojas.com.budgetcare.view;

import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.ExpensesSubcategory;

/**
 * Created by Diego on 30/4/17.
 */

public interface IExpensesView {

    void loadCategory(RealmResults<ExpensesCategory> list);
    void loadSubcategory(RealmResults<ExpensesSubcategory> list);
    void loadPayFrom(RealmResults<Account> list);
    void setAmount(String message);
    void setDate(String string);
    void setDescription(String string);
    void navigationTo();

    void setAccount(String string);
}
