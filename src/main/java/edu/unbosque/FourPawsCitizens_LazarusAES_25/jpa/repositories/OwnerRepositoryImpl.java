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
    public String save(Owner owner) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            System.out.println("It was successfully saved by the owner");
            return "It was successfully saved by the owner";
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The owner could not be saved");
        return "The owner could not be saved";
    }

    /**
     * Find by id an Owner
     *
     * @param id: Integer
     * @return Optional of Owner
     */
    @Override
    public Optional<Owner> findById(String id){
        Owner owner = entityManager.find(Owner.class, id);
        return owner != null ? Optional.of(owner) : Optional.empty();
    }

    /**
     * Find all the owners
     *
     * @return List of all Owners
     */
    @Override
    public List<Owner> findAll() { //Find all the Owners
        return entityManager.createQuery("from Owner").getResultList();
    }

    /**
     * Find owner by username
     *
     * @return List of all Owners
     */
    @Override
    public Optional<Owner> findByUsername(String username) {
        List<Owner> owners = findAll();
        for (Owner owner : owners){
            if(owner.getUserApp().getUsername().equals(username)){
              return Optional.of(owner);
            }
        }
        return Optional.empty();
    }

    /**
     * Delete by id an owner
     *
     * @param Username: String
     */
    @Override
    public  String deleteByUserName(String Username) {
        Owner owner = entityManager.find(Owner.class, Username);
        if (owner != null) {
            try {

                entityManager.getTransaction().begin();

                owner.getPets().forEach(pet -> {
                    entityManager.remove(pet);
                });

                entityManager.remove(owner);
                entityManager.getTransaction().commit();
                return "owner was successfully removed";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "the user could not be removed";
    }

    /**
     * Edit an Owner of the DataBase
     *
     * @param id:           Integer
     * @param username:     String
     * @param name:         String
     * @param adress:       String
     * @param neighborhood: String
     */
    @Override
    public String editOwner(Integer id, String username, String name, String adress, String neighborhood) {
        Owner owner = entityManager.find(Owner.class, username);
        if (owner != null) {
            try {
                entityManager.getTransaction().begin();
                owner.setName(name);
                owner.setAddress(adress);
                owner.setNeighborhood(neighborhood);
                entityManager.getTransaction().commit();
                return  "successful owner edit";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "the edition is not completed";
    }
}
