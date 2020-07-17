package org.example.mock;

public class Service {
    public String execute(MyInterface myInterface) {
        String username = myInterface.getParam("username");
        return username;
    }
}
