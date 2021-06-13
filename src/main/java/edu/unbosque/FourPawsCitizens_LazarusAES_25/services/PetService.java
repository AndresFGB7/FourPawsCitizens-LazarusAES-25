package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OwnerRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OwnerRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.PetRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.PetRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class PetService {

    PetRepository petRepository;
    OwnerRepository ownerRepository;

    public Optional<Pet> createPet(PetPOJO petPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);
        ownerRepository = new OwnerRepositoryImpl(entityManager);

        Optional<Owner> owner = ownerRepository.findById(petPOJO.getOwner_id());
        owner.ifPresent(a -> {
            a.addPets(new Pet(petPOJO.getPet_id(), petPOJO.getMicrochip(), petPOJO.getName(), petPOJO.getSpecies(), petPOJO.getRace(), petPOJO.getSize(), petPOJO.getSex(), petPOJO.getPicture()));
            ownerRepository.save(a);
        });
        entityManager.close();
        entityManagerFactory.close();

        return Optional.empty();
    }

    public Optional<PetPOJO> findPet(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);

        Optional<Pet> pet = petRepository.findById(id);
        System.out.println("pet find id -- > " + pet);
        entityManager.close();
        entityManagerFactory.close();

        if (pet.isPresent()) {
           Integer id2 = Math.toIntExact(pet.get().getOwner_id().getPersonId());
            System.out.println("este es el id perroooooo " + id2);
            return Optional.of(new PetPOJO(pet.get().getPet_id(), pet.get().getMicrochip(), pet.get().getName(), pet.get().getSpecies(), pet.get().getRace(), pet.get().getSize(), pet.get().getSex(), pet.get().getPicture(), id2));
        }
        System.out.println("no encuentra eso perroooo");
        return Optional.empty();

    }

    public List<PetPOJO> ListPets(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);
        List<Pet> pets = petRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<PetPOJO> petPOJOS = new ArrayList<>();
        for(Pet pet : pets){
            petPOJOS.add(new PetPOJO(pet.getPet_id(),pet.getMicrochip(),pet.getName(),pet.getSpecies(),pet.getRace(),pet.getSize(),pet.getSex(),pet.getPicture(),Math.toIntExact(pet.getOwner_id().getPersonId())));
        }

        return petPOJOS;
    }

    public void editPet(Integer pet_id, String microship, String name, String species, String race, String size, String sex, String picture){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);

        petRepository.editPet(pet_id,microship,name,species,race,size,sex,picture);

        entityManager.close();
        entityManagerFactory.close();
    }

    public void deletePet(Integer id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);

        petRepository.deleteById(id);

        entityManager.close();
        entityManagerFactory.close();
    }

}
