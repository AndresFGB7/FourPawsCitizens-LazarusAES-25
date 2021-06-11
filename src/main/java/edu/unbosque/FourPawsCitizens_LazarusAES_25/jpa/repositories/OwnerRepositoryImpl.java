package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 *  This class have the methods for save, find by id, find all, delete by id and edit an Owner from the Database
 */
public class OwnerRepositoryImpl implements OwnerRepository {

    private EntityManager entityManager;

    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save an owner
     *
     * @param owner: Owner
     * @return Optional of Owner
     */
    @Override
    public Optional<Owner> save(Owner owner) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Find by id an Owner
     *
     * @param id: Integer
     * @return Optional of Owner
     */
    @Override
    public Optional<Owner> findById(Integer id) {
        Owner owner = entityManager.find(Owner.class, id);
        return owner != null ? Optional.of(owner) : Optional.empty();
    }

    /**
     * Find all the owners
     * @return List of all Owners
     */
    @Override
    public List<Owner> findAll() { //Find all the Owners
        return entityManager.createQuery("from Owner").getResultList();
    }

    /**
     * Delete by id an owner
     *
     * @param id: Integer
     */
    @Override
    public void deleteById(Integer id) {
        Owner owner = entityManager.find(Owner.class, id);
        if (owner != null) {
            try {

                entityManager.getTransaction().begin();

                owner.getPets().forEach(pet -> {
                    entityManager.remove(pet);
                });

                entityManager.remove(owner);
                entityManager.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Edit an Owner of the DataBase
     *
     * @param id:           Integer
     * @param username:     String
     * @param password:     String
     * @param email:        String
     * @param personId:     Long
     * @param name:         String
     * @param adress:       String
     * @param neighborhood: String
     */
    @Override
    public void editOwner(Integer id, String username, String password, String email, Long personId, String name, String adress, String neighborhood) {
        List x = new ArrayList();
        x.add(id);
        x.add(username);
        Owner owner = entityManager.find(Owner.class, x);
        if (owner != null) {
            try {
                entityManager.getTransaction().begin();
                owner.setPassword(password);
                owner.setEmail(email);
                owner.setAddress(adress);
                owner.setNeighborhood(neighborhood);
                owner.setName(name);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
