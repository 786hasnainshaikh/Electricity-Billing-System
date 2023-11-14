package com.entities;

public class ElectricityUsage {

    private int usageId;
    private int customerId;
    private String month;
    private double unitsConsumed;

    public ElectricityUsage(int customerId, String month, double unitsConsumed) {
        this.customerId = customerId;
        this.month = month;
        this.unitsConsumed = unitsConsumed;
    }

    // Constructors, getters, and setters


    public int getUsageId() {
        return usageId;
    }

    public void setUsageId(int usageId) {
        this.usageId = usageId;
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

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public static double calculateBill(double unitsConsumed){
        double singleUnit=70.00;
        return  (singleUnit*unitsConsumed);
    }

    @Override
    public String toString() {
        return "ElectricityUsage{" +
                "usageId=" + usageId +
                ", customerId=" + customerId +
                ", month='" + month + '\'' +
                ", unitsConsumed=" + unitsConsumed +
                '}';
    }
}
