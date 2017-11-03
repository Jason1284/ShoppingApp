package com.example.shoppingapp;

/**
 * Created by SG0216351 on 11/3/2017.
 */

public class Product {
    private String name;
    private Integer price;
    private String aisle;

    /**********************************************
     *              CONSTRUCTORS
     *********************************************/
    public Product(){}
    public Product(String name, Integer price, String aisle){
        this.name = name;
        this.price = price;
        this.aisle = aisle;
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

}
