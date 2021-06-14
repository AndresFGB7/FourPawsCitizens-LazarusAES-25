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
        Optional<UserAppPOJO> persistedUserApp = new UserAppService().saveUserApp(userAppPOJO);
        if (persistedUserApp.isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .entity(persistedUserApp.get())
                    .build();
        } else {
            return Response.serverError()
                    .entity("User user could not be created")
                    .build();
        }

    }
}
