package svop.webapp;


import javax.jws.WebParam;
import javax.ws.rs.*;

@Path("/svop")
@Consumes("application/json")
@Produces("application/json")
public interface ISvop {
    //@WebParam is optional
    @GET
    @Path ("card")
    String sayHi(@WebParam(name="text") String text);

    @POST
    @Path ("card")
    String postCard(Card card);

}