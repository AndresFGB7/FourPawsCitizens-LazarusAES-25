package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitRepository {

    Optional<Visit> save(Visit visit);

    Optional<Visit> findById(Integer id);

    List<Visit> findAll();

    void deleteById(Integer id);

    void editVisit (Integer id,String created_at, String type, String description, Integer vet_id, Integer pet_id);
}
