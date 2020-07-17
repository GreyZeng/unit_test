package org.example.pk2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("D set up");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("D tear down");
    }

    @Test
    public void yesTest() {
        D a = new D();
        Assert.assertTrue(a.yes());
    }

    @Test
    public void noTest() {
        D a = new D();
        Assert.assertFalse(a.no());
    }
}