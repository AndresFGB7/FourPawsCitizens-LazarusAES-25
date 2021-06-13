package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;



import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OfficialRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OfficialRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OfficialRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OfficialPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OfficialPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The service of Official, use Repository of official
 */
public class OfficialService {

    OfficialRepository officialRepository;

    public Optional<OfficialPOJO> saveOfficial(OfficialPOJO officialPOJO){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);

        Official official = new Official(officialPOJO.getUsername(),officialPOJO.getPassword(),officialPOJO.getEmail(),officialPOJO.getName());
        Optional<Official> persistedOfficial = officialRepository.save(official);

        entityManager.close();
        entityManagerFactory.close();

        if (persistedOfficial.isPresent()) {
            return Optional.of(new OfficialPOJO(
                    persistedOfficial.get().getPassword(),
                    persistedOfficial.get().getEmail(),
                    persistedOfficial.get().getUsername(),
                    persistedOfficial.get().getName()));
        } else {
            return Optional.empty();
        }
    }

}
