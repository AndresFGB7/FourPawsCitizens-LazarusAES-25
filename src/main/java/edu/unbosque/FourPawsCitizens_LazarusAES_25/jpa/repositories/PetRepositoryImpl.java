package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * This class have the methods for save, find by id, find all, delete by id and edit a Pet from the Database
 */
public class PetRepositoryImpl implements PetRepository{

    private EntityManager entityManager;

    public PetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save a Pet
     * @param pet: Pet
     * @return Optional of Pet
     */
    @Override
    public Optional<Pet> save(Pet pet) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pet);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    /**
     * Find by id an Pet
     *
     * @param id: Integer
     * @return Optional of Pet
     */
    @Override
    public Optional<Pet> findById(Integer id) {
        Pet pet = entityManager.find(Pet.class, id);
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    /**
     * Find all the Pets
     * @return List of all Pets
     */
    @Override
    public List<Pet> findAll() {
        return entityManager.createQuery("from Pet").getResultList();
    }

    @Override
    public void editPet(Integer pet_id, String microship, String name, String species, String race, String size, String sex, String picture, Integer owner_id) {

        Pet pet = entityManager.find(Pet.class,pet_id);
        if(pet != null){
            try {
                entityManager.getTransaction().begin();
                pet.setMicroship(microship);
                pet.setName(name);
                pet.setSpecies(species);
                pet.setRace(race);
                pet.setSize(size);
                pet.setSex(sex);
                pet.setPicture(picture);
                entityManager.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * Delete by id an owner
     *
     * @param id: Integer
     */
    @Override
    public void deleteById(Integer id) {
        Pet pet = entityManager.find(Pet.class, id);
        if (pet != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(pet);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
