package flock.spring.service;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

/*@WebService(endpointInterface = "flock.spring.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}*/
/*@Path("/flock")
@Consumes("application/json")
@Produces("application/json")
public interface ISvop {
    //@WebParam is optional
    @GET
    @Path ("hi")
    String sayHi();

    @POST
    @Path ("card")
    String postCard(Card card);

}*/