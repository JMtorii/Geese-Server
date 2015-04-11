package svop.webapp;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IHelloWorld {
    //@WebParam is optional
    String sayHi(@WebParam(name="text") String text);
}