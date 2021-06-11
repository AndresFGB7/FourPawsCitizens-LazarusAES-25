package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;

import java.util.List;
import java.util.Optional;

public interface CaseRepository {

    Optional<Case> save(Case aCase);

    Optional<Case> findById(Integer id);

    List<Case> findAll();

    void deleteById(Integer id);

    void editCase(Integer id, String created_at, String type, String description, Integer pet_id);
}
