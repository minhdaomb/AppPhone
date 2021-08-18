package com.example.androidnetworking.model;

public class category {
    String category_name;
    private Integer id;
    public category() {
    }

    public category(String category_name,Integer id) {
        this.category_name = category_name;
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
