package com.app.ecommerce.v1.entities;

public class Product {

    public String title;
    public String price;
    public int image;

    public Product(String title, String price, int image) {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
