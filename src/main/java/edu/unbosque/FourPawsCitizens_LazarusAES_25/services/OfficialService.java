package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;


import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.UserApp;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
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
    UserAppRepository userAppRepository;

    public String saveOfficial(String username,OfficialPOJO officialPOJO) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        officialRepository = new OfficialRepositoryImpl(entityManager);
        userAppRepository = new UserAppRepositoryImpl(entityManager);
        System.out.println("entra aqui we");
        Optional<UserApp> user = userAppRepository.findByUsername(username);
        officialPOJO.setUsername(username);

        if (!user.isPresent()) return "The user does not exist";
        System.out.println("---> user " + user.get().getUsername());

        Official official = new Official(officialPOJO.getName());

        user.get().addOfficial(official);

        String reply = userAppRepository.save(user.get());

        entityManager.close();
        entityManagerFactory.close();

        return reply;
    }
}
