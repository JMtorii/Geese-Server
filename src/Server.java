import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import svop.webapp.HelloWorldImpl;
import svop.webapp.IHelloWorld;

public class Server {
    private Server() {
        IHelloWorld helloWorld = new HelloWorldImpl();
        //create WebService service factory
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        //register WebService interface
        factory.setServiceClass(IHelloWorld.class);
        //publish the interface
        factory.setAddress("http://localhost:9000/HelloWorld");
        factory.setServiceBean(helloWorld);
        //create WebService instance
        factory.create();
    }

    public static void main(String[] args) throws InterruptedException {
        //now start the webservice server
        new Server();
        System.out.println("Server ready...");
        Thread.sleep(1000 * 60);
        System.out.println("Server exit...");
        System.exit(0);
    }
}