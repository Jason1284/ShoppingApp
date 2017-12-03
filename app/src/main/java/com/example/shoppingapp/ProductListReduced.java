package com.example.shoppingapp;

/**
 * Created by Jason on 11/29/2017.
 * Adapted from: http://www.learn-android.com/2011/11/22/lots-of-lists-custom-adapter/
 */

public final class ProductListReduced {

    private final String name;
    private final String price;
    private final String aisle;

    public ProductListReduced(final String product, final String price, final String aisle) {
        this.name = product;
        this.price = price;
        this.aisle = aisle;
    }

    public String getProduct() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getAisle() { return aisle; }

}