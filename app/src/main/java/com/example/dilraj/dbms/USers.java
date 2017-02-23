package com.example.dilraj.dbms;

/**
 * Created by dilraj on 21/2/17.
 */

public class USers {

    private String name, branch, roll, phone, address, psswd;

    public USers(String name, String branch, String roll, String phone, String address, String psswd) {
        this.name = name;
        this.branch = branch;
        this.roll = roll;
        this.phone = phone;
        this.address = address;
        this.psswd = psswd;
    }

    public USers(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }
}
