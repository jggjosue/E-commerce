package com.app.ecommerce.v1.entities;

public class Product {

    public String title;
    public String price;
    public String image;

    public Product(String title, String price, String image) {
        this.title = title;
        this.price = "$" + price;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
