package mobile.rojas.com.budgetcare.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;


public class AccountType extends RealmObject{
    @PrimaryKey
    private String id;
    private String name;
    private RealmList<Account> accounts;
    @Ignore
    private ArrayList<Account> accountsTemp;
    @Ignore
    private Double sum;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public ArrayList<Account> getAccountsTemp() {
        return accountsTemp;
    }

    public void setAccountsTemp(ArrayList<Account> accountsTemp) {
        this.accountsTemp = accountsTemp;
    }

    public RealmList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(RealmList<Account> accounts) {
        this.accounts = accounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double sumTotal(){
        Double sum = 0.0;

        if (accounts != null){
            for (Account account: accounts) {
                if (account.getSaldo() != null) {
                    sum += account.getSaldo();
                }
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        return name;
    }
}
