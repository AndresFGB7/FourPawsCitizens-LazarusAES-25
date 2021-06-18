package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;



import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.filters.Logged;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.PetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/owners/{username}/pets")
public class PetResource {
    /**
     * Method that creates a pet and save it in the db
     *
     * @param username owner's username
     * @param petPOJO  pet's pojo
     * @return a response status
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("username") String username, PetPOJO petPOJO) {

        //petPOJO.setOwner_username(username);
        String message = new PetService().savePet(petPOJO);

        return Response.
                status(Response.Status.CREATED)
                .entity(message)
                .build();
    }

    /**
     * Method that modify a pet. Compare the ids and update the data in the db
     *
     * @param petId   pet's id
     * @param petPojo pet's pojo
     * @return a response status
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pet_id}")
    public Response modifyPet(@PathParam("pet_id") Integer petId, PetPOJO petPojo) {
        petPojo.setPet_id(petId);
        String message = new PetService().modifyPet(petPojo);
        return Response.status(Response.Status.OK).entity(message).build();
    }



    @Logged
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pet_id}/visitsCasesAll")
    public Response listVisitsCasesAll(@PathParam("pet_id") Integer pet_id) {

        return Response.
                status(Response.Status.OK).
                //entity(new PetService().listVisitsAndCaseAll(pet_id)).
                build();
    }

}
