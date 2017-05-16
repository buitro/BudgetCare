package mobile.rojas.com.budgetcare.view;

import java.util.ArrayList;

import mobile.rojas.com.budgetcare.model.AccountType;

/**
 * Created by Diego on 28/4/17.
 */

public interface IAccountListView {
    void createAdapter(ArrayList<AccountType> list);
}
