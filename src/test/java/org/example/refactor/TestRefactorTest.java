package org.example.refactor;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestRefactorTest {

    @Test
    public void execute() {
        TestRefactor refactor = new TestRefactor() {
            protected T getT() {
                return new MockT();
            }
        };
        refactor.execute();
    }

    public static class MockT extends T {
        @Override
        public void begin() {
            System.out.println("mock method begin");
        }

        @Override
        public void end() {
            System.out.println("mock method end");
        }
    }
}