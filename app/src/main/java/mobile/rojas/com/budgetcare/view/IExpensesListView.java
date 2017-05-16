package mobile.rojas.com.budgetcare.view;

import java.util.ArrayList;

import mobile.rojas.com.budgetcare.model.ExpensesCategory;

/**
 * Created by Diego on 30/4/17.
 */

public interface IExpensesListView {
    void createAdapter(ArrayList<ExpensesCategory> list);
    void hideListDefault();
    void showListDefault();
    void changeTitle(String title);


}
