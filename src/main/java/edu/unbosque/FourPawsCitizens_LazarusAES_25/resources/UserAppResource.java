package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.UserApp;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.filters.Logged;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.UserAppPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.UserAppPOJO;
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
        String reply = new UserAppService().saveUserApp(userAppPOJO);
        return Response.status(Response.Status.CREATED)
                .entity(reply)
                .build();

    }
}
