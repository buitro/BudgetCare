package mobile.rojas.com.budgetcare.presenter;

import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.ExpensesSubcategory;

/**
 * Created by Diego on 30/4/17.
 */

public interface IExpensesInteractor {
    interface OnListener{
        void onSave();
        void onDate(int form_field_not_blank);

        void onAmount(int form_field_not_blank);

        void onDescription(int form_field_not_blank);

        void onSuccess();

        void onAccount(int form_field_not_blank_account);
    }
    void save(String date, String amount, String description, ExpensesSubcategory expensesSubcategory, ExpensesCategory expensesCategorym ,Account account, OnListener listener);
}
