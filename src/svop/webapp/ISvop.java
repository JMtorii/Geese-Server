package svop.webapp;


import javax.jws.WebParam;
import javax.ws.rs.*;

@Path("/flock")
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

}