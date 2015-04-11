package svop.webapp;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by enc 2015-04-11.
 */
public class Client {
    private Client() {
    }

    public static void main(String[] args) {
        //create WebService client proxy factory
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        //register WebService interface
        factory.setServiceClass(IHelloWorld.class);
        //set webservice publish address to factory.
        factory.setAddress("http://localhost:8080/HelloWorld");
        IHelloWorld iHelloWorld = (IHelloWorld) factory.create();
        System.out.println("invoke webservice...");
        System.out.println("message context is:" + iHelloWorld.sayHi("Josen"));
        System.exit(0);
    }
}
