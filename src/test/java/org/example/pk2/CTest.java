package org.example.pk2;

import org.example.pk1.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("C set up");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("C tear down");
    }

    @Test
    public void yesTest() {
        C a = new C();
        Assert.assertTrue(a.yes());
    }

    @Test
    public void noTest() {
        C a = new C();
        Assert.assertFalse(a.no());
    }
}