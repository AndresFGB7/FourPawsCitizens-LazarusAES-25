package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VetRepository {

    String save(Vet vet);

    Optional<Vet> findByUserName(String username);

    List<Vet> findAll();

    String deleteByUserName(String username);

    String editVet (String username, String password, String email,String role,Integer vetId, String name, String address, String neighborhood);

}
