package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.filters.Logged;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OfficialPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.OfficialService;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.OwnerService;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.PetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/officials/{username}/")
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
            officialPOJO.setUsername(username);
            String reply = new OfficialService().createOfficial(officialPOJO);
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
    @Logged
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{password}")
    public Response getOfficial(@PathParam("username") String username,@PathParam("password") String password){
        List<OfficialPOJO> officialPOJOS = new OfficialService().getOfficial(username);
        OfficialPOJO us = null;
        for (OfficialPOJO off: officialPOJOS) {
             us = new OfficialPOJO(off.getUsername(), off.getPassword(), off.getEmail(), off.getRole(), off.getName());
        }
        try {
            if (us.getPassword() == password) {
                return Response.ok().entity(us).build();
            } else {
                return Response.ok().entity("Wrong password").build();
            }
        }catch (Exception e){
            return Response.ok().entity("Wrong password").build();
        }
    }
    @GET
    @Path("getOwners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwners() {
        List<OwnerPOJO> ownerPOJO = new OwnerService().listOwners();
        return Response.ok().entity(ownerPOJO).build();
    }
    @GET
    @Path("getPets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPets(String username) {
        List<PetPOJO> petPOJO = new PetService().listPetsByOwner(username);
        return Response.ok().entity(petPOJO).build();
    }
}