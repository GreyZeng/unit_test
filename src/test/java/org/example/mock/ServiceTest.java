package org.example.mock;

import org.easymock.EasyMock;
import org.easymock.internal.MocksControl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void execute() {
        MyInterface myInterface = EasyMock.createMock(MyInterface.class);
        EasyMock.expect(myInterface.getParam("username")).andReturn("userName");
        EasyMock.replay(myInterface);
        assertEquals("userName", new Service().execute(myInterface));
    }
}