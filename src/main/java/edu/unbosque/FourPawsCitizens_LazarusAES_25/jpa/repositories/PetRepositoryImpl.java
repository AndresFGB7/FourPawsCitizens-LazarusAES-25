package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PetRepositoryImpl implements PetRepository{

    private EntityManager entityManager;

    public PetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

    @Override
    public Optional<Pet> findById(Integer id) {
        Pet pet = entityManager.find(Pet.class, id);
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

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
