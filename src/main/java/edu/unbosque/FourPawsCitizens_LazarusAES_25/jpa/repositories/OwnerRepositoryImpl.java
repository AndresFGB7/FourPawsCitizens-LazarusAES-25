package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;

import javax.persistence.EntityManager;
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
    public Optional<Owner> save(Owner owner) {//Save an owner
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

    @Override
    public Optional<Owner> findById(Integer id) { //Find by id an Owner
        Owner owner = entityManager.find(Owner.class, id);
        return owner != null ? Optional.of(owner) : Optional.empty();
    }

    @Override
    public List<Owner> findAll() { //Find all the Owners
        return entityManager.createQuery("from Owner").getResultList();
    }

    @Override
    public void deleteById(Integer id) { //Delete by id an owner
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

    @Override
    //Edit an Owner of the DataBase
    public void editOwner(Integer id, String username, String password, String email, Long personId, String name, String adress, String neighborhood) {
        Owner owner = entityManager.find(Owner.class, id);
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
