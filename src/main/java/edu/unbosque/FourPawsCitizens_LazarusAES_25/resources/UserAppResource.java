package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.UserApp;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.filters.Logged;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.UserAppPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.UserAppPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.OwnerService;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.UserAppService;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.UserAppService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/user")
public class UserAppResource {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserAppPOJO userAppPOJO) {
        try {
            String reply = new UserAppService().saveUserApp(userAppPOJO);
            return Response.status(Response.Status.CREATED)
                    .entity(reply)
                    .build();
        }catch (Exception e){
            System.out.println("aparece el error:    " + e);
        }
           return Response.status(Response.Status.CONFLICT)
                   .build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response totalUsers() {
        List<UserAppPOJO> UserAppPOJO = new UserAppService().ListUsers();
        return Response.ok().entity(UserAppPOJO).build();
    }
}
