package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * This class have the methods for save, find by id, find all, delete by id and edit a Vet from the Database
 */
public class VetRepositoryImpl implements VetRepository {
    private EntityManager entityManager;

    public VetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save a Vet
     *
     * @param vet: Vet
     * @return Optional of Vet
     */
    @Override
    public Optional<Vet> save(Vet vet) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(vet);
            entityManager.getTransaction().commit();
            return Optional.of(vet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Find by username a Vet
     *
     * @param username: String
     * @return Optional of Vet
     */
    @Override
    public Optional<Vet> findByUserName(String username) {
        Vet vet = entityManager.find(Vet.class, username);
        return vet != null ? Optional.of(vet) : Optional.empty();
    }

    /**
     * Find all the Vets
     *
     * @return List of all Vets
     */
    @Override
    public List<Vet> findAll() {
        return entityManager.createQuery("from Vet").getResultList();
    }

    /**
     * Delete by username a Vet
     *
     * @param username: String
     */
    @Override
    public void deleteByUserName(String username) {
        Vet vet = entityManager.find(Vet.class, username);
        if (vet != null) {
            try {

                entityManager.getTransaction().begin();

                vet.getVisits().forEach(visit -> {
                    entityManager.remove(visit);
                });

                entityManager.remove(vet);
                entityManager.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Edit a Vet of the DataBase
     * @param username: String
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    @Override
    public void editVet(String username, String password, String email, String name, String address, String neighborhood) {
        Vet vet = entityManager.find(Vet.class, username);
        if (vet != null) {
            try {
                entityManager.getTransaction().begin();
                vet.setName(name);
                vet.setPassword(password);
                vet.setEmail(email);
                vet.setAddress(address);
                vet.setNeighborhood(neighborhood);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
