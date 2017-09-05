package com.zaptrapp.nishanth.databasetesting.Model;

import android.database.Cursor;

/**
 * Created by Nishanth on 05-Sep-17.
 */

public class Data {
    String name;
    double amount;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public Data(String name, double amount, String description) {

        this.name = name;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return name+" Rs: "+amount+" Reason: "+description;
    }
}
