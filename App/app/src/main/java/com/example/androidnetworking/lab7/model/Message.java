package com.example.androidnetworking.lab7.model;

public class Message {
    private String data;
    private Boolean fromMe;

    public Message() {
    }

    public Message(String data,Boolean fromMe) {
        this.data = data;
        this.fromMe = fromMe;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getFromMe() {
        return fromMe;
    }

    public void setFromMe(Boolean fromMe) {
        this.fromMe = fromMe;
    }
}
