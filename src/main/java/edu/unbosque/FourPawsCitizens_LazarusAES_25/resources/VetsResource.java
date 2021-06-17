package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.filters.Logged;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VetPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VisitPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.VetService;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.VisitService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/vets/{username}")
public class VetsResource {

    @Logged
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@HeaderParam("role") String role) {

        // If role doesn't match
        if (!"vet".equals(role))
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Role " + role + " cannot access to this method")
                    .build();

        return Response.ok()
                .entity(new VetService().listVet())
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVet(@PathParam("username") String username,VetPOJO vetPOJO) {
        vetPOJO.setUsername(username);
        String reply = new VetService().saveVet(vetPOJO);
        return Response.
                status(Response.Status.CREATED)
                .entity(reply)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyVet(@PathParam("username") String username,VetPOJO vetPOJO) {
        vetPOJO.setUsername(username);
        String reply = new VetService().editVet(
                vetPOJO.getUsername(),
                vetPOJO.getPassword(),
                vetPOJO.getEmail(),
                vetPOJO.getRole(),
                vetPOJO.getVetId(),
                vetPOJO.getName(),
                vetPOJO.getAddress(),
                vetPOJO.getNeighborhood());
        return Response.status(Response.Status.OK).entity(reply).build();
    }

    @POST
    @Path("/visit/pet/{pet_ID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVisit(@PathParam("username") String username,@PathParam("pet_ID") Integer pet_ID, VisitPOJO visitPOJO){
    visitPOJO.setVet_id(username);
    visitPOJO.setPet_id(pet_ID);
    String reply = new VisitService().saveVisit(visitPOJO);
        return Response.
                status(Response.Status.CREATED)
                .entity(reply)
                .build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVisits(@PathParam("username") String username){
        List<VisitPOJO> visitPOJOS = new VisitService().listVisit();
        List<VisitPOJO> visits = new ArrayList<>();
        for (VisitPOJO pojo : visitPOJOS){
            if(pojo.getVet_id().equals(username)){
                visits.add(new VisitPOJO(
                        pojo.getVisit_id(),
                        pojo.getCreated_at(),
                        pojo.getType(),
                        pojo.getDescription(),
                        pojo.getVet_id(),
                        pojo.getPet_id()
                ));
            }
        }
        return Response.ok().entity(visits).build();
    }


}
