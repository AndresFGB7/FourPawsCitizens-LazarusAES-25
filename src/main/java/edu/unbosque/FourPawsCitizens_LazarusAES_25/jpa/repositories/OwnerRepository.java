package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    Optional<Owner> save(Owner owner);

    Optional<Owner> findById(Integer id);

    List<Owner> findAll();

    void deleteById(Integer id);

    void editOwner (Integer id,String username, String password, String email, Long personId, String name, String adress,String neighborhood);
}
