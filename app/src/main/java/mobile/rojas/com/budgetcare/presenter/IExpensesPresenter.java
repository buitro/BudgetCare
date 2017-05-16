package mobile.rojas.com.budgetcare.presenter;

import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.ExpensesSubcategory;


public interface IExpensesPresenter {

    void save(String date, String amount, String description, ExpensesSubcategory expensesSubcategory, ExpensesCategory expensesCategory, Account account);
    void loadPayFrom();
}
