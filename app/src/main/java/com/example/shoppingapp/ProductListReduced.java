package com.example.shoppingapp;

/**
 * Created by Jason on 11/29/2017.
 * Adapted from: http://www.learn-android.com/2011/11/22/lots-of-lists-custom-adapter/
 */

public final class ProductListReduced {

    private final String name;
    private final String quantity;
    private final String aisle;

    public ProductListReduced(final String product, final String quantity, final String aisle) {
        this.name = product;
        this.quantity = quantity;
        this.aisle = aisle;
    }

    public String getProduct() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAisle() { return aisle; }

}