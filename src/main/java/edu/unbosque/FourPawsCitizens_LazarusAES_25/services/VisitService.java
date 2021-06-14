package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VisitPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The service of Visit, use Repository of visit
 */
public class VisitService {

    VisitRepository visitRepository;
    VetRepository vetRepository;
    PetRepository petRepository;

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
            visitPOJOS.add(new VisitPOJO(visit.getVisit_id(),visit.getCreated_at(),visit.getType(),visit.getDescription(),visit.getVet().getUsername(),visit.getPet_id().getPet_id()));
        }
        return visitPOJOS;
    }

    /**
     * Save in DB a Visit
     * @return an object (Visit)
     */
    public String saveVisit(VisitPOJO visitPOJO){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        vetRepository = new VetRepositoryImpl(entityManager);
        petRepository = new PetRepositoryImpl(entityManager);

        Optional<Vet> vet = vetRepository.findByUserName(visitPOJO.getVet_id());
        Optional<Pet> pet = petRepository.findById(visitPOJO.getPet_id());

        if (!vet.isPresent()) return "The vet does not exist";
        if (!vet.isPresent()) return "The pet does not exist";
        Visit visit = new Visit(
                visitPOJO.getCreated_at(),
                visitPOJO.getType(),
                visitPOJO.getDescription()
        );
        vet.get().addVisit(visit);
        pet.get().addVisits(visit);
        vetRepository.save(vet.get());
        petRepository.save(pet.get());
        entityManager.close();
        entityManagerFactory.close();

        return "The visit was successfully created";
    }

    /**
     * Delete a Visit of the DB
     * @param id: Integer -> ID to delete a Visit
     */
    public String deleteVisit(Integer id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        String reply = visitRepository.deleteById(id);

        entityManager.close();
        entityManagerFactory.close();
        return reply;
    }


    /**
     * Edit a Visit of the DB
     * @param visit_id: Integer
     * @param created_id: String
     * @param type: String
     * @param description: String
     * @param vet_id: Integer
     * @param pet_id: Integer
     */
    public String  editVisit(Integer visit_id, String created_id, String type, String description, Integer vet_id, Integer pet_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        String reply = visitRepository.editVisit( visit_id,  created_id,  type,  description,  vet_id,  pet_id);

        entityManager.close();
        entityManagerFactory.close();
        return reply;
    }

}
