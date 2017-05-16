package mobile.rojas.com.budgetcare.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;



public class Account extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String money;
    @Ignore
    private Double saldo;
    private Date dateTime;
    private boolean sumTotal;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(boolean sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Override
    public String toString() {
        return name;
    }
}
