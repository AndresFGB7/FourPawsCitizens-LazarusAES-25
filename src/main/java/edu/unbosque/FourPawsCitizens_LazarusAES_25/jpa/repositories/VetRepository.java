package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VetRepository {

    Optional<Vet> save(Vet vet);

    Optional<Vet> findByUserName(String username);

    List<Vet> findAll();

    void deleteByUserName(String username);

    void editVet (String username,String password, String email, String name, String address, String neighborhood);
}
