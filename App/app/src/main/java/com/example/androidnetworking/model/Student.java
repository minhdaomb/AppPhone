package com.example.androidnetworking.model;

public class Student {
    String name;
    double luong,thunhap;

    public Student() {
    }

    public Student(String name,double luong,double thunhap) {
        this.name = name;
        this.luong = luong;
        this.thunhap = thunhap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public double getThunhap() {
        return thunhap;
    }

    public void setThunhap(double thunhap) {
        this.thunhap = thunhap;
    }
}
