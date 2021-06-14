package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    public String save(Owner owner);

    Optional<Owner> findById(String id);

    List<Owner> findAll();

    public Optional<Owner> findByUsername(String username);

    String deleteByUserName(String Username);

    public String editOwner(Integer id, String username, String name, String adress, String neighborhood);
}
