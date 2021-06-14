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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/owners/{username}")
public class OwnerResource {
    @Logged
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@HeaderParam("role") String role) {

        // If role doesn't match
        if (!"owner".equals(role))
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
    public Response create(@PathParam("username") String username,OwnerPOJO ownerPOJO) {
        ownerPOJO.setUsername(username);
        String reply = new OwnerService().createOwner(ownerPOJO);

        return Response.
                status(Response.Status.CREATED)
                .entity(reply)
                .build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyOwner(OwnerPOJO ownerPojo) {
        String reply = new OwnerService().editOwner(ownerPojo);
        return Response.status(Response.Status.OK).entity(reply).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOwner(@PathParam("username") String username) {
        String reply = new OwnerService().deleteOwner(username);
        return Response.status(Response.Status.OK).entity(reply).build();
    }

    @POST
    @Path("/pet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPet(@PathParam("username") String username, PetPOJO petPOJO) {
        petPOJO.setOwner_id(username);
        String reply = new PetService().createPet(petPOJO);
        return Response.
                status(Response.Status.CREATED)
                .entity(reply)
                .build();
    }

    @GET
    @Path("/pet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response totalPets(@PathParam("username") String username) {
        List<PetPOJO> petPOJOS = new PetService().ListPets();
        List<PetPOJO> petsUser = new ArrayList<>();
        Optional<OwnerPOJO> ownerPOJO = new OwnerService().findByUserName(username);
        for (PetPOJO pojo : petPOJOS){
            if(pojo.getOwner_id().equals(ownerPOJO.get().getUsername())){
                petsUser.add(new PetPOJO(
                        pojo.getPet_id(),
                        pojo.getMicrochip(),
                        pojo.getName(),
                        pojo.getSpecies(),
                        pojo.getRace(),
                        pojo.getSize(),
                        pojo.getSex(),
                        pojo.getPicture(),
                        pojo.getOwner_id()));
            }
        }
        return Response.ok().entity(petsUser).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyPetOwner(@PathParam("username") String username, PetPOJO petPOJO) {
        String reply = new PetService().editPet(petPOJO.getPet_id(),petPOJO.getMicrochip(),petPOJO.getName(),petPOJO.getSpecies(),petPOJO.getRace(),petPOJO.getSize(),petPOJO.getSex(),petPOJO.getPicture());
        return Response.status(Response.Status.OK).entity(reply).build();
    }

    @DELETE
    @Path("petDelete/{petID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePet(@PathParam("petID") Integer petID) {
        String reply = new PetService().deletePet(petID);
        return Response.status(Response.Status.OK).entity(reply).build();
    }

}