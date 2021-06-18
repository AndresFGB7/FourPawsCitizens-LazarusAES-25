package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    This class, save, fynd by id, find all, delete by id and edit an Owner from the Database
 */
public class OwnerRepositoryImpl implements OwnerRepository {

    private EntityManager entityManager;

    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String save(Owner owner) {//Save an owner
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            return "It was successfully saved by the owner";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "The owner could not be saved";
    }

    @Override
    public Optional<Owner> findByUsername(String Username) { //Find by id an Owner
        Owner owner = entityManager.find(Owner.class, Username);
        return owner != null ? Optional.of(owner) : Optional.empty();
    }

    @Override
    public List<Owner> findAll() { //Find all the Owners
        return entityManager.createQuery("from Owner").getResultList();
    }

    @Override
    public String deleteByUsername(String  Username) { //Delete by id an owner
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

    @Override
    //Edit an Owner of the DataBase
    public String editOwner(String username, String password, String email, Integer personId, String name, String adress, String neighborhood) {
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
