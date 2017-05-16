package mobile.rojas.com.budgetcare.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ExpensesSubcategory extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    //private RealmList<Record> expenses;

//    public RealmList<Record> getExpenses() {
//        return expenses;
//    }
//
//    public void setExpenses(RealmList<Record> expenses) {
//        this.expenses = expenses;
//    }

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
}
