package com.zjl.bookkeeping.db;

public class Item {
    int id;
    String name;
    String reason;
    float money;
    String time;
    int year;
    int month;
    int day;
    int kind;
    int imageId;


    public Item() {

    }

    public Item(int id, String name, String reason, float money, String time, int year,
                int month, int day, int kind, int imageId) {
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.money = money;
        this.time = time;
        this.kind = kind;
        this.year = year;
        this.month = month;
        this.day = day;
        this.imageId = imageId;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}


