package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PetRepositoryImpl implements PetRepository{

    private EntityManager entityManager;

    public PetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String save(Pet pet) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pet);
            entityManager.getTransaction().commit();
            return "se ha registrado correctamente";
        } catch (Exception e) {
            return "Ha ocurrido un error al registrar la mascota!";
        }
    }

    @Override
    public Optional<Pet> findById(Integer id) {
        Pet pet = entityManager.find(Pet.class, id);
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public List findAll(Integer petId) {
        return entityManager.createQuery("from Pet").getResultList();
    }

    @Override
    public List<Pet> findAll() {
        return entityManager.createQuery("from Pet").getResultList();
    }

    @Override
    public String editPet(PetPOJO petPOJO) {
        entityManager.getTransaction().begin();

        Optional<Pet> pet = this.findById(petPOJO.getPet_id());
        if (!pet.isPresent()) return "The pet with the entered id does not exist!";
        pet.get().setMicroship(petPOJO.getMicrochip());
        pet.get().setName(petPOJO.getName());
        pet.get().setSpecies(petPOJO.getSpecies());
        pet.get().setRace(petPOJO.getRace());
        pet.get().setSize(petPOJO.getSize());
        pet.get().setSex(petPOJO.getSex());
        pet.get().setPicture(petPOJO.getPicture());

        entityManager.getTransaction().commit();

        return "It has been successfully modified!";
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
