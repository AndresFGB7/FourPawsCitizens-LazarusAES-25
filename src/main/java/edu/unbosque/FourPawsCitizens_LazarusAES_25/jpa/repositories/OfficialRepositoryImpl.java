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
    /**
     * Save the Official
     * @param oficial: Official
     */
    @Override
    public String save(Official oficial) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(oficial);
            entityManager.getTransaction().commit();
            System.out.println("Se guardo satisfactoriamente el Official");
            return "Se guardo satisfactoriamente el Official";
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("No se logro guardar el Official");
        return "No se logro guardar el Official";
    }
}
