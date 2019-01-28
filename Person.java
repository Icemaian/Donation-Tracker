/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

/**
 *
 * @author Isaac_Craig
 */
public class Person {

    private String first, last, telephone, address, state, zip, date;
    private double amount, goal;

    /*
    public Address() {
        this("","","","","","");
    }
     */
    // Donator ****************************************************************
    public Person() {
        this("", "", "", "", "", "", "", 0);
    }

    public Person(String first, String last, String telephone, String address,
            String state, String zip, String date, double amount) {
        setFirst(first);
        setLast(last);
        setTelephone(telephone);
        setAddress(address);
        setState(state);
        setZip(zip);
        setDate(date);
        setAmount(amount);
    }

    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }
    //*************************************************************************

    // Missionary *************************************************************
    public Person(String first, String last, String telephone, String address,
            String state, String zip, double goal) {
        setFirst(first);
        setLast(last);
        setTelephone(telephone);
        setAddress(address);
        setState(state);
        setZip(zip);
        setGoal(goal);
    }
    //*************************************************************************

    // getter & setter methods**************************************************
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    //**************************************************************************
}
