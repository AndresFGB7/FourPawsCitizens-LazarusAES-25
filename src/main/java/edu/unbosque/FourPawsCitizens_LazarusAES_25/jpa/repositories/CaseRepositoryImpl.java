package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/*
    This class, save, find by id, find all, delete by id and edit an Case from the Database
 */
public class CaseRepositoryImpl implements CaseRepository {
    private EntityManager entityManager;

    public CaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * save de case
     * @param aCase
     * @return Optional of Case
     */
    @Override
    public String save(Case aCase) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(aCase);
            entityManager.getTransaction().commit();
            return "case created successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "the case could not be created";
    }

    /**
     * Find a case by id
     * @param id
     * @return Optional of Case
     */
    @Override
    public Optional<Case> findById(Integer id) {
        Case aCase = entityManager.find(Case.class, id);
        return aCase != null ? Optional.of(aCase) : Optional.empty();
    }

    /**
     * Find all the cases
     * @return List of all cases
     */
    @Override
    public List<Case> findAll() {
        return entityManager.createQuery("from Case").getResultList();
    }

    /**
     * Delete a Case by id
     * @param id
     */
    @Override
    public String deleteById(Integer id) {
        Case aCase = entityManager.find(Case.class, id);
        if(aCase != null){
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(aCase);
                entityManager.getTransaction().commit();
                return "the case was successfully removed";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "the case could not be removed";
    }

    /**
     * Delete a Case by id
     * @param id
     * @param created_at
     * @param type
     * @param description
     */
    @Override//Edit a case in the DataBase
    public String editCase(Integer id, String created_at, String type, String description) {
        Case aCase = entityManager.find(Case.class, id);
        if(aCase != null){
            try {
                entityManager.getTransaction().begin();
                aCase.setCreated_at(created_at);
                aCase.setType(type);
                aCase.setDescription(description);
                entityManager.getTransaction().commit();
                return "the case has been satisfactorily modified";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "the case could not be modified";
    }
}
