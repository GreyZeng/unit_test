# 单元测试Tips

## 多个测试用例应该怎么组织？
假设需要测试的类是A，B，C，D，A和B在pk1包下，B和C在pk2包下
如果每次都手动点击A，B，C，D这几个类对应的测试类，那么很不方便，
我们可以对pk1包下的A和B的测试类分为以下几个，ATest，BTest，Pk1AllTest，其中ATest和BTest分别对应A和B的测试类，Pk1AllTest将ATest和BTest组合进来，执行Pk1AllTest就相当于一起执行了ATest和BTest，具体代码如下
```java
package org.example.pk1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ATest {
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
```
```java
public class BTest {

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
```
```java
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ATest.class,
        BTest.class
})
public class Pk1AllTest {
}

```
CTest, DTest, Pk2AllTest同理，然后，在pk1和pk2包的上一级，建一个AllTest，AllTest中，把Pk1AllTest和Pk2AllTest注入进来，执行AllTest，就把所有要测试的类都执行了。
```java
@RunWith(Suite.class)
@Suite.SuiteClasses({
        Pk1AllTest.class,
        Pk2AllTest.class
})
public class AllTest {

}
```




## 一个重构的例子
待重构的代码
```java
public class TestRefactor {
    public void execute() {
        // 假设T方法需要很多资源和初始化的动作，不方便构造进行测试
        T t = new T();
        t.begin();
        System.out.println("do your biz");
        t.end();
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
```
 假设T方法需要很多资源和初始化的动作，不方便构造进行测试，我们需要Mock T这个类的begin和end方法，此时，我们可以先重构一下代码，重构后的代码如下：
```java
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
```
那么我们进行单元测试的时候，在新建被测试类的时候，可以传入一个匿名内部类，并重写getT()方法，让其返回我们自定义的MockT对象，这样就实现了用MockT模拟T行为的测试需求。
```java
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
```




## Mock Object示例
对于一些方法中使用到了接口方法，比如HttpServletRequest，这些类的实现可能是由具体的容器，比如Tomcat提供的，我们要运行其单元测试就必须部署到Tomcat容器中，非常不方便，这里我们使用Easy Mock，来模拟接口的具体实现方法的返回行为，这样就便于我们进行单元测试了。
引入依赖
```xml
   <dependency>
      <groupId>org.easymock</groupId>
      <artifactId>easymock</artifactId>
      <version>4.2</version>
      <scope>test</scope>
    </dependency>
```
假设需要测试的方法如下：
```java
public class Service {
    public String execute(MyInterface myInterface) {
        String username = myInterface.getParam("username");
        return username;
    }
}

```
其中MyInterface是接口，需要依赖具体的实现，如果我们模拟一个实现类，那么就需要重写很多方法，现在我们采用mock的方式，仅需要使用这个接口的getParam方法即可，单元测试的代码如下：
```java
public class ServiceTest {

    @Test
    public void execute() {
        MyInterface myInterface = EasyMock.createMock(MyInterface.class);
        EasyMock.expect(myInterface.getParam("username")).andReturn("userName");
        EasyMock.replay(myInterface);
        assertEquals("userName", new Service().execute(myInterface));
    }
}
```


## 源码


[Github](https://github.com/GreyZeng/unit_test.git)


[Gitee](https://gitee.com/greyzeng/unit_test.git)






