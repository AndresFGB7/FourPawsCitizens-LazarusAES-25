package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;

import java.util.List;
import java.util.Optional;

public interface CaseRepository {

    String save(Case aCase);

    Optional<Case> findById(Integer id);

    List<Case> findAll();

    String deleteById(Integer id);

    String editCase(Integer id, String created_at, String type, String description);
}
