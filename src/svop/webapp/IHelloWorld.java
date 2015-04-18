package svop.webapp;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/svop")
public interface IHelloWorld {
    //@WebParam is optional
    @GET
    @Produces("text/plain") @Path("test")
    String sayHi(@WebParam(name="text") String text);
}