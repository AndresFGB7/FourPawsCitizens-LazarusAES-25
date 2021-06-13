package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.VisitRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.VisitRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VisitPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * The service of Visit, use Repository of visit
 */
public class VisitService {

    VisitRepository visitRepository;

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

    /**
     * Save in DB a Visit
     * @return an object (Visit)
     */
    public Visit saveVisit(Integer visit_id, String created_id, String type, String description, Vet vet, Pet pet){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);

        Visit visit = new Visit(visit_id,created_id,type,description);
        Visit persistedVisit = visitRepository.save(visit).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVisit;
    }

    /**
     * Delete a Visit of the DB
     * @param id: Integer -> ID to delete a Visit
     */
    public void deleteVisit(Integer id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        visitRepository.deleteById(id);

        entityManager.close();
        entityManagerFactory.close();
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
    public void  editVisit(Integer visit_id, String created_id, String type, String description, Integer vet_id, Integer pet_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        visitRepository.editVisit( visit_id,  created_id,  type,  description,  vet_id,  pet_id);

        entityManager.close();
        entityManagerFactory.close();
    }

}
