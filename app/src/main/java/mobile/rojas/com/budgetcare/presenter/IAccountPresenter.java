package mobile.rojas.com.budgetcare.presenter;

import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.CurrencyType;



public interface IAccountPresenter {
    void getCurrencyTypes();
    void getAccountTypes();
    void save(String name, String balance, String date, Boolean isSum, AccountType accountTypeId, CurrencyType currencyType);
}
