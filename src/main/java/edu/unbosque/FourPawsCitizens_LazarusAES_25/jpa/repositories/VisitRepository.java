package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitRepository {

   String save(Visit visit);

    Optional<Visit> findById(Integer id);

    List<Visit> findAll();

    String deleteById(Integer id);

    String editVisit (Integer id,String created_at, String type, String description, Integer vet_id, Integer pet_id);
}
