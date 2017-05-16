package mobile.rojas.com.budgetcare.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.constant.Constants;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.ExpensesSubcategory;
import mobile.rojas.com.budgetcare.model.Record;
import mobile.rojas.com.budgetcare.model.RecordType;

/**
 * Created by Diego on 30/4/17.
 */

public class ExpensesInteractor implements IExpensesInteractor {

    @Override
    public void save(String date, String amount, String description, ExpensesSubcategory expensesSubcategory, ExpensesCategory expensesCategory, Account account, OnListener listener) {
        boolean error = false;

        //Validación del campo fecha
        if (TextUtils.isEmpty(date)) {
            listener.onDate(R.string.form_field_not_blank);
            error = true;
        }

        if (TextUtils.isEmpty(amount)) {
            listener.onAmount(R.string.form_field_not_blank);
            error = true;
        } else if (account == null) {
            listener.onAccount(R.string.form_field_not_blank_account);
            error = true;
        } else if ( !isAmountValid(account, Double.valueOf(amount))){
            listener.onAmount(R.string.insufficient_balance_expenses);
            error = true;
        }


        if (TextUtils.isEmpty(description)) {
            listener.onDescription(R.string.form_field_not_blank);
            error = true;
        }

        if (!error) {

            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");

            try {
                Date newDate = formatter.parse(date);
                System.out.println("La fecha convertida");
                System.out.println(new Gson().toJson(newDate));
                saveExpenses( newDate, amount, description,  expensesSubcategory, expensesCategory, account,  listener);

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

    }

    private boolean isAmountValid(Account account, Double amountExpenses) {

        Realm realm = Realm.getDefaultInstance();
        Double amountIngreso = realm.where(Record.class).equalTo("buyFrom.id", account.getId()).equalTo("type.name",Constants.INGRESO).sum("amount").doubleValue();
        Double amountEgreso = realm.where(Record.class).equalTo("buyFrom.id", account.getId()).equalTo("type.name",Constants.EGRESO).sum("amount").doubleValue();

        Double amount = amountIngreso - amountEgreso;

        if (amount >= amountExpenses ){
            return true;
        } else {
            return false;
        }

    }

    private void saveExpenses(final Date date,final String amount,final String description, final ExpensesSubcategory expensesSubcategory, final ExpensesCategory expensesCategory, final Account account, final OnListener listener) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Record expenses = realm.createObject(Record.class, UUID.randomUUID().toString());
                expenses.setAmount(Double.valueOf(amount));
                expenses.setDate(date);
                expenses.setBuyFrom(account);
                expenses.setDescription(description);
                expenses.setExpensesSubcategory(expensesSubcategory);
                expenses.setExpensesCategory(expensesCategory);
                expenses.setType(realm.where(RecordType.class).equalTo("name",Constants.EGRESO).findFirst());

                listener.onSuccess();

            }
        });

    }


}
