package com.example.shoppingapp;

/**
 * Created by Jason on 11/29/2017.
 * Adapted from: http://www.learn-android.com/2011/11/22/lots-of-lists-custom-adapter/
 */

public final class ProductList {

    private final String quantity;
    private final String name;
    private final String price;

    public ProductList(final String quantity, final String product, final String price) {
        this.quantity = quantity;
        this.name = product;
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}