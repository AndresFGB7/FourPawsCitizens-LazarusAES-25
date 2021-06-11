package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;

import javax.persistence.EntityManager;
import java.util.Optional;
/*
    This class saves an Officer in the DataBase
 */
public class OfficialRepositoryImpl implements OfficialRepository{

    EntityManager entityManager;

    public OfficialRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override//Save the Official
    public Optional<Official> save(Official oficial) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(oficial);
            entityManager.getTransaction().commit();
            return Optional.of(oficial);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
