package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.UserAppService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/user/")
public class UserAppResource {
    @GET
    @Path("{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username,@PathParam("password") String password) {
        Optional<String> UserAppPOJO = new UserAppService().validateUser(username,password);
        return Response.ok().entity(UserAppPOJO).build();
    }
}
