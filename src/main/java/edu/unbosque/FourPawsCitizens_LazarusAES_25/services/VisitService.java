package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VisitPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VisitService {
    private PetRepository petRepository;
    private VetRepository vetRepository;
    private VisitRepository visitRepository;

    /**
     * @param visitPOJO visit's pojo
     * @return a string message
     */
    public String saveVisit(VisitPOJO visitPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);
        vetRepository = new VetRepositoryImpl(entityManager);

        // Creating an optional pet object and find the id of the pet in the visit's pojo
        List petO = petRepository.findAll(visitPOJO.getPet_id());

        //If the id doesn't exist return false

        if (petO.isEmpty()) return "No existe esa identificación de mascota";

        // Creating an optional vet object and find the id of the vet in the visit's pojo
        Optional<Vet> vetO = vetRepository.findById(visitPOJO.getVet_id());

        //If the id doesn't exist return false
        if (!vetO.isPresent()) return "No existe esa veterinaria";

        Pet pet = petRepository.findById(visitPOJO.getPet_id()).get();

        Vet vet = vetRepository.findById(visitPOJO.getVet_id()).get();

        //Validating the format of the date, passing date of string to date

        //Creating the visit and save it in the repository
        Visit visit = new Visit(visitPOJO.getCreated_at(), visitPOJO.getType(), visitPOJO.getDescription(), vet, pet);

        pet.addVisits(visit);
        petRepository.save(pet);

        vet.addVisit(visit);
        vetRepository.save(vet);

        entityManager.close();
        entityManagerFactory.close();
        return "Se ha creado exitosamente la visita!";
    }

    /**
     * Get all the visits of DB
     * @return List of Visit
     */
    public List<VisitPOJO> listVisit(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        List<Visit> visits = visitRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<VisitPOJO> visitPOJOS = new ArrayList<>();
        for (Visit visit : visits){

            visitPOJOS.add(new VisitPOJO(visit.getVisit_id(),visit.getCreated_at(),visit.getType(),visit.getDescription(),visit.getVet().getVet_id(),visit.getPet().getPet_id()));
        }
        return visitPOJOS;
    }
}
