package com.example.budgetifier;

public class ExpenseModel {
    private String expenseid;
    private String note;
    private String category;
    private String type;
    private long amount;
    private long time;
    private String uid;

    public ExpenseModel(String expenseid, String note, String category, String type, long amount, long time, String uid) {
        this.expenseid = expenseid;
        this.note = note;
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.uid = uid;
    }

    public String getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(String expenseid) {
        this.expenseid = expenseid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
