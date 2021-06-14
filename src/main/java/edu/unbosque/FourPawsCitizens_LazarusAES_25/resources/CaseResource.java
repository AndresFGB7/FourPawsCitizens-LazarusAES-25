package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.CasePOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.CaseService;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.OwnerService;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.PetService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/pet/{pet_id}/cases/")
public class CaseResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPet(@PathParam("pet_id") Integer pet_id, CasePOJO casePOJO) {
        casePOJO.setPet_id(pet_id);
        String reply = new CaseService().createCase(casePOJO);
        return Response.
                status(Response.Status.CREATED)
                .entity(reply)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response totalCases(@PathParam("pet_id") Integer pet_id) {
        List<CasePOJO> casePOJOS = new CaseService().ListCases();
        List<CasePOJO> cases = new ArrayList<>();
        for (CasePOJO pojo : casePOJOS){
            if(pojo.getPet_id() == pet_id){
                cases.add(new CasePOJO(
                        pojo.getCase_id(),
                        pojo.getCreated_at(),
                        pojo.getType(),
                        pojo.getDescription(),
                        pojo.getPet_id()
                ));
            }
        }
        return Response.ok().entity(cases).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyPetOwner(@PathParam("pet_id") Integer pet_id, CasePOJO casePOJO) {
        String reply = new CaseService().editCase(casePOJO.getCase_id(),casePOJO.getCreated_at(),casePOJO.getType(),casePOJO.getDescription());
        return Response.status(Response.Status.OK).entity(reply).build();
    }

    @DELETE
    @Path("{case_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePet(@PathParam("case_id") Integer case_id) {
        String reply = new CaseService().deleteCase(case_id);
        return Response.status(Response.Status.OK).entity(reply).build();
    }
}
