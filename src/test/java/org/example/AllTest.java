package org.example;

import org.example.pk1.Pk1AllTest;
import org.example.pk2.Pk2AllTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Pk1AllTest.class,
        Pk2AllTest.class
})
public class AllTest {

}
