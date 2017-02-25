package com.example.dilraj.dbms;

/*
 * Created by dilraj on 22/2/17.
 */

public class Admins {
    private String name, desig, phone, uid, psswd;

    public Admins(String name, String desig, String phone, String uid, String psswd) {
        this.name = name;
        this.desig = desig;
        this.phone = phone;
        this.uid = uid;
        this.psswd = psswd;
    }

    public Admins(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }
}
