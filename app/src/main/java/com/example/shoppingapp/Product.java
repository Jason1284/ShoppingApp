package com.example.shoppingapp;

/**
 * Product class helps to manage the database.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class Product {
    private String name;
    private Integer price;
    private String aisle;
    private Integer value;
    private Integer quantity;

    /**********************************************
     *              CONSTRUCTORS
     *********************************************/
    public Product(){}
    public Product(String name, Integer price, String aisle, Integer quantity){
        this.name = name;
        this.price = price;
        this.aisle = aisle;
        this.value = 1;
        this.quantity = quantity;
    }

    /**********************************************
     *              GETTERS
     *********************************************/
    public String getName(){
        return name;
    }

    public Integer getPrice(){
        return price;
    }

    public String getAisle(){
        return aisle;
    }

    public Integer getValue(){return value; }

    public Integer getQuantity(){return quantity; }

    /**********************************************
     *              SETTERS
     *********************************************/
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public void setAisle(String aisle){
        this.aisle = aisle;
    }

    public void setValue(Integer value){this.value = value; }

    public void setQuantity(Integer quantity){this.quantity = quantity; }
}
