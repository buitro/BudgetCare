package mobile.rojas.com.budgetcare.presenter;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.constant.Constants;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.AccountType;
import mobile.rojas.com.budgetcare.model.CurrencyType;
import mobile.rojas.com.budgetcare.model.Record;
import mobile.rojas.com.budgetcare.model.RecordType;


public class AccountInteractor implements IAccountInteractor{

    private Realm  myRealm = Realm.getDefaultInstance();;

    @Override
    public void save(final String name, final String balance, final String date, final Boolean isSum, final AccountType accountTypeTemp, final CurrencyType currencyType, final OnListener listener) {

        boolean error = false;

        if (TextUtils.isEmpty(name)) {
            listener.onName(R.string.form_field_not_blank);
            error = true;
        }

        if (TextUtils.isEmpty(balance)) {
            listener.onBalance(R.string.form_field_not_blank);
            error = true;
        }

        if (TextUtils.isEmpty(date)) {
            listener.onDate(R.string.form_field_not_blank);
            error= true;
        }
        if (!error) {




            myRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    AccountType accountType = realm.where(AccountType.class).equalTo("id", accountTypeTemp.getId()).findFirst();

                    if (accountType != null) {

                        String idAccount = UUID.randomUUID().toString();
                        Account account1 = realm.createObject(Account.class,idAccount);
                        account1.setName(name);
                        account1.setSaldo(Double.valueOf(balance));

                        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");

                        try {
                            Date dateTem = formatter.parse(date);
                            account1.setDateTime(dateTem);


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        account1.setMoney("BOB");
                        //account1.setMoney(currencyType);
                        account1.setSumTotal(isSum);
                        accountType.getAccounts().add(account1);

                        Record record = realm.createObject(Record.class, UUID.randomUUID().toString());
                        record.setBuyFrom(account1);
                        record.setAmount(account1.getSaldo());
                        record.setDate(new Date());
                        record.setDescription("Creación de cuenta");
                        record.setType( realm.where(RecordType.class).equalTo("name", Constants.INGRESO).findFirst());
                        listener.onSuccess();
                    }
                }
            });
        }





    }

    @Override
    public void setCurrencyTypes(OnListener listener) {

        RealmResults<CurrencyType> realmResults = myRealm.where(CurrencyType.class).findAll();

        if (realmResults.size() == 0) {

            myRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    String id = UUID.randomUUID().toString();

                    CurrencyType currencyTypeBob = realm.createObject(CurrencyType.class, id);
                    currencyTypeBob.setName("BOB");

                    String id2 = UUID.randomUUID().toString();

                    CurrencyType currencyTypeUs = realm.createObject(CurrencyType.class, id2);
                    currencyTypeUs.setName("US");
                }
            });

            realmResults = myRealm.where(CurrencyType.class).findAll();
            listener.onCurrencyType(realmResults);

        } else {
            listener.onCurrencyType(realmResults);
        }
    }


    @Override
    public void setAccountTypes(OnListener listener) {
        RealmResults<AccountType> realmResults = myRealm.where(AccountType.class).findAll();
        listener.onAccountType(realmResults);
    }
}
