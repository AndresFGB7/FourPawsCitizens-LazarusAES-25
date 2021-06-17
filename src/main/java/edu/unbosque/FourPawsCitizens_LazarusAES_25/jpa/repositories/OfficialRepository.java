package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;

import java.util.List;
import java.util.Optional;

public interface OfficialRepository {

     public String save(Official oficial);

     List<Official> findAll();

}
