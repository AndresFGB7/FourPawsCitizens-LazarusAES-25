package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;

import java.util.List;
import java.util.Optional;

public interface OfficialRepository {

    String save(Official oficial);

    List<Official> listAll();

    List<Official> getByUsername(String username);

}
