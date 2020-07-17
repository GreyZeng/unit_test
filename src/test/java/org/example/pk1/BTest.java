package org.example.pk1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("B set up");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("B tear down");
    }

    @Test
    public void yesTest() {
        B a = new B();
        Assert.assertTrue(a.yes());
    }

    @Test
    public void noTest() {
        B a = new B();
        Assert.assertFalse(a.no());
    }
}