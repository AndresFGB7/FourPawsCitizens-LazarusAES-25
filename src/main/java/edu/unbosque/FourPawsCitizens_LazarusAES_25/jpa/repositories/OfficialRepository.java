package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;

import java.util.Optional;

public interface OfficialRepository {

     Optional<Official> save(Official oficial);

}
