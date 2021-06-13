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

@Path("/officials")
public class OfficialsResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(OfficialPOJO officialPOJO) {
        String reply = new OfficialService().saveOfficial(officialPOJO);
        return Response.
                status(Response.Status.CREATED)
                .entity(reply)
                .build();
    }

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

    @GET
    @Path("/owners/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response totalOwners() {
        List<OwnerPOJO> ownerPOJOS = new OwnerService().listOwners();
        return Response.ok().entity(ownerPOJOS).build();
    }

    @GET
    @Path("/owners/Neighborhood/{Neighborhood}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ownersByNeighborhood(@PathParam("Neighborhood") String Neighborhood) {
        List<OwnerPOJO> ownerPOJOS = new OwnerService().listOwners();
        List<OwnerPOJO> ownerNe = new ArrayList<>();
        for (OwnerPOJO pojo : ownerPOJOS) {
            if (pojo.getNeighborhood().equals(Neighborhood)) {
                ownerNe.add(new OwnerPOJO(pojo.getUsername(), pojo.getPassword(), pojo.getEmail(), pojo.getPersonId(), pojo.getName(), pojo.getAddress(), pojo.getNeighborhood()));
            }
        }
        return Response.ok().entity(ownerNe).build();
    }

    @GET
    @Path("/pets/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response totalPets() {
        List<PetPOJO> petPOJOS = new PetService().ListPets();
        return Response.ok().entity(petPOJOS).build();
    }

    @GET
    @Path("/pets/microchip/{microchip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response petsByMicrochip(@PathParam("microchip") Boolean microchip) {
        List<PetPOJO> petPOJOS = new PetService().ListPets();
        List<PetPOJO> pets = new ArrayList<>();
        for (PetPOJO pojo : petPOJOS) {
            if (microchip == true) {
                if (pojo.getMicrochip() != null) {
                    pets.add(new PetPOJO(
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
            } else {
                if (pojo.getMicrochip() == null) {
                    pets.add(new PetPOJO(
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
        }
        return Response.ok().entity(pets).build();
    }

    @GET
    @Path("/pets/race/{race}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response petsByRace(@PathParam("race") String race) {
        List<PetPOJO> petPOJOS = new PetService().ListPets();
        List<PetPOJO> pets = new ArrayList<>();
        if (!petPOJOS.isEmpty()) {
            for (PetPOJO pojo : petPOJOS) {
                if (pojo.getRace().equals(race)) {
                    pets.add(new PetPOJO(
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
            if (pets.isEmpty()) {
                return Response.ok().entity("No pets to show").build();
            } else {
                return Response.ok().entity(pets).build();
            }

        }
        return Response.ok().entity("No pets to show").build();
    }

    @GET
    @Path("/pets/size/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response petsBySize(@PathParam("size") String size) {
        List<PetPOJO> petPOJOS = new PetService().ListPets();
        List<PetPOJO> pets = new ArrayList<>();
        if (!petPOJOS.isEmpty()) {
            for (PetPOJO pojo : petPOJOS) {
                if (pojo.getSize().equals(size)) {
                    pets.add(new PetPOJO(
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
            if (pets.isEmpty()) {
                return Response.ok().entity("No pets to show").build();
            } else {
                return Response.ok().entity(pets).build();
            }

        }
        return Response.ok().entity("No pets to show").build();
    }

    @GET
    @Path("/pets/sex/{sex}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response petsBySex(@PathParam("sex") String sex) {
        List<PetPOJO> petPOJOS = new PetService().ListPets();
        List<PetPOJO> pets = new ArrayList<>();
        if (!petPOJOS.isEmpty()) {
            for (PetPOJO pojo : petPOJOS) {
                if (pojo.getSex().equals(sex)) {
                    pets.add(new PetPOJO(
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
            if (pets.isEmpty()) {
                return Response.ok().entity("No pets to show").build();
            } else {
                return Response.ok().entity(pets).build();
            }

        }
        return Response.ok().entity("No pets to show").build();
    }

}
