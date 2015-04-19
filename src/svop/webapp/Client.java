package svop.webapp;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by enc 2015-04-11.
 */
// This is unmaintained: use the built in REST client test.
public class Client {
    private Client() {
    }

    public static void main(String[] args) {
        //create WebService client proxy factory
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        //register WebService interface
        factory.setServiceClass(ISvop.class);
        //set webservice publish address to factory.
        factory.setAddress("http://0:8080");
        ISvop iSvop = (ISvop) factory.create();
        System.out.println("invoke webservice...");
        System.out.println("message context is:" + iSvop.sayHi("Josen"));
        System.exit(0);
    }
}
