package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PetService<fin> {

    private PetRepository petRepository;
    private OwnerRepository ownerRepository;
    private VisitRepository visitRepository;
    private CaseRepository caseRepository;

    /**
     * Method that saves a pet
     *
     * @param petPOJO: pet's pojo
     * @return a message
     */
    public String savePet(PetPOJO petPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);
        ownerRepository = new OwnerRepositoryImpl(entityManager);
        //Create an owner optional object an save the id that entry
      //  Optional<Owner> owner = ownerRepository.findByUsername(petPOJO.get);

        //If the owner with the id doesn't exists return a message
      //  if (!owner.isPresent()) return "The username of the owner entered does not exist!";

        List<Pet> petList = petRepository.findAll();
        //If the owner with the id exist, scroll through the pet list to verify that the microchip does not exist
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getMicroship() != null && petList.get(i).getMicroship().equals(petPOJO.getMicrochip()))
                return "The entered microchip already exists";
        }
        //If the microchip doesn't exist save the pet in the pojo and save the pet in the owner
        Pet pet = new Pet(
                petPOJO.getPet_id(),
                petPOJO.getMicrochip(),
                petPOJO.getName(),
                petPOJO.getSpecies(),
                petPOJO.getRace(),
                petPOJO.getSize(),
                petPOJO.getSex(),
                petPOJO.getPicture());
               //owner.get().addPet(pet);

     //   ownerRepository.save(owner.get());

        entityManager.close();
        entityManagerFactory.close();
        return "Pet has been successfully registered!";
    }

    /**
     * Method that modify the data of a petPojo
     *
     * @param petPOJO pet's pojo
     * @return string message
     */
    public String modifyPet(PetPOJO petPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES_256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);

        List<Pet> petList = petRepository.findAll();
        //If the owner with the id exist, scroll through the pet list to verify that the microchip does not exist
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getMicroship() != null && petList.get(i).getMicroship().equals(petPOJO.getMicrochip()))
                return "The entered microchip already exists";
        }
        String message = petRepository.editPet(petPOJO);

        entityManager.close();
        entityManagerFactory.close();
        return message;
        }

}

