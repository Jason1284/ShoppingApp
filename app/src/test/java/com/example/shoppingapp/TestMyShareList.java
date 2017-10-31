package com.example.shoppingapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 *
 * Created by James on 10/31/2017.
 */

public class TestMyShareList {

    public String shareBody = "This was sent with ACTION_SEND";

    @Test
    public void testActionSend() throws Exception {
        assertNotNull(shareBody);
    }
}
