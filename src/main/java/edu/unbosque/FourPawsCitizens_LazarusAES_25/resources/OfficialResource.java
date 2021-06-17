package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.filters.Logged;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OfficialPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.OfficialService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/officials/{username}")
public class OfficialResource {
    @Logged
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@HeaderParam("role") String role) {

        // If role doesn't match
        if (!"official".equals(role))
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Role " + role + " cannot access to this method")
                    .build();

        return Response.ok()
                .entity("Hello " + role + " !!")
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("username") String username, OfficialPOJO officialPOJO) {
        try {
            System.out.println("inicio metodo");
            officialPOJO.setUsername(username);
            String reply = new OfficialService().createOfficial(officialPOJO);
            System.out.println("mitad metodo");
            return Response.
                    status(Response.Status.CREATED)
                    .entity(reply)
                    .build();
        } catch (Exception e) {
            System.out.println("este es el error" + e);
        }
        return Response.
                status(Response.Status.CONFLICT)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response totalOfficials() {
        List<OfficialPOJO> officialPOJOS = new OfficialService().listOfficial();
        return Response.ok().entity(officialPOJOS).build();
    }

}