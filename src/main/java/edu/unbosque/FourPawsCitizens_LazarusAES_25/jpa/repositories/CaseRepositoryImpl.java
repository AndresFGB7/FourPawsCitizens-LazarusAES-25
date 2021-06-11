package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
/*
    This class, save, find by id, find all, delete by id and edit an Case from the Database
 */
public class CaseRepositoryImpl implements CaseRepository{
    private EntityManager entityManager;

    public CaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override //save de case
    public Optional<Case> save(Case aCase) {
       try {
           entityManager.getTransaction().begin();
           entityManager.persist(aCase);
           entityManager.getTransaction().commit();
           return Optional.of(aCase);
       }catch (Exception e) {
           e.printStackTrace();
       }
        return Optional.empty();
    }

    @Override //Find a case by id
    public Optional<Case> findById(Integer id) {
        Case aCase = entityManager.find(Case.class, id);
        return aCase != null ? Optional.of(aCase) : Optional.empty();
    }

    @Override//Find all the cases
    public List<Case> findAll() {
        return entityManager.createQuery("from Case").getResultList();
    }

    @Override//Delete a Case by id
    public void deleteById(Integer id) {
        Case aCase = entityManager.find(Case.class, id);
        if(aCase != null){
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(aCase);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override//Edit a case in the DataBase
    public void editCase(Integer id, String created_at, String type, String description, Integer pet_id) {
        Case aCase = entityManager.find(Case.class, id);
        if(aCase != null){
            try {
                entityManager.getTransaction().begin();
                aCase.setCase_id(id);
                aCase.setCreated_at(created_at);
                aCase.setType(type);
                aCase.setDescription(description);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
