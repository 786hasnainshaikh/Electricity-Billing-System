package com.entities;

public class Bill {
    private int billId;
    private int customerId;
    private String month;
    private double amountDue;

    public Bill(int customerId, String month, double amountDue) {
        this.customerId = customerId;
        this.month = month;
        this.amountDue = amountDue;
    }

    // Constructors, getters, and setters


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", customerId=" + customerId +
                ", month='" + month + '\'' +
                ", amountDue=" + amountDue +
                '}';
    }
}
