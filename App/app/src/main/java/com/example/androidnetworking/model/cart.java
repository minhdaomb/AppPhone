package com.example.androidnetworking.model;

public class cart {
    private double price;
    private String image_url, product_name;
    private Integer id;

    public cart() {
    }

    public cart(double price,String image_url,String product_name,Integer id) {
        this.price = price;
        this.image_url = image_url;
        this.product_name = product_name;
        this.id = id;
    }

    public cart(double price,String image_url,String product_name) {
        this.price = price;
        this.image_url = image_url;
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
