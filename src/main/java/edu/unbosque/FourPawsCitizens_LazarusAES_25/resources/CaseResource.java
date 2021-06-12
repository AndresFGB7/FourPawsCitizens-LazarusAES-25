package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pet/{pet_id}/cases/{case_id}")
public class CaseResource {

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("pet_id") Integer Pet_id, @PathParam("case_id") Integer Case_id, Case ase) {
        return Response.ok()
                .entity(ase)
                .build();
    }
    @DELETE
    public Response delete(@PathParam("pet_id") Integer Pet_id, @PathParam("case_id") Integer Case_id) {

        return Response.noContent()
                .build();
    }
}
