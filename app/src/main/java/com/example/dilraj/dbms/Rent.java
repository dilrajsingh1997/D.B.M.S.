package com.example.dilraj.dbms;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class Rent {
    private String userid,bookid,date;

    public Rent(String userid, String bookid, String date) {
        this.userid = userid;
        this.bookid = bookid;
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
