package com.example.shoppingapp;

/**
 * Product class helps to manage the database.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class Product {
    private String name;
    private String price;
    private String aisle;
    private Integer value;
    private String quantity;

    /**
     *CONSTRUCTORS
     */
    public Product(){}
    public Product(String name, String price, String aisle, String quantity){
        this.name = name;
        this.price = price;
        this.aisle = aisle;
        this.value = 1;
        this.quantity = quantity;
    }

    /**
     *GETTERS
     */
    public String getName(){
        return name;
    }

    public String getPrice(){
        return price;
    }

    public String getAisle(){
        return aisle;
    }

    public Integer getValue(){return value; }

    public String getQuantity(){return quantity; }

    /**
     *SETTERS
     */
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public void setAisle(String aisle){
        this.aisle = aisle;
    }

    public void setValue(Integer value){this.value = value; }

    public void setQuantity(String quantity){this.quantity = quantity; }
}
