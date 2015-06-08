package svop.webapp;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class Server {
    private Server() {
        System.out.println("Starting svop server...");
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(SvopImpl.class);
        sf.setResourceProvider(SvopImpl.class, new SingletonResourceProvider(new SvopImpl()));
        sf.setAddress("http://0:8080");
        BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory factory = new JAXRSBindingFactory();
        factory.setBus(sf.getBus());
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
        sf.create();

        /*IHelloWorld helloWorld = new SvopImpl();
        //create WebService service factory
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        //register WebService interface
        factory.setServiceClass(IHelloWorld.class);
        //publish the interface
        factory.setAddress("http://0:8080/Svop");
        factory.setServiceBean(helloWorld);
        //create WebService instance
        factory.create();*/
    }

    public static void main(String[] args) throws InterruptedException {
        //now start the webservice server
        new Server();
        System.out.println("svop.webapp.Server ready...");
        Thread.sleep(1000 * 300000);
        System.out.println("svop.webapp.Server exit...");
        System.exit(0);
    }
}