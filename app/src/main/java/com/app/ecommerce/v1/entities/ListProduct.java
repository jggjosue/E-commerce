package com.app.ecommerce.v1.entities;

import java.util.ArrayList;
import java.util.List;

public class ListProduct {
    public static ListProduct INSTANCE = null;
    private List<Product> listProduct = null;

    public static synchronized ListProduct INSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ListProduct();
        return INSTANCE;
    }

    public void init() {
        listProduct = new ArrayList<>();
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Product product) {
        listProduct.add(product);
    }
}
