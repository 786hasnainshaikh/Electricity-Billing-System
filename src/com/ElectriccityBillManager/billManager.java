package com.ElectriccityBillManager;

import com.entities.*;
import com.connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class billManager {

    static Scanner sc= new Scanner(System.in);

    static Connection con= DaoConnection.getConnection();
    Bill bill;
    static Customers customers;
    static ElectricityUsage electricityUsage;

    public static void  addCustomer(){
        System.out.print("Enter Customer Name: ");
        String customer_name=sc.nextLine();
        System.out.print("Enter customer Address: ");
        String address=sc.nextLine();

        customers=new Customers(customer_name, address);

        try {
            String addCustomerQuery="insert into customers(customer_name, address) values(?,?) ";
            PreparedStatement addCusStatement=con.prepareStatement(addCustomerQuery);

            addCusStatement.setString(1, customers.getCustomerName());
            addCusStatement.setString(2, customers.getAddress());

           int rowsAffected= addCusStatement.executeUpdate();

           if(rowsAffected>0){
               System.out.println("Customer Added Successfully");
           }else {
               System.out.println("Something error!!");
           }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void seeAllCustomers(){
        String fetchCustomerQuery="select * from customers";
        try {
            PreparedStatement fetchCustomerStatement= con.prepareStatement(fetchCustomerQuery);

            ResultSet resultSet = fetchCustomerStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Customer Id:" +  resultSet.getInt("customer_id")+
                                   ", Customer Name:" + resultSet.getString("customer_name")+
                                   ", Customer Address:"+ resultSet.getString("address"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void recordElectricityUsage(){
        System.out.print("Enter the Customer Id:");
        int Cus_Id=sc.nextInt();
        System.out.print("Enter the Month:");
        String month=sc.next();
        System.out.print("Enter the Electric Units Usage:");
        double unit_usage=sc.nextDouble();

        electricityUsage = new ElectricityUsage(Cus_Id, month, unit_usage);

        try{
            String recordUsageQuery="insert into electricity_usage(customer_id, month, units_consumed) values(?,?,?)";
            PreparedStatement usageStatement= con.prepareStatement(recordUsageQuery);

            usageStatement.setInt(1,electricityUsage.getCustomerId());
            usageStatement.setString(2, electricityUsage.getMonth());
            usageStatement.setDouble(3, electricityUsage.getUnitsConsumed());

            int rowsAffected = usageStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Data inserted Successfully");
            }
            else {
                System.out.println("Data not inserted!!!!");
            }
        }catch (Exception e){
            System.out.println("Data is already there for this id " + e);
        }


    }

    public static void seeElectricUsage(){
        System.out.print("Enter the customer Id:");
        int cus_id=sc.nextInt();

        String seeUsageQuery="select * from electricity_usage where customer_id=?";
        try {
            PreparedStatement seeUsageStatement= con.prepareStatement(seeUsageQuery);
            seeUsageStatement.setInt(1, cus_id);

            ResultSet resultSet = seeUsageStatement.executeQuery();

            while (resultSet.next()){
                System.out.println("Custoer id: "+ resultSet.getInt("customer_id")+
                                   ", Month: " + resultSet.getString("month")+
                                   ", Units consumed: " +resultSet.getDouble("units_consumed"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void  generateBill(){

        System.out.println("Enter the Customer id:");
        int Cus_id=sc.nextInt();

        String name = null;
        String month = null;
        double units_consumed=0.0;

        try {
            String cus_name_query="select customer_name from customers where customer_id=?";
            String getMonth_query="select month from electricity_usage where customer_id=?";
            String units_consumed_query= "select units_consumed from electricity_usage where customer_id=?";
            String billQuery="insert into bills (customer_id, month, amount_due) values(?,?,?)";

            PreparedStatement cus_name_Statement= con.prepareStatement(cus_name_query);
            PreparedStatement getMonth_Statement= con.prepareStatement(getMonth_query);
            PreparedStatement units_consumed_statement= con.prepareStatement(units_consumed_query);

            // fetch name from customer database table
            cus_name_Statement.setInt(1, Cus_id);
            ResultSet resultSetName = cus_name_Statement.executeQuery();

            if (resultSetName.next()){
                name=resultSetName.getString("customer_name");
            }



            // fetch month from electricity_usage database table
            getMonth_Statement.setInt(1,Cus_id);
            ResultSet resultSetMonth = getMonth_Statement.executeQuery();
            if (resultSetMonth.next()){
                 month=resultSetMonth.getString("month");
            }



            // fetch units_consumed from electricity_usage database table
            units_consumed_statement.setInt(1,Cus_id);
            ResultSet resultSetUnits = units_consumed_statement.executeQuery();
            if (resultSetUnits.next()){
                units_consumed =resultSetUnits.getDouble("units_consumed");
            }

            if (name ==null && month==null){
                System.out.println("there is no data with this id");
            }

            // insert data into bill database table
            PreparedStatement billStatement= con.prepareStatement(billQuery);
            // generating total bill due
            double calculateBill = ElectricityUsage.calculateBill(units_consumed);

            billStatement.setInt(1,Cus_id);
            billStatement.setString(2, month);
            billStatement.setDouble(3, calculateBill);

            int i = billStatement.executeUpdate();
            if(i>0){
                System.out.println("customer ID:" + Cus_id +
                                   ", Customer Name: "+ name +
                                   ", Month:  " + month+
                                   ", Units Consumed: "+ units_consumed+
                                   ", Amount Due: " + calculateBill);
            }
            else {
                System.out.println("Error to Generate Bill!!!! ");

            }



        }catch (Exception e){

        }

    }



}
