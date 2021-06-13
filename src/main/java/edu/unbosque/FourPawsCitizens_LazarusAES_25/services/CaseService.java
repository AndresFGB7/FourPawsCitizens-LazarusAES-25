package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.CaseRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.CaseRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.PetRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.PetRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.CasePOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class CaseService {
    PetRepository petRepository;
    CaseRepository caseRepository;


    public Optional<CasePOJO> createCase(Case aCase){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);


        Optional<Pet> pet = petRepository.findById(aCase.getPet_id().getPet_id());
        pet.ifPresent(a-> {
            a.addCases(new Case(aCase.getCreated_at(),aCase.getType(),aCase.getDescription()));
            petRepository.save(a);
        });
        entityManager.close();
        entityManagerFactory.close();

        return Optional.empty();

    }

    public Optional<CasePOJO> findCase(Integer id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        caseRepository = new CaseRepositoryImpl(entityManager);
        Optional<Case> aCase = caseRepository.findById(id);
        System.out.println("case find id -- > " + aCase);
        entityManager.close();
        entityManagerFactory.close();

        if(aCase.isPresent()){
            return Optional.of(new CasePOJO(aCase.get().getCase_id(),aCase.get().getCreated_at(),aCase.get().getType(),aCase.get().getDescription(),aCase.get().getPet_id().getPet_id()));
        }
        return Optional.empty();
    }

    public List<CasePOJO> ListCases(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        caseRepository = new CaseRepositoryImpl(entityManager);
        List<Case> cases = caseRepository.findAll();
        entityManager.close();
        entityManagerFactory.close();

        List<CasePOJO> casePOJOS = new ArrayList<>();
        for(Case cas : cases){
            casePOJOS.add(new CasePOJO(cas.getCase_id(),cas.getCreated_at(),cas.getType(),cas.getDescription(),cas.getPet_id().getPet_id()));
        }
        return casePOJOS;
    }

    public void editCase(Integer id, String created_at, String type, String description){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        caseRepository = new CaseRepositoryImpl(entityManager);

        caseRepository.editCase(id,created_at,type,description);

        entityManager.close();
        entityManagerFactory.close();
    }

    public void deleteCase(Integer id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        caseRepository = new CaseRepositoryImpl(entityManager);

        caseRepository.deleteById(id);

        entityManager.close();
        entityManagerFactory.close();
    }

}
