package com.zaptrapp.nishanth.databasetesting.Model;

/**
 * Created by Nishanth on 05-Sep-17.
 */

public class Data {
    String name;
    long amount;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Data() {
    }

    public Data(String name, long amount, String description) {
        this.name = name;
        this.amount = amount;
        this.description = description;
    }
}
