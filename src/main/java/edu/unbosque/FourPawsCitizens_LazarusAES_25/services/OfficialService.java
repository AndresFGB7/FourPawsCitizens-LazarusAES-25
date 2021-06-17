package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;


import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.UserApp;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OfficialRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OfficialPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OfficialPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;

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
        System.out.println("entra crear offficial 1");
        Optional<UserApp> user = userAppRepository.findByUsername(username);
        officialPOJO.setUsername(username);
        System.out.println("entra crear offficial 2");
        if (!user.isPresent()) return "The user does not exist";
        System.out.println("---> user " + user.get());
        System.out.println("entra crear offficial 3");
        Official official = new Official(officialPOJO.getName());

        user.get().addOfficial(official);

        String reply = userAppRepository.save(user.get());

        entityManager.close();
        entityManagerFactory.close();

        return reply;
    }

    public List<OfficialPOJO> listOfficial() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);

        List<Official> official =  officialRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OfficialPOJO> officialPOJOS = new ArrayList<>();
        for (Official official1 : official) {
            officialPOJOS.add(new OfficialPOJO(official1.getUserApp().getUsername(),official1.getName()));
        }
        return officialPOJOS;
    }
}
