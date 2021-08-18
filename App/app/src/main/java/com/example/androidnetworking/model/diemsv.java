package com.example.androidnetworking.model;

public class diemsv {
    private String name,diema,diemb,diemc,diemd;

    public diemsv() {
    }

    public diemsv(String name,String diema,String diemab,String diemc,String diemad) {
        this.name = name;
        this.diema = diema;
        this.diemb = diemab;
        this.diemc = diemc;
        this.diemd = diemad;
    }

    public diemsv(String name,double diema,double diemb,double diemc,double diemd) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiema() {
        return diema;
    }

    public void setDiema(String diema) {
        this.diema = diema;
    }

    public String getDiemab() {
        return diemb;
    }

    public void setDiemab(String diemab) {
        this.diemb = diemab;
    }

    public String getDiemc() {
        return diemc;
    }

    public void setDiemc(String diemc) {
        this.diemc = diemc;
    }

    public String getDiemad() {
        return diemd;
    }

    public void setDiemad(String diemad) {
        this.diemd = diemad;
    }
}
