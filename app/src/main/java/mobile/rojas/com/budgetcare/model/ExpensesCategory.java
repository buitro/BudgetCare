package mobile.rojas.com.budgetcare.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;



public class ExpensesCategory extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private RealmList<ExpensesSubcategory> subcategory;
    @Ignore
    private Double sum;
    @Ignore
    private ArrayList<Record> expenses;

    public ArrayList<Record> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Record> expenses) {
        this.expenses = expenses;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public RealmList<ExpensesSubcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(RealmList<ExpensesSubcategory> subcategory) {
        this.subcategory = subcategory;
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

    @Override
    public String toString() {
        return name;
    }

    public Double sumTotal(){
        Double sum = 0.0;

        if (subcategory != null){
            for (ExpensesSubcategory account: subcategory) {
//                if (account.get() != null) {
//                    sum += account.getSaldo();
//                }
            }
        }
        return sum;
    }
}
