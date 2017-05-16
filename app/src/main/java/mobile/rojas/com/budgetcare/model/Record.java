package mobile.rojas.com.budgetcare.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



public class Record extends RealmObject{
    @PrimaryKey
    private String id;
    private Date date;
    private Double amount;
    private Account buyFrom;
    private String description;
    private ExpensesCategory expensesCategory;
    private ExpensesSubcategory expensesSubcategory;
    private RecordType type;

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public ExpensesCategory getExpensesCategory() {
        return expensesCategory;
    }

    public void setExpensesCategory(ExpensesCategory expensesCategory) {
        this.expensesCategory = expensesCategory;
    }

    public ExpensesSubcategory getExpensesSubcategory() {
        return expensesSubcategory;
    }

    public void setExpensesSubcategory(ExpensesSubcategory expensesSubcategory) {
        this.expensesSubcategory = expensesSubcategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getBuyFrom() {
        return buyFrom;
    }

    public void setBuyFrom(Account buyFrom) {
        this.buyFrom = buyFrom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
