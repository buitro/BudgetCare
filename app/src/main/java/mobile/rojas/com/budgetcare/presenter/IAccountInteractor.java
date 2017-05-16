package mobile.rojas.com.budgetcare.presenter;

import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.CurrencyType;



 interface IAccountInteractor {

    void save(String name,String balance,String date,Boolean isSum, AccountType accountTypeId, CurrencyType currencyType, OnListener accountPresenter);

    interface OnListener{
        void onCurrencyType(RealmResults<CurrencyType> list);
        void onAccountType(RealmResults<AccountType> list);

        void onDate(int form_field_not_blank);

        void onBalance(int form_field_not_blank);

        void onName(int form_field_not_blank);

        void onSuccess();
        void onError();
    }
    void setCurrencyTypes(OnListener listener);
    void setAccountTypes(OnListener listener);
}
