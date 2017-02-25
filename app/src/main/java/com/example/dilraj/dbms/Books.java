package com.example.dilraj.dbms;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class Books {
    private String id, name, author;
    private int status;

    public Books(String id, String name, String author, int status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
