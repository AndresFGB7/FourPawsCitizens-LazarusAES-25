package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    String save(Owner owner);

    Optional<Owner> findByUsername(String Username);

    List<Owner> findAll();

    String deleteByUsername(String username);

    String editOwner (String username, String password, String email, Integer personId, String name, String adress,String neighborhood);
}
