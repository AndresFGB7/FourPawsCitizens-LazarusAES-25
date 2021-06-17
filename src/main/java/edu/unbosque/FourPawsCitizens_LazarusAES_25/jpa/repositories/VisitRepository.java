package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitRepository {

    String save(Visit visit);


    List<Visit> findAll();


}
