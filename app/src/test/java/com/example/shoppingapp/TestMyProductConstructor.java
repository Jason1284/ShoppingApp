package com.example.shoppingapp;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
/**
 * Created by SG0216351 on 10/31/2017.
 */

public class TestMyProductConstructor {
    public String name = "Chocolate";
    public int price = 5;
    public String type = "Candies";

    @Test
    public void testProductNotNull(){
        TestProduct product = new TestProduct(name, price, type);
        assertNotNull(product);
    }

    class TestProduct{
        private String name;
        private int price;
        private String type;
        TestProduct(String name, int price, String type){
            this.name = name;
            this.price = price;
            this.type = type;
        }
    }

}
