package org.example.refactor;

public class TestRefactor {
    public void execute() {
        T t = getT();
        t.begin();
        System.out.println("do your biz");
        t.end();
    }

    protected T getT() {
        return new T();
    }
}

class T {
    public void begin() {
        System.out.println("T begin");
    }

    public void end() {
        System.out.println("T end");
    }
}
