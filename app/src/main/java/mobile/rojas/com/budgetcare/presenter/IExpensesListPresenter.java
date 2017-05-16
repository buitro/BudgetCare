package mobile.rojas.com.budgetcare.presenter;

import java.util.ArrayList;

import mobile.rojas.com.budgetcare.model.ExpensesCategory;

public interface IExpensesListPresenter {
    void loadExpensesList(ArrayList<ExpensesCategory> list);
    ArrayList<ExpensesCategory> getExpensesList();
}
