package svop.webapp;

import javax.jws.WebService;

@WebService
public class HelloWorldImpl implements IHelloWorld {
    public String sayHi(String name) {
        System.out.println("sayHello is called by " + name);
        return "Hello " + name;
    }
}