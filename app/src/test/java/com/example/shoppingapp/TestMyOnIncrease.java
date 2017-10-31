package com.example.shoppingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMyOnIncrease {
    public int TestIncrease = 2;
    public int TestDecrease = 4;
    public int result = 3;

    @Test
    public void testIncrease() {
        myIncreaseClass tester = new myIncreaseClass();
        assertEquals("Result", result, tester.increase(TestIncrease));
    }

    @Test
    public void testDecrease() {
        myDecreaseClass tester = new myDecreaseClass();
        assertEquals("Result", result, tester.decrease(TestDecrease));
    }

    class myIncreaseClass {
        public int increase(int i) {
            return ++i;
        }
    }

    class myDecreaseClass {
        public int decrease(int j) { return --j; }
    }

}
