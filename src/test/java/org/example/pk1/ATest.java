package org.example.pk1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ATest {

    @Before
    public void setUp() throws Exception {
        System.out.println("A set up");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("A tear down");
    }

    @Test
    public void yesTest() {
        A a = new A();
        Assert.assertTrue(a.yes());
    }

    @Test
    public void noTest() {
        A a = new A();
        Assert.assertFalse(a.no());
    }
}