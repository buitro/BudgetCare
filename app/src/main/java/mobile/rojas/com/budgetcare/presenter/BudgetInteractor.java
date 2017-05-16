package mobile.rojas.com.budgetcare.presenter;

import android.text.TextUtils;

import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.Budget;



public class BudgetInteractor implements IBudgetInteractor {
    @Override
    public void onSave(String amount, OnListener listener) {
        boolean error = false;

        if (TextUtils.isEmpty(amount)) {
            error = true;
            listener.onAmount(R.string.form_field_not_blank);
        }

        if (!error) {
            save(Double.valueOf(amount), listener);
        }
    }

    private void save(final Double amount, final OnListener listener) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Budget budget = realm.createObject(Budget.class, UUID.randomUUID().toString());
                budget.setAmount(amount);
                budget.setDate(new Date());
                listener.onSuccess();

            }
        });
    }


}
