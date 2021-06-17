package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    String save(Pet pet);

    Optional<Pet> findById(Integer id);

    Optional<Pet> findAll(Integer petId);

    List<Pet> findAll();

    String editPet(PetPOJO petPOJO);
    void deleteById(Integer id);

}
