package com.example.androidnetworking.model;

public class login {
    String email,password;

    public login() {
    }

    public login(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public login(String email) {
        this.email = email;
    }
}
